package com.southwind.mmall002.service;

import com.southwind.mmall002.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.southwind.mmall002.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 景德
 * @since 2021-10-06
 */
public interface OrderService extends IService<Orders> {
    public boolean save(Orders orders, User user,String address,String remark);
}
