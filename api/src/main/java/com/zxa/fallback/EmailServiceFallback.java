package com.zxa.fallback;

import com.zxa.service.EmailService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class EmailServiceFallback implements EmailService {
    @Override
    public boolean bindingEmail(@RequestParam(value = "toEmail") String toEmail,@RequestParam(value = "captcha") String captcha) {
        return false;
    }
}
