package com.zxa.fallback;

import com.zxa.service.EmailService;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceFallback implements EmailService {
    @Override
    public boolean bindingEmail(String toEmail,String captcha) {
        return false;
    }
}
