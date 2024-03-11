package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.repository.ProductRepository;
import com.chanthai.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }
}
