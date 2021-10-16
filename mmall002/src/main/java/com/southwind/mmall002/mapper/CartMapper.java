package com.southwind.mmall002.mapper;

import com.southwind.mmall002.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 景德
 * @since 2021-10-06
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {




    /**
     * 修改购物车数据中商品的数量
     * @param id 购物车数据的id
     * @param quantity 新的数量
     */
    Integer updateNumById(@Param("id") Integer id,
                           @Param("quantity") Integer quantity);



    /**
     * 根据用户id和商品id查询购物车中的数据
     * @param userId 用户id
     * @param productId 商品id
     * @return 匹配的购物车数据，如果该用户的购物车中并没有该商品，则返回null
     */
   Cart findByUidAndPid(
            @Param("userId") Integer userId,
            @Param("productId") Integer productId);



}

