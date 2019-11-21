package com.immoc.sell.enums;

import lombok.Getter;

@Getter // 可以不用写get方法
public enum ProductStatusEnum {
    // 商品状态枚举
    UP(0, "在架"),
    DOWN(1, "下架");
    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
