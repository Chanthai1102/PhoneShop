package com.chanthai.phoneshop.spec;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleDetailFilter {
    private LocalDate startDate;
    private LocalDate endDate;
}
