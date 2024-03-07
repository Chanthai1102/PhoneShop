package com.chanthai.phoneshop.mapper;

import com.chanthai.phoneshop.dto.ModelDTO;
import com.chanthai.phoneshop.entity.Brand;
import com.chanthai.phoneshop.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
    @Mapping(target = "brand", source = "brandId")
    Model toModel(ModelDTO dto);

    default Brand toBrand(Integer brandID){
        Brand brand = new Brand();
        brand.setId(brandID);
        return brand;
    }
}
