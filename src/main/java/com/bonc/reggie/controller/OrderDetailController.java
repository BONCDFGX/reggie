package com.bonc.reggie.controller;

import com.bonc.reggie.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderDetailController {

    @Autowired
    OrdersService ordersService;

}
