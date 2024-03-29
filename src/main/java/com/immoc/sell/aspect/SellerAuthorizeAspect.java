package com.immoc.sell.aspect;

import com.immoc.sell.constant.CookieConstant;
import com.immoc.sell.constant.RedisConstant;
import com.immoc.sell.exception.SellerAuthorizeException;
import com.immoc.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.immoc.sell.controller.Seller*.*(..))" +
            "&& !execution(public * com.immoc.sell.controller.SellerUserController.*(..))")
    //表示除了SellerUserController下的其他seller开头的controller都需要经过verify函数
    public void verify() {
    }

    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登陆校验】cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        // 去redis里面查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getName()));
        if (StringUtils.isEmpty(tokenValue)){
            log.warn("【登陆校验】redis查不到token");
            throw new SellerAuthorizeException();
        }


    }
}
