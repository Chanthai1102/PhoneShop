package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.entity.Brand;

public interface BrandService {
    Brand create(Brand brand);
    Brand getById(Integer id);
    Brand update(Integer id, Brand brandUpdate);
}
