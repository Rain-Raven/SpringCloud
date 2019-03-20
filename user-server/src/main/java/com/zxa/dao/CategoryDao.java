package com.zxa.dao;

import com.zxa.pojo.CategoryPojo;
import com.zxa.pojo.SecondaryCategoryPojo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao{

    @Select("select id,name,sort from t_category where status=1 order by sort desc")
    @Results(value = {
            @Result(property = "secondaryCategoryPojoList",column = "id",many = @Many(select = "com.zxa.dao.CategoryDao.getSecondaryCategory"))
            })
    List<CategoryPojo> getCategory();

    @Select("select id,name,sort from t_secondary_category where category_id=#{id} and status=1 order by sort desc")
    List<SecondaryCategoryPojo> getSecondaryCategory(int id);
}
