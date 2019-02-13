package com.zxa.controller;

import com.zxa.service.MailService;
import com.zxa.service.TemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


@RestController
public class EmailController {
    @Autowired
    private TemplateService templateService;
    @Autowired
    private MailService mailService;
    @Value(value = "${mail.from.address}")
    private String fromEmail;
    @Value(value = "${info.appName}")
    private String appName;
    @Value(value = "${mail.binding.time}")
    private Integer bindingTime;

    @RequestMapping(value = "/bindingEmail", method = {RequestMethod.GET, RequestMethod.POST})
    public boolean bindingEmail(String toEmail, String captcha){
        Map<String, Object> params = new HashMap<>();
        params.put("captcha", captcha);
        params.put("type",appName);
        params.put("time",bindingTime);
        String html = templateService.render("bindingEmail.html", params);
        mailService.mimeMail(fromEmail, toEmail, "绑定邮箱验证", html);
        return true;
    }
}

