package com.immoc.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

@Data
public class ProductForm {

    private String productId;

    @NotEmpty(message = "姓名必填")
    private String productName;  // 名字

    @NotEmpty(message = "单价必填")
    private BigDecimal productPrice; // 单价

    @NotEmpty(message = "库存必填")
    private Integer productStock; // 库存

    private String productDescription; // 描述

    private String productIcon; // 小图

    @NotEmpty(message = "类目必填")
    private Integer categoryType; // 类目编号

}
