package com.bonc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.reggie.entity.ShoppingCart;
import com.bonc.reggie.entity.User;
import com.bonc.reggie.mapper.ShoppingCartMapper;
import com.bonc.reggie.mapper.UserMapper;
import com.bonc.reggie.service.ShoppingCartService;
import com.bonc.reggie.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
