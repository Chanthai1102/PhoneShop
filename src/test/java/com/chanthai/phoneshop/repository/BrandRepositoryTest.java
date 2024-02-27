package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BrandRepositoryTest {
    @Autowired
    private BrandRepository brandRepository;
    @Test
    public void testFindByNameLike(){
        //give
        Brand brand = new Brand();
        brand.setName("Apple");
        brandRepository.save(brand);
        //when
        List<Brand> brands = brandRepository.findByNameLike("%A%");
        //then
        assertEquals(1,brands.size());
        assertEquals("Apple",brands.get(0).getName());
        assertEquals(1,brands.get(0).getId());
    }
}
