package com.chanthai.phoneshop.service;

import com.chanthai.phoneshop.entity.Brand;
import com.chanthai.phoneshop.exception.ResourceNotFoundException;
import com.chanthai.phoneshop.repository.BrandRepository;
import com.chanthai.phoneshop.service.impl.BrandServiceImpl;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @Mock
    private BrandRepository brandRepository;

    private  BrandService brandService;

    @BeforeEach
    public void setUp(){
        brandService = new BrandServiceImpl(brandRepository);
    }

//    @Test
//    public void testCreate(){
//        //given
//        Brand brand = new Brand();
//        brand.setName("Thai");
//        brand.setId(1);
//        //When
//        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
//        Brand brandReturn  = brandService.create(new Brand());
//        //Then
//        assertEquals(1,brandReturn.getId());
//        assertEquals("Thai",brandReturn.getName());
//
//
//    }
    @Test
    public void testCreate(){
        //given
        Brand brand = new Brand();
        brand.setName("Thai");
        //when
        brandService.create(brand);
        //then
        verify(brandRepository,times(1)).save(brand);
    }
    @Test
    public void testGetById(){
        //given
        Brand brand = new Brand();
        brand.setName("Thai");
        brand.setId(1);
        //when
        when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
        Brand brandReturn = brandService.getById(1);
        //then
        assertEquals(1,brandReturn.getId());
        assertEquals("Thai",brandReturn.getName());
    }

    @Test
    public void testGetByIdThrow(){
        //given

        //when
        when(brandRepository.findById(2)).thenReturn(Optional.empty());
        //brandService.getById(2);
        assertThatThrownBy(() -> brandService.getById(2))
                .isInstanceOf(ResourceNotFoundException.class);
        //then
    }
}
