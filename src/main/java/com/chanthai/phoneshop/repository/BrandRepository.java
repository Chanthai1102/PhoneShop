package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    List<Brand> findByNameLike(String name);
}
