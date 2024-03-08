package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.dto.ModelDTO;
import com.chanthai.phoneshop.entity.Model;
import com.chanthai.phoneshop.mapper.ModelMapper;
import com.chanthai.phoneshop.service.BrandService;
import com.chanthai.phoneshop.service.ModelService;
import com.chanthai.phoneshop.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }
}
