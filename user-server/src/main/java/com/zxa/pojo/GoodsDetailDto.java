package com.zxa.pojo;

import lombok.Data;

@Data
public class GoodsDetailDto {
    private int id;
    private String name;
    private float price;
    private int inventory;
    private String complexDescribe;
    private int categoryId;
    private String categoryName;
}
