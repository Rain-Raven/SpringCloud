package com.zxa.interceptor;

import com.google.gson.Gson;
import com.zxa.common.ApplicationConstant;
import com.zxa.common.ReturnEntity;
import com.zxa.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${session.pre:session}")
    private final String SESSION_ID_PRE="session";
    //不需要登录的接口控制
    private Set<Pattern> freeset = new HashSet<>();
    //session参数名字
    public static final String sessionName = "sessionId";
    public static final String USER_ATTRIBUTE="LoginUser";
    @Autowired
    private Gson standardGson=new Gson();

    private static final Logger log= LoggerFactory.getLogger(AuthenticationInterceptor.class);

    {
        freeset.add(Pattern.compile("/images/*"));
        freeset.add(Pattern.compile("/js/*"));
        freeset.add(Pattern.compile("/css/*"));
        freeset.add(Pattern.compile("/user/*"));
        freeset.add(Pattern.compile("/index/*"));
        freeset.add(Pattern.compile("/error"));
        freeset.add(Pattern.compile("/goods"));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();
        // 先检查是否为不需要签名的界面
        for (Pattern pattern : freeset) {
            if (pattern.matcher(uri).find()) {
                return true;
            }
        }
        String sessionId = request.getHeader(sessionName);
        if (sessionId == null) {
            sessionId = request.getParameter(sessionName);
        }
        if (sessionId == null) {
            ReturnEntity returnEntity=ReturnEntity.error(ApplicationConstant.PLEASE_LOGIN);
            responseJsonMessage(response, standardGson.toJson(returnEntity));
            return false;
        }
        User user = validateRedisLoginUser(sessionId);
        if (user == null) {
            responseJsonMessage(response, standardGson.toJson(ReturnEntity.error(ApplicationConstant.PLEASE_LOGIN)));
            return false;
        }
        request.setAttribute(USER_ATTRIBUTE, user);
        return true;
    }

    /**
     * 获取登录的用户
     *
     * @param sessionId
     * @return
     */
    public User validateRedisLoginUser(String sessionId) {
        if (StringUtils.isEmpty(sessionId)) {
            return null;
        }
        User user= (User) redisTemplate.opsForValue().get(sessionId);
        return user;
    }

    private void responseJsonMessage(HttpServletResponse response, String msg) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(msg);
        } catch (IOException e) {
            log.error(e.getCause().toString());
        }
    }
}
