package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.config.security.AuthUser;
import com.chanthai.phoneshop.config.security.RoleEnum;
import com.chanthai.phoneshop.config.security.UserService;
import com.chanthai.phoneshop.entity.User;
import com.chanthai.phoneshop.exception.APIException;
import com.chanthai.phoneshop.exception.ResourceNotFoundException;
import com.chanthai.phoneshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public Optional<AuthUser> findUserByUsername(String username) {
        User user = userRepository.findByusername(username)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND,"User not Found!!"));
        AuthUser authUser = AuthUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole().getAuthorities())
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .enable(user.isEnable())
                .build();
        return Optional.ofNullable(authUser);
    }
}
