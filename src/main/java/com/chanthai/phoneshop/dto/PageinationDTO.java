package com.chanthai.phoneshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageinationDTO {
    private int pageSize;
    private int pageNumber;
    private int totalPage;
    private long totalElement;
    private long numberOfElements;
    private boolean first;
    private boolean last;
    private boolean empty;
}
