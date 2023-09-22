package com.bonc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.reggie.dto.DishDto;
import com.bonc.reggie.entity.Dish;
import com.bonc.reggie.entity.DishFlavor;
import com.bonc.reggie.mapper.DishMapper;
import com.bonc.reggie.service.DishFlavorService;
import com.bonc.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品，同时保存对应的口味数据
     * @param dishDto
     */
    @Override
    @Transactional // 事务控制
    public void saveWithFlavor(DishDto dishDto) {

        // 保存菜品的基础信息到菜品表dish
        this.save(dishDto);

        Long dishId = dishDto.getId(); // 菜品id

        // 菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        // 保存菜品口味数据到菜品表dish_flavor
        dishFlavorService.saveBatch(flavors); // saveBatch可以批量保存数组

    }
}
