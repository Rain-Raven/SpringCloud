package com.zxa.service;

import com.zxa.pojo.GoodsDetailDto;
import com.zxa.pojo.GoodsDto;
import com.zxa.pojo.NewGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsService {
    List<GoodsDto> getGoodsByPage(int categoryId, int pageNumber, int pageSize, int order);

    GoodsDetailDto getGoodsSingle(int id);

    List<String> getGoodsImages(int id);

    List<GoodsDto> getGoodsByKey(String key, int pageNumber,
                                  int pageSize, int order);

    List<NewGoods> getNewGoods(int start,int end);
}
