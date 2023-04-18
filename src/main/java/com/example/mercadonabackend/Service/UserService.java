package com.example.mercadonabackend.Service;


import com.example.mercadonabackend.pojo.UserEntity;

public interface UserService {

    void createUser(UserEntity user);

    void deleteUser(Long id);

    void updateUser(Long userId, UserEntity user);




}
