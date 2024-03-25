package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.ProductImportHistory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long >, JpaSpecificationExecutor<ProductImportHistory> {

}
