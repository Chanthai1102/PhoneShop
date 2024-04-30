package com.chanthai.phoneshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
