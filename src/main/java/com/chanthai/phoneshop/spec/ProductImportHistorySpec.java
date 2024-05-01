package com.chanthai.phoneshop.spec;

import com.chanthai.phoneshop.entity.ProductImportHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ProductImportHistorySpec implements Specification<ProductImportHistory> {
    private ProductImportHistoryFilter importHistoryFilter;
    @Override
    public Predicate toPredicate(Root<ProductImportHistory> importHistory, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        if (Objects.nonNull(importHistoryFilter.getStartDate())){
            Predicate startDate = criteriaBuilder.greaterThanOrEqualTo(importHistory.get("dateImport"), importHistoryFilter.getStartDate());
            predicateList.add(startDate);
        }
        if (Objects.nonNull(importHistoryFilter.getEndDate())){
            Predicate endDate = criteriaBuilder.lessThanOrEqualTo(importHistory.get("dateImport"),importHistoryFilter.getEndDate());
            predicateList.add(endDate);
        }
        Predicate predicate = criteriaBuilder.and(predicateList.toArray(Predicate[]::new));
        return predicate;
    }
}
