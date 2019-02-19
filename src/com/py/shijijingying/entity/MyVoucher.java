package com.py.shijijingying.entity;
/*
 * 我的代金券
 */
public class MyVoucher {
    //id
	private Integer myVoucherId;
	//用户id
	private Integer userId;
	//代金券id
	private Integer voucherId;
	//标记是否使用
	private String voucherUse;
	//标记是否过期 
	private String voucherOverdue;
	public Integer getMyVoucherId() {
		return myVoucherId;
	}
	public void setMyVoucherId(Integer myVoucherId) {
		this.myVoucherId = myVoucherId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}
	public String getVoucherUse() {
		return voucherUse;
	}
	public void setVoucherUse(String voucherUse) {
		this.voucherUse = voucherUse;
	}
	public String getVoucherOverdue() {
		return voucherOverdue;
	}
	public void setVoucherOverdue(String voucherOverdue) {
		this.voucherOverdue = voucherOverdue;
	}
	
}
