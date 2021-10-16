package com.southwind.mmall002;

import com.southwind.mmall002.entity.ProductCategory;
import com.southwind.mmall002.mapper.ProductCategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Mmall002ApplicationTests {

    @Autowired
    private ProductCategoryMapper mapper;


    @Test
    void contextLoads() {


    }

    @Test
    void test() {
      mapper.selectList(null).forEach(System.out::println);

    }
}
