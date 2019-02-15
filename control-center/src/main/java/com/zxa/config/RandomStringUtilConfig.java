package com.zxa.config;

import com.zxa.utils.RandomStringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomStringUtilConfig {

    @Bean
    public RandomStringUtil emailCaptchaUtil(){
        return RandomStringUtil.builder().capitalLetters().numbers().create();
    }
}
