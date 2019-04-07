package com.zxa.controller;

import com.zxa.common.ApplicationConstant;
import com.zxa.common.ReturnEntity;
import com.zxa.entity.ShoppingCart;
import com.zxa.entity.User;
import com.zxa.interceptor.AuthenticationInterceptor;
import com.zxa.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/shoppingCart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    RedisTemplate redisTemplate;
    @Value("${session.pre:session-}")
    private final String SESSION_ID_PRE="session";
    private final String COOKIE_SESSION_ID="sessionId";

    /***
     * 我的购物车
     * @param request
     * @return
     */
    @RequestMapping("/myShoppingCart")
    public String myShoppingCart(HttpServletRequest request){
        String sessionId=getSessionId(request);
        if (StringUtils.isEmpty(sessionId)){
            return "redirect:/index/login";
        }
        User user= (User) redisTemplate.opsForValue().get(SESSION_ID_PRE+sessionId);
        return "redirect:/index/collection/"+user.getId();
    }


    @PostMapping("/updateQuantity")
    @ResponseBody
    public ReturnEntity updateQuantity(int id,int newQuantity){
        if (id<0 || newQuantity<=0){
            return ReturnEntity.error(ApplicationConstant.PARAMETER_ERROR);
        }
        shoppingCartService.updateShoppingCartQuantity(id,newQuantity);
        return ReturnEntity.success(newQuantity);
    }

    @PostMapping("/deleteShoppingCart")
    @ResponseBody
    public ReturnEntity deleteShoppingCart(int id) {
        if (id < 0) {
            return ReturnEntity.error(ApplicationConstant.PARAMETER_ERROR);
        }
        shoppingCartService.deleteShoppingCart(id);
        return ReturnEntity.success();
    }

    @RequestMapping("/add/{goodsId}")
    public String add(@PathVariable int goodsId,HttpServletRequest request){
        if (goodsId<=0){
            return "redirect:/index";
        }
        String sessionId=getSessionId(request);
        if (StringUtils.isEmpty(sessionId)){
            return "redirect:/index/login";
        }
        User user= (User) redisTemplate.opsForValue().get(SESSION_ID_PRE+sessionId);
        if (user==null){
            return "redirect:/index/login";
        }
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setUserId(user.getId());
        shoppingCart.setGoodsId(goodsId);
        shoppingCart.setQuantity(1);
        shoppingCart.setStatus(0);
        shoppingCart.setCreateTime(new Date());
        shoppingCart.setUpdateTime(new Date());
        shoppingCartService.insert(shoppingCart);
        return "redirect:/index/collection/"+user.getId();
    }

    private String  getSessionId(HttpServletRequest request){
        String sessionId = request.getHeader(AuthenticationInterceptor.sessionName);
        if (sessionId == null) {
            sessionId = request.getParameter(AuthenticationInterceptor.sessionName);
        }

        if (StringUtils.isEmpty(sessionId)) {
            Cookie[] cookies=request.getCookies();
            for (Cookie cookie:cookies){
                if (cookie.getName().equals(COOKIE_SESSION_ID)){
                    sessionId=cookie.getValue();
                }
            }
        }
        return sessionId;
    }
}
