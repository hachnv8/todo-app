package com.hacheery.todoapp.mapper;

import com.hacheery.todoapp.dto.UserDto;
import com.hacheery.todoapp.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
