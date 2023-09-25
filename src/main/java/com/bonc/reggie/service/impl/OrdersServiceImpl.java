package com.bonc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.reggie.entity.Orders;
import com.bonc.reggie.entity.User;
import com.bonc.reggie.mapper.OrdersMapper;
import com.bonc.reggie.mapper.UserMapper;
import com.bonc.reggie.service.OrdersService;
import com.bonc.reggie.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
}
