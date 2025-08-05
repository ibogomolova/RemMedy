package com.remmedy.pharma_box.service;

import com.remmedy.pharma_box.dto.CreateUserDto;
import com.remmedy.pharma_box.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto create (CreateUserDto dto);
    List<UserDto> getAll();
    UserDto getById(UUID id);
    UserDto update(UUID id ,UserDto dto);
    void delete (UUID id);
}
