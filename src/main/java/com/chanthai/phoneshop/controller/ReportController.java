package com.chanthai.phoneshop.controller;

import com.chanthai.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("reports")
public class ReportController {
    private final SaleService saleService;
    @GetMapping("{startDate}/{endDate}")
    public ResponseEntity<?> productSold(){
        return ResponseEntity.ok();
    }
}
