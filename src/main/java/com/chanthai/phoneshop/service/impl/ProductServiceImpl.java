package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.dto.ProductImportDTO;
import com.chanthai.phoneshop.entity.ProductImportHistory;
import com.chanthai.phoneshop.exception.APIException;
import com.chanthai.phoneshop.exception.ResourceNotFoundException;
import com.chanthai.phoneshop.mapper.ProductMapper;
import com.chanthai.phoneshop.repository.ProductImportHistoryRepository;
import com.chanthai.phoneshop.repository.ProductRepository;
import com.chanthai.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository importHistoryRepository;
    private final ProductMapper productMapper;
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

    @Override
    public void importProduct(ProductImportDTO importDTO) {
        if (importDTO.getImportUnit() == null){
            throw new APIException(HttpStatus.BAD_REQUEST,"Import unit can't be null");
        }
        //Update availableUnit Product
        Product product = getByID(importDTO.getProductId());
        Integer availableUnit = 0;
        if (product.getAvailableUnit() != null){
            availableUnit = product.getAvailableUnit();
        }
        product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
        productRepository.save(product);

        //save Product Import History
        ProductImportHistory productImportHistory = productMapper.toProductImportHistory(importDTO,product);
        importHistoryRepository.save(productImportHistory);
    }
}
