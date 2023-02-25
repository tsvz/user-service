package com.example.userservice.service;

import com.example.userservice.dto.UserDto;

public interface UserService {
    UserDto create(UserDto userDto);
    UserDto update(Integer userId, UserDto userDto);
    UserDto get(Integer userId);
    void delete(Integer userId);

}
