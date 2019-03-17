package com.zxa.config;

import com.zxa.utils.RandomStringUtil;
import com.zxa.utils.RandomStringUtilFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomStringUtilConfig {

    @Bean(name = "emailCodeUtil")
    public RandomStringUtil emailCodeUtil(){
        return new RandomStringUtilFactory().numbers().capitalLetters().create();
    }

    @Bean(name = "sessionIdUtil")
    public RandomStringUtil sessionIdUtil(){
        return new RandomStringUtilFactory().numbers().letters().create();
    }
}
