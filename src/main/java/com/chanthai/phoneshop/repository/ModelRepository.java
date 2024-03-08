package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.Brand;
import com.chanthai.phoneshop.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model,Integer> {
    List<Model> findByBrandId(Integer brandId);
}
