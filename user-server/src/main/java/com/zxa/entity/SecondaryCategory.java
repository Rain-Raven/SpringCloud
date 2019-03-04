package com.zxa.entity;


/**
 * secondary_category实体类
 * 
 * @author 
 *
 */
public class SecondaryCategory {
	/***/
	private Integer id; 
	/**一级分类ID*/
	private Integer categoryId; 
	/**类别名*/
	private String name; 
	/**排序 由大到小*/
	private Integer sort; 
	/**类别状态 0禁用，1启用*/
	private Integer status; 
	/***/
	private java.time.Instant createTime; 
	/***/
	private java.time.Instant updateTime;
}
