package com.zxa.service.impl;

import com.zxa.dao.GoodsDao;
import com.zxa.pojo.GoodsDetailDto;
import com.zxa.pojo.GoodsDto;
import com.zxa.pojo.NewGoods;
import com.zxa.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsDao goodsDao;
    @Override
    public List<GoodsDto> getGoodsByPage(int categoryId, int pageNumber, int pageSize, int order) {
        String orderString=getOrderString(order);
        return goodsDao.getGoodsByPage(categoryId,pageNumber,pageSize,orderString);
    }

    @Override
    public GoodsDetailDto getGoodsSingle(int id) {
        return goodsDao.getGoodsSingle(id);
    }

    @Override
    public List<String> getGoodsImages(int id) {
        return goodsDao.getGoodsImages(id);
    }

    @Override
    public List<GoodsDto> getGoodsByKey(String key, int pageNumber, int pageSize, int order) {
        String orderString=getOrderString(order);
        return goodsDao.getGoodsByKey(key,pageNumber,pageSize,orderString);
    }

    private String getOrderString(int order){
        String orderString="";
        switch (order){
            case 1:orderString="g.sales_quantity"; break;
            case 2:orderString="g.create_time";break;
            case 3:orderString="g.price";break;
            default:orderString="g.create_time";break;

        }
        return orderString;
    }

    @Override
    public List<NewGoods> getNewGoods(int start,int end) {
        return goodsDao.getNewGoods(start,end);
    }
}

