package com.zxa.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("email")
public interface EmailService {
    @RequestMapping(value = "/bindingEmail", method =RequestMethod.POST)
    boolean bindingEmail(@RequestParam("toEmail") String toEmail,@RequestParam("captcha") String captcha);
}
