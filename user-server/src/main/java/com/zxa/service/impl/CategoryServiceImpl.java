package com.zxa.service.impl;

import com.zxa.dao.CategoryDao;
import com.zxa.pojo.CategoryPojo;
import com.zxa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    CategoryDao categoryDao;

    private final String CATEGORY_KEY="CATEGORY";
    @Override
    public List<CategoryPojo> getCategory() {
        List<CategoryPojo> categoryPojoList= (List<CategoryPojo>) redisTemplate.opsForValue().get(CATEGORY_KEY);
        if (categoryPojoList == null){
            categoryPojoList=categoryDao.getCategory();
            redisTemplate.opsForValue().set(CATEGORY_KEY,categoryPojoList,30, TimeUnit.MINUTES);
        }
        return categoryPojoList;
    }
}
