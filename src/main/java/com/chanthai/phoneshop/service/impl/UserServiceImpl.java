package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.config.security.AuthUser;
import com.chanthai.phoneshop.config.security.RoleEnum;
import com.chanthai.phoneshop.config.security.UserService;
import com.chanthai.phoneshop.entity.Role;
import com.chanthai.phoneshop.entity.User;
import com.chanthai.phoneshop.exception.APIException;
import com.chanthai.phoneshop.exception.ResourceNotFoundException;
import com.chanthai.phoneshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .authorities(getAuthorities(user.getRoles()))
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .enable(user.isEnable())
                .build();
        return Optional.ofNullable(authUser);
    }
    private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles){
        Set<SimpleGrantedAuthority> authorities1 = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE" + role.getName()))
                .collect(Collectors.toSet());
        Set<SimpleGrantedAuthority> authorities =  roles.stream().flatMap(role -> toStream(role))
                .collect(Collectors.toSet());
        authorities.addAll(authorities1);
        return authorities;
    }

    private Stream<SimpleGrantedAuthority> toStream(Role role){
        return role.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()));
    }
}
