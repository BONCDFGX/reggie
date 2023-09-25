package com.bonc.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bonc.reggie.entity.Orders;
import com.bonc.reggie.entity.User;

public interface OrdersService extends IService<Orders>{

    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);

}
