package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.entity.Brand;
import com.chanthai.phoneshop.repository.BrandRepository;
import com.chanthai.phoneshop.service.impl.BrandServiceImpl;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @Mock
    private BrandRepository brandRepository;

    private  BrandService brandService;

    @BeforeEach
    public void setUp(){
        brandService = new BrandServiceImpl(brandRepository);
    }

    @Test
    public void testCreate(){
        //given
        Brand brand = new Brand();
        brand.setName("Thai");
        brand.setId(1);
        //When
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
        Brand brandReturn  = brandService.create(new Brand());
        //Then
        assertEquals(1,brandReturn.getId());
        assertEquals("Thai",brandReturn.getName());


    }
}
