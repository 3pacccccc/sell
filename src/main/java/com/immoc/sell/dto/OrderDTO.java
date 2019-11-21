package com.immoc.sell.dto;

import com.immoc.sell.dataobject.OrderDetail;
import com.immoc.sell.enums.OrderStatusEnum;
import com.immoc.sell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private String orderId; // 订单ID

    private String buyerName; // 买家名字

    private String buyerPhone; // 买家手机号

    private String buyerAddress; //买家地址

    private String buyerOpenid; // 买家微信openid

    private BigDecimal orderAmount; // 订单总金额

    private Integer orderStatus; //订单状态 默认为新下单

    private Integer payStatus; // 支付状态，默认为0未支付

    private Date createTime;  // 创建时间

    private Date updateTime; // 更新时间

    List<OrderDetail> orderDetailList;
}
