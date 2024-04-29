package com.chanthai.phoneshop.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceFakeImpl implements UserService{
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<AuthUser> findUserByUsername(String username) {
        List<AuthUser> users = List.of(
                new AuthUser("thai",passwordEncoder.encode("thai1234"),RoleEnum.ADMIN.getAuthorities(),true,true,true,true),
                new AuthUser("nith",passwordEncoder.encode("nith1234"),RoleEnum.ADMIN.getAuthorities(),true,true,true,true)
        );
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
