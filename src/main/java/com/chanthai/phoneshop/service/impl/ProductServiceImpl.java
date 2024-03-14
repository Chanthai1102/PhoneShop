package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.exception.ResourceNotFoundException;
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
        String name = "%s %s".formatted(product.getModel().getName(),product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getByID(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product",id));
    }
}
