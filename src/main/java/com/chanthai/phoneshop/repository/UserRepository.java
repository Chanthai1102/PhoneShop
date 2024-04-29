package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByusername(String username);
}
