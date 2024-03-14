package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.dto.ModelDTO;
import com.chanthai.phoneshop.entity.Model;

import java.util.List;

public interface ModelService {
    Model save (Model model);
    List<Model> getByBrand(Long brandId);
    Model getById(Long id);
}
