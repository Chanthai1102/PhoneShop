package com.chanthai.phoneshop.controller;

import com.chanthai.phoneshop.dto.report.ProductReportDTO;
import com.chanthai.phoneshop.dto.report.ExpenseReportDTO;
import com.chanthai.phoneshop.projection.ProductSold;
import com.chanthai.phoneshop.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("reports")
public class ReportController {
    private final ReportService reportService;
    @GetMapping("{startDate}/{endDate}")
    public ResponseEntity<?> productSold(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate")LocalDate startDate,@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate")LocalDate endDate){
        List<ProductSold> productSolds = reportService.getProductSold(startDate,endDate);
        return ResponseEntity.ok(productSolds);
    }
    @GetMapping("V2/{startDate}/{endDate}")
    public ResponseEntity<?> productSoldV2(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate")LocalDate startDate,@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate")LocalDate endDate){
        List<ProductReportDTO> productSolds = reportService.getProductReport(startDate,endDate);
        return ResponseEntity.ok(productSolds);
    }
    @GetMapping("expense/{startDate}/{endDate}")
    public ResponseEntity<?> productExpense(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("startDate")LocalDate startDate,@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("endDate")LocalDate endDate){
        List<ExpenseReportDTO> expenseReportDTOS = reportService.getExpenseReport(startDate,endDate);
        return ResponseEntity.ok(expenseReportDTOS);
    }
}
