package com.zxa.entity;

import lombok.Data;

import java.util.Date;

/**
 * user实体类
 * 
 * @author 
 *
 */
@Data
public class User {
	/***/
	private Integer id; 
	/***/
	private String userName; 
	/***/
	private String password; 
	/***/
	private String email; 
	/***/
	private java.util.Date updateTime;
	/***/
	private java.util.Date createTime;
	/***/
	private String sessionId;

	public User(){
		super();
		Date now=new Date();
		this.createTime=now;
		this.updateTime=now;
	}
}
