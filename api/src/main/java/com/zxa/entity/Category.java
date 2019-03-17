package com.zxa.entity;


import lombok.Data;

/**
 * category实体类
 * 
 * @author 
 *
 */
@Data
public class Category {
	/***/
	private Integer id; 
	/**类别名*/
	private String name; 
	/**类别排序*/
	private Integer sort; 
	/**类别状态 0禁用，1启用*/
	private Integer status; 
	/***/
	private java.time.Instant createTime; 
	/***/
	private java.time.Instant updateTime;
}
