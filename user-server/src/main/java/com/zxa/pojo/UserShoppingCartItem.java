package com.zxa.pojo;

import lombok.Data;

@Data
public class UserShoppingCartItem {
    private int id;
    private int userId;
    private int goodsId;
    private int quantity;
    private String image;
    private String simpleDescribe;
    private String name;
    private float price;
}
