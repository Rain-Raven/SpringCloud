package com.zxa.service.impl;

import com.zxa.dao.ShoppingCartDao;
import com.zxa.entity.ShoppingCart;
import com.zxa.pojo.UserShoppingCartItem;
import com.zxa.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ShoppingCartDao shoppingCartDao;
    @Override
    public List<UserShoppingCartItem> getUserShoppingCartList(int userId) {
        return shoppingCartDao.getUserShoppingCartList(userId);
    }

    @Override
    public int updateShoppingCartQuantity(int id, int newQuantity) {
        return shoppingCartDao.updateShoppingCartQuantity(id,newQuantity);
    }

    @Override
    public int deleteShoppingCart(int id) {
        return shoppingCartDao.updateShoppingCartStatus(id,2);
    }

    @Override
    public int insert(ShoppingCart shoppingCart) {
        return shoppingCartDao.insert(shoppingCart);
    }
}
