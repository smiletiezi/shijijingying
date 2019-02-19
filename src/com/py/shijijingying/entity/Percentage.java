package com.py.shijijingying.entity;
/*
 * 提成策略
 */
public class Percentage {
//id
	private Integer percentageId;
	//产品类型名称
	private String name;
	//业务员提成
	private Double salesman;
	//合伙人提成
	private Double partner;
	//备注
	private String remark;
	public Integer getPercentageId() {
		return percentageId;
	}
	public void setPercentageId(Integer percentageId) {
		this.percentageId = percentageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSalesman() {
		return salesman;
	}
	public void setSalesman(Double salesman) {
		this.salesman = salesman;
	}
	public Double getPartner() {
		return partner;
	}
	public void setPartner(Double partner) {
		this.partner = partner;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
