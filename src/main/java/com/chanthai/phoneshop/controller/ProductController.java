package com.chanthai.phoneshop.controller;

import com.chanthai.phoneshop.dto.ProductDTO;
import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.mapper.ProductMapper;
import com.chanthai.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductService productService;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
        Product product = productMapper.toProduct(productDTO);
        product = productService.create(product);
        return ResponseEntity.ok(product);
    }
}
