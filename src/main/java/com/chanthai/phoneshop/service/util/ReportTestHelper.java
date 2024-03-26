package com.chanthai.phoneshop.service.util;

import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.entity.ProductImportHistory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReportTestHelper {
    private static Product product1 = Product.builder()
            .id(1L)
            .name("iphone 5")
            .build();
    private static Product product2 = Product.builder()
            .id(2L)
            .name("iphone 6")
            .build();
    private static Product product3 = Product.builder()
            .id(3L)
            .name("iphone 7")
            .build();

    public static List<Product> getProducts(){
        return List.of(product1,product2);
    }


    public static List<ProductImportHistory> getProductImportHistories(){
        ProductImportHistory history1 = ProductImportHistory.builder()
                .product(product1)
                .importUnit(10)
                .pricePerUnit(BigDecimal.valueOf(1200))
                .dateImport(LocalDateTime.of(2024,3,1,11,50))
                .build();
        ProductImportHistory history2 = ProductImportHistory.builder()
                .product(product2)
                .importUnit(20)
                .pricePerUnit(BigDecimal.valueOf(1300))
                .dateImport(LocalDateTime.of(2024,3,2,11,50))
                .build();
        ProductImportHistory history3 = ProductImportHistory.builder()
                .product(product3)
                .importUnit(30)
                .pricePerUnit(BigDecimal.valueOf(1400))
                .dateImport(LocalDateTime.of(2024,3,4,11,50))
                .build();
        return List.of(history1,history2,history3);
    }
}
