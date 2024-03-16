package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long>{
}
