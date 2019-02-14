package com.zxa.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zxa.fallback.EmailServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "email",fallback = EmailServiceFallback.class)
public interface EmailService {

    @RequestMapping(value = "/bindingEmail", method =RequestMethod.POST)
    boolean bindingEmail(@RequestParam("toEmail") String toEmail,@RequestParam("captcha") String captcha);
}
