package com.bonc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.reggie.entity.Dish;
import com.bonc.reggie.entity.DishFlavor;
import com.bonc.reggie.mapper.DishFlavorMapper;
import com.bonc.reggie.mapper.DishMapper;
import com.bonc.reggie.service.DishFlavorService;
import com.bonc.reggie.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
