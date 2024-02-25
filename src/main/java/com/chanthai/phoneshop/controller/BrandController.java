package com.chanthai.phoneshop.controller;

import com.chanthai.phoneshop.dto.BrandDTO;
import com.chanthai.phoneshop.entity.Brand;
import com.chanthai.phoneshop.mapper.BrandMapper;
import com.chanthai.phoneshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("brands")
public class BrandController {
    @Autowired
    private BrandService brandService;
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
    public ResponseEntity<?> getBrands(){
        List<BrandDTO> list = brandService.getBrands()
                .stream()
                .map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
                .collect(Collectors.toList());
        return  ResponseEntity.ok(list);
    }
    @GetMapping("filter")
    public ResponseEntity<?> getBrands(@RequestParam("name") String name){
        List<BrandDTO> list = brandService.getBrands(name)
                .stream()
                .map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
                .collect(Collectors.toList());
        return  ResponseEntity.ok(list);
    }

}
