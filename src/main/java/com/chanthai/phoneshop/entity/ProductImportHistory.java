package com.chanthai.phoneshop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbProductImportHistory")
public class ProductImportHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "import_id")
    private Long id;

    @Column(name = "date_import")
    private LocalDateTime dateImport;

    @Column(name = "import_unit")
    private Integer importUnit;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
