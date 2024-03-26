package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.dto.report.ExpenseReportDTO;
import com.chanthai.phoneshop.entity.Product;
import com.chanthai.phoneshop.entity.ProductImportHistory;
import com.chanthai.phoneshop.repository.ProductImportHistoryRepository;
import com.chanthai.phoneshop.repository.ProductRepository;
import com.chanthai.phoneshop.repository.SaleDetailRepository;
import com.chanthai.phoneshop.repository.SaleRepository;
import com.chanthai.phoneshop.service.impl.ReportServiceImpl;
import com.chanthai.phoneshop.service.util.ReportTestHelper;
import com.chanthai.phoneshop.spec.ProductImportHistorySpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
    @Mock
    private SaleRepository saleRepository;
    @Mock
    private SaleDetailRepository saleDetailRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductImportHistoryRepository productImportHistoryRepository;

    private ReportService reportService;

    @BeforeEach
    public void setup(){
        reportService = new ReportServiceImpl(saleRepository,saleDetailRepository,productRepository,productImportHistoryRepository);
    }

    @Test
    public void testGetExpenseReport(){
        //given
        List<ProductImportHistory> importHistories = ReportTestHelper.getProductImportHistories();
        List<Product> products = ReportTestHelper.getProducts();
        //when
        when(productImportHistoryRepository.findAll(Mockito.any(ProductImportHistorySpec.class)))
                .thenReturn(importHistories);
        when(productRepository.findAllById(anySet()))
                .thenReturn(products);
        List<ExpenseReportDTO> expenseReports = reportService.getExpenseReport(LocalDate.now().minusMonths(0),LocalDate.now());
        //then
        assertEquals(1,expenseReports.size());
    }
}
