package com.tistory.fullth.post.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix= "tistory.auto-read-me")
@Data
public class Properties {
    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    private String code;
    private String token;
    private String blogName;
}
