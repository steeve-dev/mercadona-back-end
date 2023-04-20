package com.example.mercadonabackend.repository;

import com.example.mercadonabackend.pojo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String name);
    UserEntity findByEmail(String email);
}
