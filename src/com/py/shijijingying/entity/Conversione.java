package com.py.shijijingying.entity;
/*
 * 消费金额与积分兑率，与抽奖券兑率
 */
public class Conversione {
//id
	private Integer conversionId;
	//与积分兑率
	private Double voucher;
	//与抽奖券兑率
	private Double draw;
	//中奖概率
	private Double probability;
	//备注
	private String remark;
	public Integer getConversionId() {
		return conversionId;
	}
	public void setConversionId(Integer conversionId) {
		this.conversionId = conversionId;
	}
	public Double getVoucher() {
		return voucher;
	}
	public void setVoucher(Double voucher) {
		this.voucher = voucher;
	}
	public Double getDraw() {
		return draw;
	}
	public void setDraw(Double draw) {
		this.draw = draw;
	}
	
	public Double getProbability() {
		return probability;
	}
	public void setProbability(Double probability) {
		this.probability = probability;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
