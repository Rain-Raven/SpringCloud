package com.zxa.service.impl;

import com.zxa.dao.GoodsDao;
import com.zxa.dao.ShoppingCartDao;
import com.zxa.entity.ShoppingCart;
import com.zxa.pojo.UserShoppingCartItem;
import com.zxa.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ShoppingCartDao shoppingCartDao;
    @Autowired
    GoodsDao goodsDao;
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

    @Override
    public int updateSaleQuantity(int goodsId, int add) {
        return goodsDao.addSaleQuantity(goodsId,add);
    }

    @Override
    public int getOrder(int userId) {
        List<UserShoppingCartItem> shoppingCartItems=this.getUserShoppingCartList(userId);
        for (UserShoppingCartItem item:shoppingCartItems){
            this.updateSaleQuantity(item.getGoodsId(),item.getQuantity());
            shoppingCartDao.updateShoppingCartStatus(item.getId(),1);
        }
        return shoppingCartItems.size();
    }
}
