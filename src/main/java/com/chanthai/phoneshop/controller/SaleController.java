package com.chanthai.phoneshop.controller;

import com.chanthai.phoneshop.dto.SaleDTO;
import com.chanthai.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("sales")
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SaleDTO saleDTO){
        saleService.Sale(saleDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("{saleId}/cancel")
    public ResponseEntity<?> cancelSale(){
        return ResponseEntity.ok().build();
    }
}
