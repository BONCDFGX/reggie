package com.bonc.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bonc.reggie.entity.AddressBook;
import com.bonc.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
