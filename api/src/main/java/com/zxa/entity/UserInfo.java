package com.zxa.entity;


import lombok.Data;

/**
 * user_info实体类
 * 
 * @author 
 *
 */
@Data
public class UserInfo {
	/***/
	private Integer id; 
	/**用户ID*/
	private Integer userId; 
	/**性别 0未知，1男性，2女性*/
	private Integer sex; 
	/**生日*/
	private java.time.Instant birthday; 
	/**昵称*/
	private String nickName; 
	/**个性签名*/
	private String personalizedSignature; 
	/**手机号*/
	private String phoneNumber; 
	/***/
	private java.time.Instant createTime; 
	/***/
	private java.time.Instant updateTime;
	
}
