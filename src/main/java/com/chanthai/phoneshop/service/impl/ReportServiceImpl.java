package com.chanthai.phoneshop.service.impl;

import com.chanthai.phoneshop.projection.ProductSold;
import com.chanthai.phoneshop.repository.SaleRepository;
import com.chanthai.phoneshop.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final SaleRepository saleRepository;
    @Override
    public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate,endDate);
    }
}
