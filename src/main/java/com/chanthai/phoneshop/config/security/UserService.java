package com.chanthai.phoneshop.config.security;


import java.util.Optional;

public interface UserService {
    Optional<AuthUser> findUserByUsername(String username);

}
