package com.zxa.controller;

import com.zxa.service.EmailService;
import com.zxa.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户认证请求控制器
 */
@Controller
public class CertificationController {
    @Autowired
    EmailService emailService;
    @Autowired
    RedisTemplate redisTemplate;

    private RandomStringUtil randomStringUtil=RandomStringUtil.builder().capitalLetters().numbers().create();

    @ResponseBody
    @RequestMapping(value = "/getEmailCaptcha",method = {RequestMethod.GET,RequestMethod.POST})
    public String getEmailCaptcha(String userName,Integer type){
        String captcha=randomStringUtil.getRandomString(6);
        redisTemplate.opsForValue().set(userName,captcha);
        emailService.bindingEmail("13609733372@163.com",captcha);
        return String.valueOf(type);
    }
}
