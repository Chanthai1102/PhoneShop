package com.chanthai.phoneshop.mapper;

import com.chanthai.phoneshop.dto.ModelDTO;
import com.chanthai.phoneshop.entity.Model;
import com.chanthai.phoneshop.service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelEntityMapper {
    ModelEntityMapper INSTANCE = Mappers.getMapper(ModelEntityMapper.class);
    @Mapping(target = "brand", source = "brandId")
    Model toModel(ModelDTO dto);
    @Mapping(target = "brandId", source = "brand.id")
    ModelDTO toModelDTO(Model model);
}
