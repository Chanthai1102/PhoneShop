package com.chanthai.phoneshop.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tbProduct", uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id","color_id"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", unique = true)
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "available_unit")
    private Integer availableUnit;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "sale_price")
    @DecimalMin(value = "0.00001",message = "Price Must Be Greater than 0")
    private BigDecimal salePrice;

}
