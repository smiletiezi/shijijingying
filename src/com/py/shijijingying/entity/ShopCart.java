package com.py.shijijingying.entity;

/*
 * 购物车管理
 */
public class ShopCart {
	//购物车Id
	private Integer shopCartId;
	//用户Id
	private Integer userId;
	//产品Id
	private Integer productId;
	//购物车code<用来标记是否已支付>
	private String shopCode;
	//备注
	private String shopRemark;
	//创建时间
	private String shopCreateTime;
	//修改时间
	private String shopUpdateTime;
	//产品数量
	private Integer shopSum;
	public Integer getShopCartId() {
		return shopCartId;
	}
	public void setShopCartId(Integer shopCartId) {
		this.shopCartId = shopCartId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getShopCode() {
		return shopCode;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public String getShopRemark() {
		return shopRemark;
	}
	public void setShopRemark(String shopRemark) {
		this.shopRemark = shopRemark;
	}
	public String getShopCreateTime() {
		return shopCreateTime;
	}
	public void setShopCreateTime(String shopCreateTime) {
		this.shopCreateTime = shopCreateTime;
	}
	public String getShopUpdateTime() {
		return shopUpdateTime;
	}
	public void setShopUpdateTime(String shopUpdateTime) {
		this.shopUpdateTime = shopUpdateTime;
	}
	public Integer getShopSum() {
		return shopSum;
	}
	public void setShopSum(Integer shopSum) {
		this.shopSum = shopSum;
	}
	
}
