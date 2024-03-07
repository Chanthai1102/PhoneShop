package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model,Integer> {
}
