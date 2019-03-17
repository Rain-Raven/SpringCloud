package com.zxa.task;

import com.zxa.service.EmailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@AllArgsConstructor
public class EmailTask implements Runnable{
    private EmailService emailService;
    private String email;
    private String captcha;
    private static final Logger log= LoggerFactory.getLogger(EmailTask.class);
    @Override
    public void run() {
        log.warn("开始邮件测试");
        boolean b=emailService.bindingEmail(email,captcha);
        if (!b){
            log.warn("邮件发送到"+email+"失败!");
        }
    }
}
