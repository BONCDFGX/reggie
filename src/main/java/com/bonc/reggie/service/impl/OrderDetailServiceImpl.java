package com.bonc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.reggie.entity.OrderDetail;
import com.bonc.reggie.entity.User;
import com.bonc.reggie.mapper.OrderDetailMapper;
import com.bonc.reggie.mapper.UserMapper;
import com.bonc.reggie.service.OrderDetailService;
import com.bonc.reggie.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
