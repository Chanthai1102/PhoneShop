package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand create(Brand brand);
    Brand getById(Integer id);
    Brand update(Integer id, Brand brandUpdate);
    List<Brand> getBrands();
    List<Brand> getBrands(String name);
}
