package com.southwind.mmall002.service.impl;

import com.southwind.mmall002.entity.Product;
import com.southwind.mmall002.mapper.ProductMapper;
import com.southwind.mmall002.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 景德
 * @since 2021-10-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private  ProductMapper productMapper;


    @Override
    public List<Product> findProduct(String type,Integer categoryId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("categorylevel"+type+"_id",categoryId);
        return productMapper.selectByMap(map);

    }

    @Override
    public List<Product> findTwoProduct(Integer categoryId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("categoryleveltwo_id",categoryId);
        return productMapper.selectByMap(map);

    }
}
