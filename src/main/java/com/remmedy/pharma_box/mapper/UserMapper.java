package com.remmedy.pharma_box.mapper;

import com.remmedy.pharma_box.dto.user.UserDto;
import com.remmedy.pharma_box.dto.user.UserNew;
import com.remmedy.pharma_box.dto.user.UserUpdate;
import com.remmedy.pharma_box.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "role", constant = "USER")
    User toUser(UserNew userNew);

    List<UserDto> toUserDtoList(List<User> userList);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdate userUpdate);
}
