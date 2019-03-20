package com.zxa.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryPojo {
    private int id;
    private String name;
    private int sort;
    private List<SecondaryCategoryPojo> secondaryCategoryPojoList;
}
