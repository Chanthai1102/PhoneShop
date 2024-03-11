package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.entity.Model;
import com.chanthai.phoneshop.mapper.ModelEntityMapper;
import com.chanthai.phoneshop.service.ModelService;
import com.chanthai.phoneshop.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelEntityMapper modelEntityMapper;
    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public List<Model> getByBrand(Long brandId) {
        return modelRepository.findByBrandId(brandId);
    }
}
