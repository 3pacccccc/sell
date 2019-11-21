package com.immoc.sell.repository;

import com.immoc.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void findByOrderId() {
    }

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("1388321455");
        orderMaster.setBuyerAddress("cq");
        orderMaster.setBuyerOpenid("safw564");
        orderMaster.setOrderAmount(new BigDecimal("2.5"));

        OrderMaster orderMaster1 = repository.save(orderMaster);
        Assert.assertNotNull(orderMaster1);

    }

    @Test
    public void findByBuyerOpenid(){
        PageRequest request = new PageRequest(0, 1);
        Page<OrderMaster> result = repository.findByBuyerOpenid("safw564", request);
        Assert.assertNotEquals(0, result.getTotalElements());
    }

}
