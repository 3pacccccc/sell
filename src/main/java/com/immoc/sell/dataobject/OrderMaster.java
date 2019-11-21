package com.immoc.sell.dataobject;

import com.immoc.sell.enums.OrderStatusEnum;
import com.immoc.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    /*
     * 买家订单
     * */
    private String orderId; // 订单ID

    private String buyerName; // 买家名字

    private String buyerPhone; // 买家手机号

    private String buyerAddress; //买家地址

    private String buyerOpenId; // 买家微信openid

    private BigDecimal orderAmount; // 订单总金额

    private Integer orderStatus = OrderStatusEnum.NEW.getCode(); //订单状态 默认为新下单

    private Integer payStatus = PayStatusEnum.WAIT.getCode(); // 支付状态，默认为0未支付

    private Date createTime;  // 创建时间

    private Date updateTime; // 更新时间
}
