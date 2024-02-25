package com.chanthai.phoneshop.mapper;

import com.chanthai.phoneshop.dto.BrandDTO;
import com.chanthai.phoneshop.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    Brand toBrand(BrandDTO brandDTO);
    BrandDTO toBrandDTO(Brand entity);

}
