package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail,Long>{
    List<SaleDetail> findBySaleId(Long saleId);
}
