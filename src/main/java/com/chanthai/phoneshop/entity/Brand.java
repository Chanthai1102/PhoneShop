package com.chanthai.phoneshop.entity;

import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "tbBrands")
public class Brand extends AuditEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "brand_id")
     private Long id;
     @Column(name = "brand_name")
     private String name;
}
