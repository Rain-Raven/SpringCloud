package com.zxa.service;

import com.zxa.entity.ShoppingCart;
import com.zxa.pojo.UserShoppingCartItem;

import java.util.List;

public interface ShoppingCartService {

    List<UserShoppingCartItem> getUserShoppingCartList(int userId);

    int updateShoppingCartQuantity(int id,int newQuantity);

    int deleteShoppingCart(int id);

    int insert(ShoppingCart shoppingCart);
}
