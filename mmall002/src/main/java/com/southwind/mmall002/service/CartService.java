package com.southwind.mmall002.service;

import com.southwind.mmall002.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.mmall002.productCategoryVO.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 景德
 * @since 2021-10-06
 */
public interface CartService extends IService<Cart> {

     List<CartVO> findAllCartVOByUserId(Integer id);






}


