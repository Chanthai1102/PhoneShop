package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.dto.ProductImportDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public interface ProductService {
    Product create(Product product);
    Product getByID(Long id);

    Product getByModelIdAndColorId (Long modelId, Long colorId);

    void importProduct(ProductImportDTO importDTO);

    void setSalePrice(Long productId, BigDecimal price);

    void validateStock(Long productId, Integer numberOfUnit);

    Map<Integer,String> uploadProduct(MultipartFile file) throws IOException;
}
