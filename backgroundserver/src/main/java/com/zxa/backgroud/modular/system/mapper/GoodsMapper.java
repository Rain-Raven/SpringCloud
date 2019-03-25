package com.zxa.backgroud.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxa.backgroud.modular.system.entity.Category;
import com.zxa.backgroud.modular.system.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsMapper extends BaseMapper<Goods> {
    Page<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition, @Param("id") String id);

    List<Category> getAll();
}
