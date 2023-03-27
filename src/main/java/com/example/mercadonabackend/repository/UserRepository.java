package com.example.mercadonabackend.repository;

import com.example.mercadonabackend.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.desktop.OpenFilesEvent;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
