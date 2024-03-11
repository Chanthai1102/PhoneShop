package com.chanthai.phoneshop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbColors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Long id;
    @Column(name = "color_name")
    private String name;
}
