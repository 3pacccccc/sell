package com.immoc.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法。。。");
        log.info("code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx0be1e1549c033f06&secret=761f5d4128fac7f21e83298383eaecf9&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }
}

//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0be1e1549c033f06&redirect_uri=http://maruimin.natapp1.cc/sell/weixin/auth&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect

//https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx0be1e1549c033f06&secret=761f5d4128fac7f21e83298383eaecf9&code=CODE&grant_type=authorization_code

//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0be1e1549c033f06&redirect_uri=http://maruimin.natapp1.cc/sell/weixin/auth&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect