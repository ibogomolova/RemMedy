package com.remmedy.pharma_box.mapper;

import com.remmedy.pharma_box.dto.CreateUserDto;
import com.remmedy.pharma_box.dto.UserDto;
import com.remmedy.pharma_box.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(CreateUserDto dto);
}
