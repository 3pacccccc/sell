package com.immoc.sell.repository;

import com.immoc.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void findByBuyerOpenid() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("111111");
        Assert.assertNotEquals(0, orderDetailList.size());

    }

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345678ds29");
        orderDetail.setOrderId("111111333");
        orderDetail.setProductIcon("http://456416a.jpg");
        orderDetail.setProductId("1111155555");
        orderDetail.setProductName("双皮奶");
        orderDetail.setProductPrice(new BigDecimal("7.5"));
        orderDetail.setProductQuantity(2);

        OrderDetail orderDetail1 = repository.save(orderDetail);
        Assert.assertNotNull(orderDetail1);
    }
}
