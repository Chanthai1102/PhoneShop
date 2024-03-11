package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.Brand;
import com.chanthai.phoneshop.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model,Long> {
    List<Model> findByBrandId(Long brandId);
}
