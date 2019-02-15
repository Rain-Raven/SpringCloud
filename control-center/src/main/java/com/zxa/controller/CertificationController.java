package com.zxa.controller;

import com.zxa.service.EmailService;
import com.zxa.utils.RandomStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log= LoggerFactory.getLogger(this.getClass());

    private RandomStringUtil randomStringUtil=RandomStringUtil.builder().capitalLetters().numbers().create();

    @ResponseBody
    @RequestMapping(value = "/getEmailCaptcha",method = {RequestMethod.GET,RequestMethod.POST})
    public boolean getEmailCaptcha(String userName,Integer type){
        String captcha=randomStringUtil.getRandomString(6);
        redisTemplate.opsForValue().set(userName,captcha);
        boolean result=emailService.bindingEmail("13609733372@163.com",captcha);
        log.info(emailService.toString());
        return result;
    }
}