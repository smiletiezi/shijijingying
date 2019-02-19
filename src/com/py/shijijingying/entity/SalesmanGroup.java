package com.py.shijijingying.entity;
/*
 * 业务人员团队
 */
public class SalesmanGroup {  
	//合伙人id
	private Integer userId;
	//合伙人姓名
	private String name;
	//客户总消费
	private Double money;
	//合伙人预计收益
	private Double cash;
	//我的收益
	private Double fund;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Double getFund() {
		return fund;
	}
	public void setFund(Double fund) {
		this.fund = fund;
	}
	
}
