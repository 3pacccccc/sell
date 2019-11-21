package com.immoc.sell.utils;

import java.util.Random;

public class KeyUtil {
    /*
    * 生成唯一主键*
    格式：时间+随机数
     */
    public static synchronized String genUniqueKey() { // 在多线程情况下仍有可能重复，所以加入synchronized
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000; // 生成六位随机数
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
