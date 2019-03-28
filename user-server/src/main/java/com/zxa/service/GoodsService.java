package com.zxa.service;

import com.zxa.pojo.GoodsDetailDto;
import com.zxa.pojo.GoodsDto;

import java.util.List;

public interface GoodsService {
    List<GoodsDto> getGoodsByPage(int categoryId, int pageNumber, int pageSize, int order);

    GoodsDetailDto getGoodsSingle(int id);

    List<String> getGoodsImages(int id);
}
