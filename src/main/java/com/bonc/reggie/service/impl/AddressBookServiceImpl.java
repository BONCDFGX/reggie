package com.bonc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.reggie.entity.AddressBook;
import com.bonc.reggie.entity.User;
import com.bonc.reggie.mapper.AddressBookMapper;
import com.bonc.reggie.mapper.UserMapper;
import com.bonc.reggie.service.AddressBookService;
import com.bonc.reggie.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
