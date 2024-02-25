package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.entity.Brand;
import com.chanthai.phoneshop.exception.ResourceNotFoundException;
import com.chanthai.phoneshop.repository.BrandRepository;
import com.chanthai.phoneshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand getById(Integer id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand",id));
    }

    @Override
    public Brand update(Integer id, Brand brandUpdate) {
        Brand brand = getById(id);
        brand.setName(brandUpdate.getName());
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getBrands() {
        return  brandRepository.findAll();
    }

    @Override
    public List<Brand> getBrands(String name) {
        return brandRepository.findByNameLike("%" + name + "%");
    }
}
