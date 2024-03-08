package com.chanthai.phoneshop.controller;

import com.chanthai.phoneshop.dto.ModelDTO;
import com.chanthai.phoneshop.entity.Model;
import com.chanthai.phoneshop.mapper.ModelMapper;
import com.chanthai.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;
    private final ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO){
        Model model = modelMapper.toModel(modelDTO);
        model = modelService.save(model);
        return ResponseEntity.ok(modelMapper.toModelDTO(model));
    }
}
