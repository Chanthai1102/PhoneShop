package com.chanthai.phoneshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO {
    @NotEmpty
    private List<ProductSoldDTO> products;

    private LocalDateTime saleDate;
}
