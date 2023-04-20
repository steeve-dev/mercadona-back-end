package com.example.mercadonabackend.Service;


import com.example.mercadonabackend.dto.RegistrationDto;
import com.example.mercadonabackend.pojo.UserEntity;

public interface UserService {

    void createUser(RegistrationDto registrationDto);

    void deleteUser(Long id);

    void updateUser(Long userId, UserEntity user);


    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
