package com.chanthai.phoneshop.controller;

import com.chanthai.phoneshop.dto.BrandDTO;
import com.chanthai.phoneshop.entity.Brand;
import com.chanthai.phoneshop.service.BrandService;
import com.chanthai.phoneshop.service.util.Mapper;
import org.hibernate.Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("brands")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
        Brand brand = Mapper.toBrand(brandDTO);
        brand = brandService.create(brand);
        return ResponseEntity.ok(Mapper.toBrandDTO(brand));
    }
    @GetMapping ("{id}")
    public ResponseEntity<?> getOneBrand(@PathVariable Integer id){
        Brand brand = brandService.getById(id);
        return ResponseEntity.ok(Mapper.toBrandDTO(brand));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer brandId, @RequestBody BrandDTO brandDTO){
        Brand brand = Mapper.toBrand(brandDTO);
        Brand updateBrand = brandService.update(brandId,brand);
        return ResponseEntity.ok(Mapper.toBrandDTO(updateBrand));
    }

}
