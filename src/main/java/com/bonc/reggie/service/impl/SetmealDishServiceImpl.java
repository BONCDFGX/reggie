package com.bonc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.reggie.entity.Setmeal;
import com.bonc.reggie.entity.SetmealDish;
import com.bonc.reggie.mapper.SetmealDishMapper;
import com.bonc.reggie.mapper.SetmealMapper;
import com.bonc.reggie.service.SetmealDishService;
import com.bonc.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
}
