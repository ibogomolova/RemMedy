package com.remmedy.pharma_box.service;

import com.remmedy.pharma_box.dto.user.UserDto;
import com.remmedy.pharma_box.dto.user.UserNew;
import com.remmedy.pharma_box.dto.user.UserUpdate;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto userRegistration(UserNew userNew);

    List<UserDto> getAllUsers(int page, int size);

    UserDto getUserById(UUID userId);

    UserDto updateUserById(UUID userId, UserUpdate userUpdate);

    void deleteUserById(UUID userId);
}
