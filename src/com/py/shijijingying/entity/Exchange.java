package com.py.shijijingying.entity;
/*
 * 积分兑换记录
 */
public class Exchange {
 //id
	private Integer exchangeId;
	//用户id
	private Integer userId;
	//兑换名称
	private String product;
	//消耗积分
	private Integer  personal;
	//兑换时间
	private String date;
	
	public Integer getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Integer getPersonal() {
		return personal;
	}
	public void setPersonal(Integer personal) {
		this.personal = personal;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
