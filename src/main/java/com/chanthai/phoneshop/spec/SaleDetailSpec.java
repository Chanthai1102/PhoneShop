package com.chanthai.phoneshop.spec;

import com.chanthai.phoneshop.entity.Sale;
import com.chanthai.phoneshop.entity.SaleDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class SaleDetailSpec implements Specification<SaleDetail> {
    private SaleDetailFilter detailFilter;
    @Override
    public Predicate toPredicate(Root<SaleDetail> saleDetail, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        Join<SaleDetail, Sale> sale = saleDetail.join("sale");
        if (Objects.nonNull(detailFilter.getStartDate())){
            criteriaBuilder.greaterThanOrEqualTo(sale.get("soldDate"), detailFilter.getStartDate());
        }
        if (Objects.nonNull(detailFilter.getEndDate())){
            criteriaBuilder.lessThanOrEqualTo(sale.get("soldDate"),detailFilter.getEndDate());
        }
        Predicate predicate = criteriaBuilder.and(predicateList.toArray(Predicate[]::new));
        return predicate;
    }
}
