package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.dto.SaleDTO;
import com.chanthai.phoneshop.entity.Sale;

public interface SaleService {
    void Sale(SaleDTO saleDTO);
    Sale getById(Long saleId);
    void cancelSale(Long saleId);
}
