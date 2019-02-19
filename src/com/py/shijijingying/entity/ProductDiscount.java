package com.py.shijijingying.entity;

public class ProductDiscount {
	//折扣Id
	private Integer discountId;
	//折扣Id
	private Integer discountSum;
	//折扣名称
	private String discountName;
	//折扣系数
	private Double discountRemark;
	//折扣类型
	private String discountType;
	public Integer getDiscountId() {
		return discountId;
	}
	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}
	
	public Integer getDiscountSum() {
		return discountSum;
	}
	public void setDiscountSum(Integer discountSum) {
		this.discountSum = discountSum;
	}
	public String getDiscountName() {
		return discountName;
	}
	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}
	public Double getDiscountRemark() {
		return discountRemark;
	}
	public void setDiscountRemark(Double discountRemark) {
		this.discountRemark = discountRemark;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

}
