package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.Sale;
import com.chanthai.phoneshop.projection.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long>{
    @Query(value = "select p.product_id as productId, p.product_name productName, sum(sd.unit) unit, sum(sd.unit * sd.sold_amount) totalAmount\r\n"
            + "from tb_sale_details sd \r\n"
            + "inner join tb_sales s on sd.sale_id = s.sale_id\r\n"
            + "inner join tb_product p on p.product_id = sd.product_id\r\n"
            + "where date(s.sold_date) >= :startDate and date(s.sold_date) <= :endDate\r\n"
            + "group by p.product_id, p.product_name\r\n"
            + "", nativeQuery = true)
    List<ProductSold> findProductSold(LocalDate startDate, LocalDate endDate);
}
