package com.zxa.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ShoppingCart {
    private int id;
    private int goodsId;
    private int userId;
    private int quantity;
    private int status;
    private Date updateTime;
    private Date createTime;
}
