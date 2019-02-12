package com.zxa.config;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

//@Configuration
@ConditionalOnClass(Gson.class)
//@ConditionalOnMissingClass(name = "com.fasterxml.jackson.core.JsonGenerator")
@ConditionalOnBean(Gson.class)
public  class GsonHttpMessageConverterConfiguration {

    /*@Bean
    @ConditionalOnMissingBean*/
    public GsonHttpMessageConverter gsonHttpMessageConverter(Gson standardGson) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(standardGson);
        return converter;
    }

}
