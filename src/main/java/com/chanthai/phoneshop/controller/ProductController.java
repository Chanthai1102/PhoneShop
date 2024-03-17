package com.chanthai.phoneshop.controller;

import com.chanthai.phoneshop.dto.PriceDTO;
import com.chanthai.phoneshop.dto.ProductDTO;
import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.dto.ProductImportDTO;
import com.chanthai.phoneshop.mapper.ProductMapper;
import com.chanthai.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
        Product product = productMapper.toProduct(productDTO);
        product = productService.create(product);
        return ResponseEntity.ok(product);
    }
    @PostMapping("importProduct")
    public ResponseEntity<?> importProduct(@RequestBody @Valid ProductImportDTO productImportDTO){
        productService.importProduct(productImportDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{productId}/setSalePrice")
    public ResponseEntity<?> setSalePrice(@PathVariable Long productId, @RequestBody PriceDTO priceDTO){
        productService.setSalePrice(productId,priceDTO.getPrice());
        return ResponseEntity.ok().build();
    }

    @PostMapping("uploadProduct")
    public ResponseEntity<?> uploadProduct(@RequestParam("file") MultipartFile file) throws IOException {
        Map<Integer,String> errorMap =  productService.uploadProduct(file);
        return ResponseEntity.ok(errorMap);
    }
}
