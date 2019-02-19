package com.py.shijijingying.entity;

public class ExcleOrder {
    //订单id
	private Integer orderId;
	//订单编号
	private String orderNumber;
	//订单金额
	private Double money;
	//创建时间
	private String createTime;
	//购买单位/购买人
	private String userName;
	//商品名称
	private String productName;
	//商品规格
	private String size;
	//购买数量
	private String num;
	//收货地址
	private String shipAddress;
	//收货人
	private String shipPerson;
	//收货电话
	private String shipPhone;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getShipAddress() {
		return shipAddress;
	}
	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
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
	
	
}
