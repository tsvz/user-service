package com.example.userservice.converter;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import com.example.userservice.service.UserServiceImpl;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel="spring")
public interface UserConverter {
    User toEntity(UserDto dto);
    UserDto toDto(User entity);
}
