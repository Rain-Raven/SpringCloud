package com.zxa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import java.util.Map;

@Service
public class TemplateService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    public String render(String template, Map<String, Object> params) {
        Context context = new Context(LocaleContextHolder.getLocale());
        context.setVariables(params);
        return templateEngine.process(template, context);
    }
}