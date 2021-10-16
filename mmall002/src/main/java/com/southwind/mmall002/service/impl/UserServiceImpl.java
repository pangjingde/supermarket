package com.southwind.mmall002.service.impl;

import com.southwind.mmall002.entity.User;
import com.southwind.mmall002.mapper.UserMapper;
import com.southwind.mmall002.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 景德
 * @since 2021-10-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
