package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.entity.Brand;
import com.chanthai.phoneshop.repository.BrandRepository;
import com.chanthai.phoneshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }
}
