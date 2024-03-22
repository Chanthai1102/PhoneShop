package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.Sale;
import com.chanthai.phoneshop.projection.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long>{
    List<ProductSold> findProductSold(LocalDate startDate, LocalDate endDate);
}
