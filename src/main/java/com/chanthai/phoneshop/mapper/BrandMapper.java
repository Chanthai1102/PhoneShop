package com.chanthai.phoneshop.mapper;

import com.chanthai.phoneshop.dto.BrandDTO;
import com.chanthai.phoneshop.entity.Brand;
import org.mapstruct.Mapper;

@Mapper
public interface BrandMapper {
    Brand toBrand(BrandDTO brandDTO);

}
