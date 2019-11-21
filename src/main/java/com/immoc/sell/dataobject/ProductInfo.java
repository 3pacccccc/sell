package com.immoc.sell.dataobject;

import lombok.Data;
import org.dom4j.dom.DOMText;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;

    private String productName;  // 名字

    private BigDecimal productPrice; // 单价

    private Integer productStock; // 库存

    private String productDescription; // 描述

    private String productIcon; // 小图

    private Integer productStatus; // 状态：0正常1下架;

    private Integer categoryType; // 类目编号
}
