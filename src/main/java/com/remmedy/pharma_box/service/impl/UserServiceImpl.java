package com.remmedy.pharma_box.service.impl;

import com.remmedy.pharma_box.dto.CreateUserDto;
import com.remmedy.pharma_box.dto.UserDto;
import com.remmedy.pharma_box.entity.User;
import com.remmedy.pharma_box.exception.UserNotFoundException;
import com.remmedy.pharma_box.mapper.UserMapper;
import com.remmedy.pharma_box.repository.MedicineRepository;
import com.remmedy.pharma_box.repository.UserRepository;
import com.remmedy.pharma_box.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MedicineRepository medicineRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto create(CreateUserDto dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(dto.getPassword());
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto getById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto update(UUID id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        User updated = userRepository.save(user);
        return userMapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}
