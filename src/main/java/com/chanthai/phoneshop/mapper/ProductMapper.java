package com.chanthai.phoneshop.mapper;

import com.chanthai.phoneshop.dto.ProductDTO;
import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.service.BrandService;
import com.chanthai.phoneshop.service.ColorService;
import com.chanthai.phoneshop.service.ModelService;
import com.chanthai.phoneshop.service.ProductService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ModelService.class, ColorService.class})
public interface ProductMapper {
    @Mapping(target = "model",source = "modelId")
    @Mapping(target = "color",source = "colorId")
    Product toProduct(ProductDTO productDTO);
}
