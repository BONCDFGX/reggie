package com.bonc.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {


    /**
     * 插入操作，自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
<<<<<<< HEAD
        metaObject.setValue("createUser", new Long(1));
        metaObject.setValue("updateUser", new Long(1));
=======
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
>>>>>>> origin/master
    }

    /**
     * 更新操作，自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]");
        log.info(metaObject.toString());
<<<<<<< HEAD
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateUser", new Long(1));
=======
        metaObject.setValue("createTime", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
>>>>>>> origin/master
    }
}
