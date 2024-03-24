package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.dto.ProductReportDTO;
import com.chanthai.phoneshop.projection.ProductSold;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
    List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate);
}
