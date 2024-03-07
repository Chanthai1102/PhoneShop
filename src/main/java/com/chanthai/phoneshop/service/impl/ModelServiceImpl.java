package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.entity.Model;
import com.chanthai.phoneshop.service.BrandService;
import com.chanthai.phoneshop.service.ModelService;
import com.chanthai.phoneshop.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {
    private ModelRepository modelRepository;
    private BrandService brandService;
    @Override
    public Model save(Model model) {
        Integer brandId = model.getBrand().getId();
        brandService.getById(brandId);
        return modelRepository.save(model);
    }
}
