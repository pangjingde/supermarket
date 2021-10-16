package com.southwind.mmall002.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southwind.mmall002.entity.Cart;
import com.southwind.mmall002.entity.Product;
import com.southwind.mmall002.mapper.CartMapper;
import com.southwind.mmall002.mapper.ProductMapper;
import com.southwind.mmall002.productCategoryVO.CartVO;
import com.southwind.mmall002.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {


    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<CartVO> findAllCartVOByUserId(Integer id ) {

        List<CartVO> cartVOList = new ArrayList<>();


        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",id);
        List<Cart> cartList = cartMapper.selectList(wrapper);

        for (Cart cart : cartList) {
            CartVO cartVO = new CartVO();
            Product product = productMapper.selectById(cart.getProductId());
            BeanUtils.copyProperties(product, cartVO);
            BeanUtils.copyProperties(cart, cartVO);
            cartVOList.add(cartVO);
        }
        return cartVOList;
    }


}
