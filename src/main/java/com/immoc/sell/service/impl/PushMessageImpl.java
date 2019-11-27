package com.immoc.sell.service.impl;

import com.immoc.sell.dto.OrderDTO;
import com.immoc.sell.service.PushMessageService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PushMessageImpl implements PushMessageService {
    @Override
    public void orderStatus(OrderDTO orderDTO) {

        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId("aaa");
        templateMessage.setToUser(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "亲，请记得收货"),
                new WxMpTemplateData("keyword1", "微信点餐"),  // 商家名称
                new WxMpTemplateData("keyword2", "1238194768"), // 商家电话
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),// 订单号
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMsg()), // 状态
                new WxMpTemplateData("keyword5", "￥" + orderDTO.getOrderAmount()), // 总价
                new WxMpTemplateData("remark", "欢迎再次光临") // 备注
        );

    }
}
