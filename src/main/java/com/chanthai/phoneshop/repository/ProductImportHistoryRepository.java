package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long > {
}
