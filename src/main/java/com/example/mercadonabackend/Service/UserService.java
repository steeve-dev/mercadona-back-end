package com.example.mercadonabackend.Service;


import com.example.mercadonabackend.security.User;

public interface UserService {

    void createUser(User user);

    void deleteUser(Long id);

    void updateUser(Long userId, User user);




}
