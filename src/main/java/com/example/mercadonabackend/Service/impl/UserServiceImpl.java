package com.example.mercadonabackend.Service.impl;

import com.example.mercadonabackend.Service.UserService;
import com.example.mercadonabackend.repository.UserRepository;
import com.example.mercadonabackend.security.User;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long userId, User user) {

    }
}
