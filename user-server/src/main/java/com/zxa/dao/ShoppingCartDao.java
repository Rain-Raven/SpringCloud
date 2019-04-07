package com.zxa.dao;

import com.zxa.entity.ShoppingCart;
import com.zxa.pojo.UserShoppingCartItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartDao {
    @Select("SELECT  sc.id,sc.user_id,sc.goods_id,sc.quantity,gi.image,g.simple_describe,g.name,\n" +
            "g.price\n" +
            " FROM t_shopping_cart  AS sc\n" +
            "LEFT JOIN t_goods AS g ON g.id=sc.goods_id\n" +
            "LEFT JOIN t_goods_image AS gi ON gi.goods_id=g.id\n" +
            "WHERE sc.user_id=#{userId} AND sc.`status`=0 ANd gi.type=1\n" +
            "ORDER BY sc.create_time DESC")
    List<UserShoppingCartItem> getUserShoppingCartList(int userId);

    @Update("update t_shopping_cart SET quantity=#{newQuantity} WHERE id=#{id} ")
    int updateShoppingCartQuantity(@Param("id") int id, @Param("newQuantity") int newQuantity);

    @Update("update t_shopping_cart SET status=#{status} WHERE id=#{id} ")
    int updateShoppingCartStatus(@Param("id") int id,@Param("status") int status);

    @Insert("INSERT INTO t_shopping_cart(goods_id,user_id,`status`,quantity,create_time,update_time) " +
            "values (#{goodsId},#{userId},#{status},#{quantity},#{createTime},#{updateTime})")
    int insert(ShoppingCart shoppingCart);
}
