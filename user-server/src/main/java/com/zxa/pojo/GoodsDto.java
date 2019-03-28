package com.zxa.pojo;

import lombok.Data;

@Data
public class GoodsDto {
    private int id;
    private String image;
    private float price;
    private String name;
    private String simpleDescribe;
}
