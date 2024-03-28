package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.dto.report.ProductReportDTO;
import com.chanthai.phoneshop.dto.report.ExpenseReportDTO;
import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.entity.ProductImportHistory;
import com.chanthai.phoneshop.entity.SaleDetail;
import com.chanthai.phoneshop.projection.ProductSold;
import com.chanthai.phoneshop.repository.ProductImportHistoryRepository;
import com.chanthai.phoneshop.repository.ProductRepository;
import com.chanthai.phoneshop.repository.SaleDetailRepository;
import com.chanthai.phoneshop.repository.SaleRepository;
import com.chanthai.phoneshop.service.ReportService;
import com.chanthai.phoneshop.spec.ProductImportHistoryFilter;
import com.chanthai.phoneshop.spec.ProductImportHistorySpec;
import com.chanthai.phoneshop.spec.SaleDetailFilter;
import com.chanthai.phoneshop.spec.SaleDetailSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;

    @Override
    public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate,endDate);
    }

    @Override
    public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
        List<ProductReportDTO> list = new ArrayList<>();
        SaleDetailFilter detailFilter = new SaleDetailFilter();
        detailFilter.setStartDate(startDate);
        detailFilter.setEndDate(endDate);
        Specification<SaleDetail> spec = new SaleDetailSpec(detailFilter);
        List<SaleDetail> saleDetails = saleDetailRepository.findAll(spec);

        List<Long> productIds = saleDetails.stream()
                .map(saleDetail -> saleDetail.getProduct().getId())
                .toList();

        Map<Long,Product> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        Map<Product,List<SaleDetail>> saleDetailMap = saleDetails.stream()
                .collect(Collectors.groupingBy(SaleDetail::getProduct));

        for (var entry: saleDetailMap.entrySet()){
            Product product = productMap.get(entry.getKey().getId());
            List<SaleDetail> saleDetailList =  entry.getValue();
            //total unit
            Integer unit = saleDetailList.stream().map(SaleDetail::getUnit)
                    .reduce(0,(a,b) -> a+b);

            double totalAmount = saleDetailList.stream()
                    .mapToDouble(saleDetail -> saleDetail.getUnit() * saleDetail.getAmount().doubleValue())
                    .sum();
//                    .map(saleDetail -> saleDetail.getUnit() * saleDetail.getAmount().doubleValue())
//                    .reduce(0.0,(a,b) -> a+b);
            ProductReportDTO reportDTO = new ProductReportDTO();
            reportDTO.setProductId(product.getId());
            reportDTO.setProductName(product.getName());
            reportDTO.setUnit(unit);
            reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
            list.add(reportDTO);
        }
        return list;
    }

    @Override
    public List<ExpenseReportDTO> getExpenseReport(LocalDate startDate, LocalDate endDate) {
        ProductImportHistoryFilter importHistoryFilter = new ProductImportHistoryFilter();
        importHistoryFilter.setStartDate(startDate);
        importHistoryFilter.setEndDate(endDate);

        ProductImportHistorySpec spec = new ProductImportHistorySpec(importHistoryFilter);
        List<ProductImportHistory> importHistories = productImportHistoryRepository.findAll(spec);

        Set<Long> productIds = importHistories.stream()
                .map(his -> his.getProduct().getId())
                .collect(Collectors.toSet());

        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, p-> p));
        //Map<Product,List<ProductImportHistory>>
        Map<Product,List<ProductImportHistory>> importMap = importHistories.stream()
                .collect(Collectors.groupingBy(ProductImportHistory::getProduct));

        var expenseReportDTOs = new ArrayList<ExpenseReportDTO>();

        for (var entry : importMap.entrySet()){
            Product product = productMap.get(entry.getKey().getId());
            List<ProductImportHistory> importProduct = entry.getValue();
            int totalUnit = importProduct.stream()
                    .mapToInt(ProductImportHistory::getImportUnit)
                    .sum();
            double totalAmount = importProduct.stream()
                    .mapToDouble(pi -> pi.getImportUnit() * pi.getPricePerUnit().doubleValue())
                    .sum();
            var expenseReportDTO = new ExpenseReportDTO();
            expenseReportDTO.setProductId(product.getId());
            expenseReportDTO.setProductName(product.getName());
            expenseReportDTO.setTotalUnit(totalUnit);
            expenseReportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
            expenseReportDTOs.add(expenseReportDTO);
        }
        return expenseReportDTOs;
    }
}
