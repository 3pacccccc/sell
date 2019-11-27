package com.immoc.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.immoc.sell.enums.ProductStatusEnum;
import com.immoc.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    private String productName;  // 名字

    private BigDecimal productPrice; // 单价

    private Integer productStock; // 库存

    private String productDescription; // 描述

    private String productIcon; // 小图

    private Integer productStatus = ProductStatusEnum.UP.getCode(); // 状态：0正常1下架;

    private Integer categoryType; // 类目编号

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
