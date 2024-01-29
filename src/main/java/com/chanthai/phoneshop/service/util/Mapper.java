package com.chanthai.phoneshop.service.util;

import com.chanthai.phoneshop.dto.BrandDTO;
import com.chanthai.phoneshop.entity.Brand;

public class Mapper {
    public static Brand toBrand(BrandDTO dto){
        Brand brand = new Brand();
        brand.setName(dto.getName());
        return brand;
    }
    public static BrandDTO toBrandDTO(Brand brand){
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName(brand.getName());
        return brandDTO;
    }
}
