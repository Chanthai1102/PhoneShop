package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.entity.Product;

public interface ProductService {
    Product create(Product product);
    Product getByID(Long id);
}
