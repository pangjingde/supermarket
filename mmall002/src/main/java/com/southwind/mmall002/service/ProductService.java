package com.southwind.mmall002.service;

import com.southwind.mmall002.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 景德
 * @since 2021-10-06
 */
public interface ProductService extends IService<Product> {
    List<Product> findProduct(String type,Integer categoryId);

    List<Product> findTwoProduct(Integer categoryId);
}
