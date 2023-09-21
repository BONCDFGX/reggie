package com.bonc.test;

import org.junit.jupiter.api.Test;

public class UploadFileTest {

    @Test
    public void testSuffix(){
        String fileName = "sajcci.jpg";
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);
    }

}
