package com.zxa.entity;


import lombok.Data;

/**
 * shipping_address实体类
 * 
 * @author 
 *
 */
@Data
public class ShippingAddress {
	/***/
	private Integer id; 
	/**用户ID*/
	private Integer userId; 
	/**收货地址*/
	private String address; 
	/**收货人手机*/
	private String phoneNumber; 
	/**收货人姓名*/
	private String name; 
	/***/
	private java.time.Instant createTime; 
	/***/
	private java.time.Instant updateTime;
}
