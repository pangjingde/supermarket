package com.southwind.mmall002.service;

import com.southwind.mmall002.mapper.UserAddressMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserAddressServiceTest {

    @Autowired
    private UserAddressMapper userAddressMapper;
    @Test
    public void test(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id",10);
        System.out.println(userAddressMapper.selectByMap(map));
    }
}