package com.southwind.mmall002.service.impl;

import com.southwind.mmall002.entity.User;

import com.southwind.mmall002.service.ProductCategoryService;
import com.southwind.mmall002.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServicelTest {


    @Autowired
    private UserService userService;

    @Test
    void test(){
        User user=new User();
        user.setLoginName("jq");
        user.setUserName("123");
        user.setPassword("123");
        user.setGender(1);
        System.out.println(userService.save(user));
    }

    @Test
    void test2(){
        User user = userService.getById(28);
        user.setPassword("123");
        System.out.println(userService.updateById(user));
    }
}