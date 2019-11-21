package com.immoc.sell.service.impl;

import com.immoc.sell.dataobject.OrderDetail;
import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private final String BUYER_OPENID = "1FDQ243";

    private final String orderId = "1574344398641664231";

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("小马");
        orderDTO.setBuyerAddress("cq");
        orderDTO.setBuyerPhone("1542184721");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        // 购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("1234568");
        orderDetail1.setProductQuantity(2);
        orderDetailList.add(orderDetail1);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单 result=】", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {

        OrderDTO orderDTO = orderService.findOne(orderId);
        log.info("查询订单 result={}", orderDTO);
        Assert.assertEquals(orderId, orderDTO.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0, 1);
        Page<OrderDTO> orderDTOS =  orderService.findList(BUYER_OPENID, pageRequest);
        Assert.assertNotEquals(0, orderDTOS.getTotalElements());
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}