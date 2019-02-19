package com.py.shijijingying.entity;
/*
 * 积分明细
 */
public class IntegralDetail {
//id
	private Integer detailId;
	//用户Id
	private Integer userId;
	//加或者减（1加，0减）
	private String detailType;
	//积分数
	private Integer personal;
	//改变时间
	private String date;
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
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
