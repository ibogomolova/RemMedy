package com.remmedy.pharma_box.service.impl;

import com.remmedy.pharma_box.dto.user.UserDto;
import com.remmedy.pharma_box.dto.user.UserNew;
import com.remmedy.pharma_box.dto.user.UserUpdate;
import com.remmedy.pharma_box.exception.EmailAlreadyExistsException;
import com.remmedy.pharma_box.exception.UserNotFoundException;
import com.remmedy.pharma_box.mapper.UserMapper;
import com.remmedy.pharma_box.model.User;
import com.remmedy.pharma_box.repository.UserRepository;
import com.remmedy.pharma_box.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto userRegistration(UserNew userNew) {
        validateEmailUniqueness(userNew.email());
        User user = userMapper.toUser(userNew);
        User savedUser = userRepository.save(user);
        UserDto userDto = userMapper.toUserDto(savedUser);
        log.info("User with ID = {} created.", userDto.id());
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "lastName");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<User> userPage = userRepository.findAll(pageRequest);
        List<User> userList = userPage.getContent();
        List<UserDto> userDtoList = userMapper.toUserDtoList(userList);
        log.info("User List on page {} with size {} has been returned.", page, size);
        return userDtoList;
    }

    @Override
    public UserDto getUserById(UUID userId) {
        User user = findUserById(userId);
        UserDto userDto = userMapper.toUserDto(user);
        log.info("User with ID {} returned.", userId);
        return userDto;
    }

    @Override
    public UserDto updateUserById(UUID userId, UserUpdate userUpdate) {
        User user = findUserByIdForUpdate(userId);

        if (userUpdate.email() != null && !userUpdate.email().equals(user.getEmail())) {
            validateEmailUniqueness(userUpdate.email());
        }

        userMapper.updateUser(user, userUpdate);
        User updatedUser = userRepository.save(user);
        UserDto userDto = userMapper.toUserDto(updatedUser);
        log.info("User with ID {} updated.", userId);
        return userDto;
    }

    @Override
    public void deleteUserById(UUID userId) {
        User user = findUserById(userId);
        userRepository.delete(user);
        log.info("User with ID {} has been deleted.", userId);
    }

    private User findUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("The User with ID = %s not found",
                        userId)));
    }

    private User findUserByIdForUpdate(UUID userId) {
        return userRepository.findUserByIdForUpdate(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("The User with ID = %s not found", userId)));
    }

    private void validateEmailUniqueness(String email) {
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException(String.format("Email = %s already exists",
                            email));
                });
    }
}
