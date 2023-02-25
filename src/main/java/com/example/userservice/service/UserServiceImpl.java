package com.example.userservice.service;

import com.example.userservice.converter.UserConverter;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = Logger.getLogger(String.valueOf(UserServiceImpl.class));

    private final UserConverter converter;
    private final UserRepository repository;

    public UserServiceImpl(UserConverter converter, UserRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }


    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        System.out.println(userDto);
        var user = converter.toEntity(userDto);
        System.out.println(user);
        logger.info("created user: " + user);
        user = repository.save(user);
        return converter.toDto(user);
    }

    @Override
    @Transactional
    public UserDto update(Integer userId, UserDto userDto) {
        var user = getById(userId);
        updateFields(userDto, user);
        user = repository.save(user);
        var userDtoUpdated = converter.toDto(user);
        logger.info("updated user: " + userDtoUpdated);
        return userDtoUpdated;
    }

    private void updateFields(UserDto userDto, User user) {
        user.setUsername(userDto.username());
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setEmail(userDto.email());
        user.setPhone(userDto.phone());
    }

    @Override
    @Transactional
    public void delete(Integer userId) {
        repository.findById(userId).orElseThrow(() -> {
            logger.warning("user was not fount with id: " + userId);
            throw new RuntimeException("user was not fount with id: " + userId);
        });
        repository.deleteById(userId);
    }

    @Override
    public UserDto get(Integer userId) {
        var user = getById(userId);
        return converter.toDto(user);
    }

    private User getById(Integer userId) {
        return repository.findById(userId).orElseThrow(() -> {
            logger.warning("user was not fount with id: " + userId);
            throw new RuntimeException("user was not fount with id: " + userId);
        });
    }
}
