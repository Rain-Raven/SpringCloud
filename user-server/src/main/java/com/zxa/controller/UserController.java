package com.zxa.controller;

import com.zxa.common.ApplicationConstant;
import com.zxa.common.ReturnEntity;
import com.zxa.entity.User;
import com.zxa.interceptor.AuthenticationInterceptor;
import com.zxa.parameter.EmailAddress;
import com.zxa.parameter.LoginParameter;
import com.zxa.parameter.RegisterParameter;
import com.zxa.service.EmailService;
import com.zxa.service.UserService;
import com.zxa.task.EmailTask;
import com.zxa.utils.MD5Util;
import com.zxa.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    EmailService emailService;
    @Autowired
    RandomStringUtil emailCodeUtil;
    @Autowired
    RandomStringUtil sessionIdUtil;
    @Autowired
    Executor asyncServiceExecutor;


    @Value("${email.code.length:6}")
    private static final int EMAIL_CODE_LENGTH=6;
    @Value("${session.length:20}")
    private static final int SESSION_ID_LENGTH=20;
    @Value("${session.days:15}")
    private final int SESSION_ID_DAYS=15;
    @Value("${session.pre:session-}")
    private final String SESSION_ID_PRE="session";
    @Value("${email.code.time:10}")
    private final int EMAIL_CODE_VALID_TIME=10;
    @Value("${web.session.time:259200}")
    private final int WEB_SESSION_TIME=259200;
    @Value("${web.session.domain:localhost}")
    private final String WEB_SESSION_DOMAIN="localhost";
    @Value("${web.session.path:/}")
    private final String WEB_SESSION_PATH="/";
    private final String COOKIE_USER_NAME="userName";
    private final String COOKIE_SESSION_ID="sessionId";

    /**
     * 检查邮箱存在
     * @param email
     * @return
     */
    @RequestMapping(value = "/checkEmail",method = RequestMethod.POST)
    @ResponseBody
    public ReturnEntity checkEmail(@Valid EmailAddress email,BindingResult bindingResult){
        if (email==null || StringUtils.isEmpty(email.getEmail())){
            return ReturnEntity.error(ApplicationConstant.PARAMETER_ERROR);
        }
        if (bindingResult.hasErrors()){
            return ReturnEntity.error(ApplicationConstant.MAILBOX_FORMAT_IS_INCORRECT);
        }
        if (userService.emailIsExist(email.getEmail())){
            return ReturnEntity.error(ApplicationConstant.EMAIL_IS_EXIST);
        }
        return ReturnEntity.success();
    }

    /**
     * 发送邮箱验证码
     * @param email
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/emailAddressVerification")
    @ResponseBody
    public ReturnEntity emailAddressVerification(@Valid EmailAddress email,BindingResult bindingResult){
        if (email==null || StringUtils.isEmpty(email.getEmail())){
            return ReturnEntity.error(ApplicationConstant.PARAMETER_ERROR);
        }
        if (bindingResult.hasErrors()){
            return ReturnEntity.error(ApplicationConstant.MAILBOX_FORMAT_IS_INCORRECT);
        }
        String captcha=emailCodeUtil.getRandomString(EMAIL_CODE_LENGTH);
        asyncServiceExecutor.execute(new EmailTask(emailService,email.getEmail(),captcha));
        redisTemplate.opsForValue().set(email.getEmail(),captcha,EMAIL_CODE_VALID_TIME, TimeUnit.MINUTES);
        return ReturnEntity.success();
    }

    /**
     * 用户注册
     * @param registerParameter
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public ReturnEntity userRegister(@Valid RegisterParameter registerParameter, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ReturnEntity.error(ApplicationConstant.PARAMETER_ERROR);
        }
        if (userService.emailIsExist(registerParameter.getEmail())){
            return ReturnEntity.error(ApplicationConstant.EMAIL_IS_EXIST);
        }
        String captcha= (String) redisTemplate.opsForValue().get(registerParameter.getEmail());
      /* 暂时不进行校验
       if (StringUtils.isEmpty(captcha) || !captcha.equals(registerParameter.getCaptcha())){
            return ReturnEntity.error(ApplicationConstant.CAPTCHA_IS_INCORRECT);
        }
        */
        String encryptedPassword= MD5Util.crypt(registerParameter.getPassword());
        User user=new User();
        user.setUserName(registerParameter.getUserName());
        user.setEmail(registerParameter.getEmail());
        user.setPassword(encryptedPassword);
        userService.registerUser(user);
        return ReturnEntity.success(user);
    }

    /**
     * 用户登陆
     * @param loginParameter
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public ReturnEntity userLogin(@Valid LoginParameter loginParameter, BindingResult bindingResult, HttpServletResponse response){
        if (bindingResult.hasErrors()){
            return ReturnEntity.error(ApplicationConstant.PARAMETER_ERROR);
        }
        User user=userService.getUserByEmail(loginParameter.getEmail());
        if (user == null){
            return ReturnEntity.error(ApplicationConstant.EMAIL_IS_NOT_EXIST);
        }
        String encryptedPassword= MD5Util.crypt(loginParameter.getPassword());
        if (!user.getPassword().equals(encryptedPassword)){
            return ReturnEntity.error(ApplicationConstant.PASSWORD_IS_INCORRECT);
        }
        String sessionId=sessionIdUtil.getRandomString(SESSION_ID_LENGTH);
        if (!StringUtils.isEmpty(user.getSessionId())){
            redisTemplate.delete(SESSION_ID_PRE+user.getSessionId());
        }
        redisTemplate.opsForValue().set(SESSION_ID_PRE+sessionId,user,SESSION_ID_DAYS,TimeUnit.DAYS);
        user.setSessionId(sessionId);
        user.setUpdateTime(new Date());
        userService.update(user);
        /**设置cookie*/
        Cookie userCookie=new Cookie(COOKIE_USER_NAME,user.getUserName());
        userCookie.setMaxAge(WEB_SESSION_TIME);
        userCookie.setDomain(WEB_SESSION_DOMAIN);
        userCookie.setPath(WEB_SESSION_PATH);
        response.addCookie(userCookie);
        Cookie sessionCookie=new Cookie(COOKIE_SESSION_ID,sessionId);
        sessionCookie.setMaxAge(WEB_SESSION_TIME);
        sessionCookie.setDomain(WEB_SESSION_DOMAIN);
        sessionCookie.setPath(WEB_SESSION_PATH);
        response.addCookie(sessionCookie);
        return ReturnEntity.success();
    }

    /**
     * 用户退出登陆
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/userLoggedOut",method = RequestMethod.GET)
    public String userLoggedOut(HttpServletRequest request,HttpServletResponse response){
        /**设置cookie*/
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equals(COOKIE_USER_NAME) || cookie.getName().equals(COOKIE_SESSION_ID)){
                cookie.setPath(WEB_SESSION_PATH);
                cookie.setMaxAge(0);
                cookie.setDomain(WEB_SESSION_DOMAIN);
                response.addCookie(cookie);
            }
        }
        /*Cookie userCookie=new Cookie(COOKIE_USER_NAME,null);
        userCookie.setMaxAge(0);
        userCookie.setDomain(WEB_SESSION_DOMAIN);
        userCookie.setPath(WEB_SESSION_PATH);
        response.addCookie(userCookie);
        Cookie sessionCookie=new Cookie(COOKIE_SESSION_ID,null);
        sessionCookie.setMaxAge(0);
        sessionCookie.setDomain(WEB_SESSION_DOMAIN);
        sessionCookie.setPath(WEB_SESSION_PATH);
        response.addCookie(sessionCookie);*/
        return "redirect:/index";
    }
}
