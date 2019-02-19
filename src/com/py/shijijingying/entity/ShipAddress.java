package com.py.shijijingying.entity;
/*
 * 收货地址管理
 */
public class ShipAddress {
	//收获Id
	private Integer shipId;
	//用户Id
	private Integer userId;
	//收货人
	private String  shipPerson;
	//收获电话
	private String  shipPhone;
	//收货地址
	private String  shipAddress;
	//判断是否设为默认地址
	private String  shipType;
	
	public Integer getShipId() {
		return shipId;
	}
	public void setShipId(Integer shipId) {
		this.shipId = shipId;
	}
	public String getShipPerson() {
		return shipPerson;
	}
	public void setShipPerson(String shipPerson) {
		this.shipPerson = shipPerson;
	}
	public String getShipPhone() {
		return shipPhone;
	}
	public void setShipPhone(String shipPhone) {
		this.shipPhone = shipPhone;
	}
	public String getShipAddress() {
		return shipAddress;
	}
	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	public String getShipType() {
		return shipType;
	}
	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
