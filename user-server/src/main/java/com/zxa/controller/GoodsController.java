package com.zxa.controller;

import com.zxa.common.ApplicationConstant;
import com.zxa.common.ReturnEntity;
import com.zxa.pojo.GoodsDto;
import com.zxa.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @ResponseBody
    @PostMapping("getGoodsByPage")
    public ReturnEntity getGoodsByPage(int categoryId, @RequestParam(defaultValue = "1") int pageNumber,
                                       @RequestParam(defaultValue = "9") int pageSize, @RequestParam(defaultValue = "1") int order){
        if (categoryId<=0){
            return ReturnEntity.error(ApplicationConstant.PARAMETER_ERROR);
        }
        List<GoodsDto> goodsDtoList=goodsService.getGoodsByPage(categoryId,(pageNumber-1)*pageSize,pageSize,order);
        return ReturnEntity.success(goodsDtoList);
    }

    @ResponseBody
    @PostMapping("getGoodImage")
    public ReturnEntity getGoodImage(int id){
        if (id<=0){
            return ReturnEntity.error(ApplicationConstant.PARAMETER_ERROR);
        }
        List<String> images=goodsService.getGoodsImages(id);
        return ReturnEntity.success(images);
    }

    @ResponseBody
    @PostMapping("getGoodsByKey")
    public ReturnEntity getGoodsByKey(String key, @RequestParam(defaultValue = "1") int pageNumber,
                                       @RequestParam(defaultValue = "9") int pageSize, @RequestParam(defaultValue = "1") int order){
        if (StringUtils.isEmpty(key)){
            return ReturnEntity.error(ApplicationConstant.PARAMETER_ERROR);
        }
        List<GoodsDto> goodsDtoList=goodsService.getGoodsByKey(key,(pageNumber-1)*pageSize,pageSize,order);
        return ReturnEntity.success(goodsDtoList);
    }
}
