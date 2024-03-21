package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.dto.ProductSoldDTO;
import com.chanthai.phoneshop.dto.SaleDTO;
import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.entity.Sale;
import com.chanthai.phoneshop.entity.SaleDetail;
import com.chanthai.phoneshop.exception.APIException;
import com.chanthai.phoneshop.exception.ResourceNotFoundException;
import com.chanthai.phoneshop.repository.ProductRepository;
import com.chanthai.phoneshop.repository.SaleDetailRepository;
import com.chanthai.phoneshop.repository.SaleRepository;
import com.chanthai.phoneshop.service.ProductService;
import com.chanthai.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    @Override
    public void Sale(SaleDTO saleDTO) {
        List<Long> productIds = saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId)
                .toList();
        // validate product
        productIds.forEach(productService::getByID);

        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));


        // validate stock
        saleDTO.getProducts()
                .forEach(ps ->{
                    Product product = productMap.get(ps.getProductId());
                    if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
                        throw new APIException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock".formatted(product.getName()));
                    }
                });

        // Sale
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);

        // Sale Detail
        saleDTO.getProducts().forEach(ps ->{
            Product product = productMap.get(ps.getProductId());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(product.getSalePrice());
            saleDetail.setProduct(product);
            saleDetail.setSale(sale);
            saleDetail.setUnit(ps.getNumberOfUnit());
            saleDetailRepository.save(saleDetail);

            // cut stock
            Integer availableUnit =  product.getAvailableUnit() - ps.getNumberOfUnit();
            product.setAvailableUnit(availableUnit);
            productRepository.save(product);
        });

    }

    @Override
    public Sale getById(Long saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale",saleId));
    }

    @Override
    public void cancelSale(Long saleId) {
        //Update Sale Status
        Sale sale = getById(saleId);
        sale.setActive(false);
        saleRepository.save(sale);

        //Update stock
        List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(saleId);
        List<Long> productIds = saleDetails.stream()
                        .map(sd -> sd.getProduct().getId())
                        .toList();
        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                        .collect(Collectors.toMap(Product::getId, Function.identity()));
        saleDetails.forEach(sd -> {
            Product product = sd.getProduct();

        });
    }

}
