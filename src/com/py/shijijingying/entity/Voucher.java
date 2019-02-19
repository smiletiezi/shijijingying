package com.py.shijijingying.entity;
/*
 * 代金券
 */
public class Voucher {
	//id
	private Integer voucherId;
	//代金券名字
	private String voucherName;
	//代金券金额
	private Integer voucherPar;
	//代金券使用起点（满多少使用）
	private String voucherMore;
	//有效期
	private String voucherTime;
	//创建时间
	private String voucherCreateTime;
	//到期时间
	private String voucherExpiry;
	//判断是否在使用（0未使用。1使用中
	private String voucherUse;
	//代金券是否可用于抽奖（1可以，0不可以）
	private String voucherType;
	//代金券是否可用于积分兑换（1可以，0不可以）
	private String present;
	//兑换所需积分
	private Integer integration;
	
	public Integer getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}
	public String getVoucherName() {
		return voucherName;
	}
	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}
	public Integer getVoucherPar() {
		return voucherPar;
	}
	public void setVoucherPar(Integer voucherPar) {
		this.voucherPar = voucherPar;
	}
	public String getVoucherMore() {
		return voucherMore;
	}
	public void setVoucherMore(String voucherMore) {
		this.voucherMore = voucherMore;
	}
	public String getVoucherTime() {
		return voucherTime;
	}
	public void setVoucherTime(String voucherTime) {
		this.voucherTime = voucherTime;
	}
	public String getVoucherCreateTime() {
		return voucherCreateTime;
	}
	public void setVoucherCreateTime(String voucherCreateTime) {
		this.voucherCreateTime = voucherCreateTime;
	}
	public String getVoucherExpiry() {
		return voucherExpiry;
	}
	public void setVoucherExpiry(String voucherExpiry) {
		this.voucherExpiry = voucherExpiry;
	}
	public String getVoucherUse() {
		return voucherUse;
	}
	public void setVoucherUse(String voucherUse) {
		this.voucherUse = voucherUse;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getPresent() {
		return present;
	}
	public void setPresent(String present) {
		this.present = present;
	}
	public Integer getIntegration() {
		return integration;
	}
	public void setIntegration(Integer integration) {
		this.integration = integration;
	}
	
}
