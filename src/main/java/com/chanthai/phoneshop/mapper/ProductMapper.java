package com.chanthai.phoneshop.mapper;

import com.chanthai.phoneshop.dto.ProductDTO;
import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.dto.ProductImportDTO;
import com.chanthai.phoneshop.entity.ProductImportHistory;
import com.chanthai.phoneshop.service.ColorService;
import com.chanthai.phoneshop.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ModelService.class, ColorService.class})
public interface ProductMapper {
    @Mapping(target = "model",source = "modelId")
    @Mapping(target = "color",source = "colorId")
    Product toProduct(ProductDTO productDTO);

    @Mapping(target = "dateImport",source = "productImportDTO.importDate")
    @Mapping(target = "pricePerUnit",source = "productImportDTO.importPrice")
    @Mapping(target = "product",source = "product")
    @Mapping(target = "id",ignore = true)
    ProductImportHistory toProductImportHistory(ProductImportDTO productImportDTO, Product product);
}
