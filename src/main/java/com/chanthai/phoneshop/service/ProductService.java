package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.dto.ProductImportDTO;

import java.math.BigDecimal;

public interface ProductService {
    Product create(Product product);
    Product getByID(Long id);

    void importProduct(ProductImportDTO importDTO);

    void setSalePrice(Long productId, BigDecimal price);

    void validateStock(Long productId, Integer numberOfUnit);
}
