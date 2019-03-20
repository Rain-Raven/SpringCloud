package com.zxa.dao;

import com.zxa.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.   Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("select * from t_user where email=#{email}")
    User getUserByEmail(String email);

    @Insert("insert into t_user (user_name,password,email,session_id,update_time,create_time) " +
            "values (#{userName},#{password},#{email},#{sessionId},#{updateTime},#{createTime})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insert(User user);

    @Update("update t_user set user_name=#{userName},password=#{password},email=#{email}," +
            "session_id=#{sessionId},update_time=#{updateTime},create_time=#{createTime}" +
            "where id=#{id}")
    int update(User user);
}
