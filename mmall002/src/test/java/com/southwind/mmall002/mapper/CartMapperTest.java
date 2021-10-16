package com.southwind.mmall002.mapper;

import com.southwind.mmall002.entity.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartMapperTest {


    @Autowired
    private  CartMapper cartMapper;

    @Test
    void update(){
        cartMapper.findByUidAndPid(28,733);
    }

    @Test
    void  insert(){
        Cart cart = new Cart();
        cart.setId(200);
        cart.setQuantity(111);
        cart.setUserId(29);
        cart.setProductId(222);
          cartMapper.insert(cart);
    }
}