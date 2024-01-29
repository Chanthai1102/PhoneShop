package com.chanthai.phoneshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbBrands")
public class Brand {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "brand_id")
     private Integer id;
     @Column(name = "brand_name")
     private String name;
}
