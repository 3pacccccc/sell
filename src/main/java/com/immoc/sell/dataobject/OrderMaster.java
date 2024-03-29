package com.immoc.sell.dataobject;

import com.immoc.sell.enums.OrderStatusEnum;
import com.immoc.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    /*
     * 买家订单
     * */
    @Id
    private String orderId; // 订单ID

    private String buyerName; // 买家名字

    private String buyerPhone; // 买家手机号

    private String buyerAddress; //买家地址

    private String buyerOpenid; // 买家微信openid

    private BigDecimal orderAmount; // 订单总金额

    private Integer orderStatus = OrderStatusEnum.NEW.getCode(); //订单状态 默认为新下单

    private Integer payStatus = PayStatusEnum.WAIT.getCode(); // 支付状态，默认为0未支付

    private Date createTime;  // 创建时间

    private Date updateTime; // 更新时间

//    @Transient // 在orm到数据库得时候会忽略此字段
//    private List<OrderDetail> orderDetailList;
}
