package com.southwind.mmall002.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southwind.mmall002.entity.*;
import com.southwind.mmall002.mapper.*;
import com.southwind.mmall002.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 景德
 * @since 2021-10-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {


    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductMapper productMapper;

    public boolean save(Orders orders, User user,String address,String remark){

        //判断新地址是否在地址列表中
        if (orders.getUserAddress().equals("newAddress")){
            UserAddress userAddress = new UserAddress();
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddress.setIsdefault(1);
            userAddress.setUserId(user.getId());

            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("isdefault",1);
            wrapper.eq("user_id",user.getId());
            UserAddress oldDefault = userAddressMapper.selectOne(wrapper);
            oldDefault.setIsdefault(0);

            userAddressMapper.updateById(oldDefault);
            userAddressMapper.insert(userAddress);

            orders.setUserAddress(address);
        }

        orders.setUserId(user.getId());
        orders.setLoginName(user.getLoginName());
        String seriaNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<32;i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber =  result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        orders.setSerialnumber(seriaNumber);
        orderMapper.insert(orders);

        //查询购物车记录
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getId());
        List<Cart> cartList = cartMapper.selectList(wrapper);


        for (Cart cart : cartList) {


            //把Order的数据赋值给OrderDetail，
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart,orderDetail);
            orderDetail.setId(null);
            orderDetail.setOrderId(orders.getId());
            orderDetailMapper.insert(orderDetail);



            //扣库存

            Product product = productMapper.selectById(cart.getProductId());
            Integer stock=product.getStock()-orderDetail.getQuantity();
            product.setStock(stock);
            productMapper.updateById(product);


        }






        wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getId());
        cartMapper.delete(wrapper);



        return true;
    }
}
