package com.chanthai.phoneshop.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
@Data
public class PageDTO {
    private List<?> list;
    private PageinationDTO pagination;

    public PageDTO(Page<?> page){
        this.list = page.getContent();
        this.pagination = PageinationDTO.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getPageable().getPageSize())
                .pageNumber(page.getPageable().getPageNumber())
                .totalPage(page.getTotalPages())
                .totalElement(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .build();
    }
}
