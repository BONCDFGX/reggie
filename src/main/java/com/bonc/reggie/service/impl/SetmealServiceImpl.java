package com.bonc.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.reggie.common.CustomException;
import com.bonc.reggie.dto.SetmealDto;
import com.bonc.reggie.entity.Setmeal;
import com.bonc.reggie.entity.SetmealDish;
import com.bonc.reggie.mapper.SetmealMapper;
import com.bonc.reggie.service.SetmealDishService;
import com.bonc.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    SetmealDishService setmealDishService;

    /**
     * 新增套餐，同时保存套餐和菜品的关联关系
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {

        // 保存套餐的基本信息，操作setmeal表，执行insert操作
        this.save(setmealDto);
        Long setmealId = setmealDto.getId(); // 套餐id

        // 保存套餐和菜品的关联关系，操作setmeal_dish表，执行insert操作
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealId);
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }


    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {

        // select count(*) from setmeal where id in (1,2,3) and status = 1
        // 查询套餐状态，确定是否可以删除
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.in(Setmeal::getId,ids);
        setmealLambdaQueryWrapper.eq(Setmeal::getStatus,1);

        int count = this.count(setmealLambdaQueryWrapper);

        // 如果不能删除，抛出一个业务异常
        if(count > 0){
            throw new CustomException("套餐正在售卖中，不能删除");
        }

        // 如果可以删除，先删除套餐表中的数据--setmeal
        this.removeByIds(ids);

        // 删除关系表中的数据--setmeal_dish
        // delete form setmeal_dish where setmeal_id in (1,2,3)
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(setmealDishLambdaQueryWrapper);

    }
}
