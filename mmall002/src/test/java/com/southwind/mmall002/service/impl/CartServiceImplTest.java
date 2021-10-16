package com.southwind.mmall002.service.impl;

import com.southwind.mmall002.productCategoryVO.CartVO;
import com.southwind.mmall002.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceImplTest {


    @Autowired
    private CartService cartService;

    @Test
    void  test(){
        List<CartVO> cart = cartService.findAllCartVOByUserId(28);
        System.out.println(cart);
    }
}