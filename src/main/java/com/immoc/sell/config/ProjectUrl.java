package com.immoc.sell.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "productUrl")
@Component
public class ProjectUrl {

    public String wechatMpAuthorize;

    public String wechatOpenAuthorize;

    public String sell;
}
