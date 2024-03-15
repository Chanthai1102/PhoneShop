package com.chanthai.phoneshop.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class ProductImportDTO {
    @NotNull(message = "Product id can't be null")
    private Long productId;

    @Min(value = 1, message = "Import Unit Must Be Greater than 0")
    private Integer importUnit;

    @DecimalMin(value = "0.00001",message = "Price Must Be Greater than 0")
    private BigDecimal importPrice;

    @NotNull(message = "Import Date Can't be Null")
    private LocalDate importDate;
}
