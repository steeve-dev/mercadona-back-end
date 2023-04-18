package com.example.mercadonabackend.Service.impl;

import com.example.mercadonabackend.Service.UserService;
import com.example.mercadonabackend.pojo.UserEntity;
import com.example.mercadonabackend.repository.UserRepository;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public void createUser(UserEntity user) {

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long userId, UserEntity user) {

    }
}
