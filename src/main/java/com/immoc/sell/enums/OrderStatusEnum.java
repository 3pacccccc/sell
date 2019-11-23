package com.immoc.sell.enums;

import com.immoc.sell.utils.EnumUtil;
import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0, "新下单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消");

    private Integer code;

    private String msg;

    OrderStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
