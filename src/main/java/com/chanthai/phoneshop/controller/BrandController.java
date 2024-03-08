package com.chanthai.phoneshop.controller;

import com.chanthai.phoneshop.dto.BrandDTO;
import com.chanthai.phoneshop.dto.ModelDTO;
import com.chanthai.phoneshop.dto.PageDTO;
import com.chanthai.phoneshop.entity.Brand;
import com.chanthai.phoneshop.entity.Model;
import com.chanthai.phoneshop.mapper.BrandMapper;
import com.chanthai.phoneshop.mapper.ModelEntityMapper;
import com.chanthai.phoneshop.service.BrandService;
import com.chanthai.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequiredArgsConstructor
@RestController
@RequestMapping("brands")
public class BrandController {
    private final BrandService brandService;
    private final ModelService modelService;
    private final ModelEntityMapper modelMapper;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brand = brandService.create(brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }
    @GetMapping ("{id}")
    public ResponseEntity<?> getOneBrand(@PathVariable Integer id){
        Brand brand = brandService.getById(id);
        return ResponseEntity.ok( BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer brandId, @RequestBody BrandDTO brandDTO){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        Brand updateBrand = brandService.update(brandId,brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updateBrand));
    }
    @GetMapping
    public ResponseEntity<?> getBrands(@RequestParam Map<String,String> params){
        Page<Brand> page  = brandService.getBrands(params);
        PageDTO pageDTO = new PageDTO(page);
        /*List<BrandDTO> list = brandService.getBrands(params)
                .stream()
                .map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
                .collect(Collectors.toList());*/
        return  ResponseEntity.ok(pageDTO);
    }

    @GetMapping ("{id}/models")
    public ResponseEntity<?> getModelsByBrand(@PathVariable Integer id){
        List<Model> brands = modelService.getByBrand(id);
        List<ModelDTO> list = brands.stream()
                .map(modelMapper::toModelDTO)
                .toList();
        return ResponseEntity.ok(list);
    }

}
