package com.immoc.sell.controller;


import com.immoc.sell.dataobject.SellerInfo;
import com.immoc.sell.enums.ResultEnum;
import com.immoc.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid, Map<String, Object> map) {
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
//        if (sellerInfo == null) {
//            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
//            map.put("url", "/sell/seller/order/list");
//            return new ModelAndView("common/error");
//        }
        redisTemplate.opsForValue().set("abc", "666");
//        return new ModelAndView("common/error");
        return null;
    }
}
