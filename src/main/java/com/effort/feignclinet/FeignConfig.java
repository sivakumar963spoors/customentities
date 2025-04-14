package com.effort.feignclinet;

import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Bearer YOUR_TOKEN_HERE");
    }
}
