package com.bonc.reggie.controller;

import com.bonc.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传与下载
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonContorller {

    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        // file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除

        // 获取原始文件名s
        String originalFileName = file.getOriginalFilename();
        // 获取文件后缀(带.)
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));

        // 使用UUID重新生成文件名，防止文件名重复造成文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;

        // 创建一个目录文件
        File dir = new File(basePath);
        // 判断目录是否存在
        if(!dir.exists()){
            // 目录不存在，需要创建
            dir.mkdirs();
        }

        try {
            // 将临时文件转存到指定位置
            file.transferTo(new File(basePath+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info(file.toString());
        return R.success(fileName);
    }

}
