package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.dto.ProductImportDTO;

public interface ProductService {
    Product create(Product product);
    Product getByID(Long id);

    void importProduct(ProductImportDTO importDTO);
}
