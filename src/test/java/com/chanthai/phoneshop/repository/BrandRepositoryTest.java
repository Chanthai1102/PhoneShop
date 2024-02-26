package com.chanthai.phoneshop.repository;

import com.chanthai.phoneshop.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static org.junit.Assert.assertEquals;

import java.util.List;

@Component
public class BrandRepositoryTest {
    @Autowired
    private BrandRepository brandRepository;
    public void testFindByNameLike(){
        //give
        Brand brand = new Brand();
        brand.setName("Apple");
        brandRepository.save(brand);
        //when
        List<Brand> findByNameLike = brandRepository.findByNameLike("Ä");
        //then
        assertEquals(1,findByNameLike.size());
    }
}
