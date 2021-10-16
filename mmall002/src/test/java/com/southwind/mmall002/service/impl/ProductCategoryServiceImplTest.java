package com.southwind.mmall002.service.impl;

import com.southwind.mmall002.productCategoryVO.CartVO;
import com.southwind.mmall002.service.CartService;
import com.southwind.mmall002.service.ProductCategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService service;

    @Test
    void getAllProductCategoryVO() {
        service.getAllProductCategoryVO();
    }
}