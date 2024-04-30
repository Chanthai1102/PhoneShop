package com.chanthai.phoneshop.entity;

import com.chanthai.phoneshop.config.security.RoleEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enable;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
