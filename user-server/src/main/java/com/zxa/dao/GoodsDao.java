package com.zxa.dao;

import com.zxa.pojo.GoodsDetailDto;
import com.zxa.pojo.GoodsDto;
import com.zxa.pojo.NewGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDao {
    @Select("SELECT g.id,g.`name`,gi.image,g.price,g.simple_describe FROM t_goods  AS g\n" +
            "LEFT JOIN t_goods_image AS gi ON gi.goods_id=g.id\n" +
            "WHERE gi.type=1 AND category_id=#{categoryId}\n" +
            "ORDER BY ${order} DESC\n" +
            "LIMIT #{pageNumber},#{pageSize}")
    List<GoodsDto> getGoodsByPage(@Param("categoryId") int categoryId, @Param("pageNumber") int pageNumber,
                                  @Param("pageSize") int pageSize, @Param("order") String order);


    @Select("SELECT g.id,g.name,g.complex_describe,g.price,g.inventory-g.sales_quantity AS inventory,\n" +
            "c.id AS categoryId,c.`name` AS categoryName\n" +
            "FROM t_goods AS g\n" +
            "INNER JOIN t_secondary_category AS c ON c.id=g.category_id\n" +
            "WHERE g.id=#{id}")
    GoodsDetailDto getGoodsSingle(int id);

    @Select("SELECT image FROM t_goods_image\n" +
            "WHERE goods_id=#{id}")
    List<String> getGoodsImages(int id);


    @Select("SELECT g.id,g.`name`,gi.image,g.price,g.simple_describe FROM t_goods  AS g\n" +
            "LEFT JOIN t_goods_image AS gi ON gi.goods_id=g.id\n" +
            "WHERE gi.type=1 AND name like CONCAT(#{key},'%') \n" +
            "ORDER BY #{order} DESC\n" +
            "LIMIT #{pageNumber},#{pageSize}")
    List<GoodsDto> getGoodsByKey(@Param("key") String key, @Param("pageNumber") int pageNumber,
                                  @Param("pageSize") int pageSize, @Param("order") String order);

    @Select("SELECT g.id,g.`name`,gi.image,g.complex_describe,g.simple_describe FROM t_goods  AS g\n" +
            "LEFT JOIN t_goods_image AS gi ON gi.goods_id=g.id\n" +
            "WHERE gi.type=1 \n" +
            "ORDER BY g.create_time DESC\n" +
            "LIMIT #{start},#{end}")
    List<NewGoods> getNewGoods(@Param("start") int start,@Param("end") int end);

    @Update("update t_goods set sales_quantity=sales_quantity+#{add} where id=#{goodsId}")
    int addSaleQuantity(@Param("goodsId") int goodsId, @Param("add") int add);
}
