package com.py.shijijingying.utils;

import java.util.Date;

public class SMSBean {
	
	private Integer id;
	private String phonenum;//发送手机
	private String value;//发送的消息
	private Date sendDate;//发送日期
	
	public int getId() {
		return id; 
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public SMSBean(Integer id, String phonenum, String value, Date sendDate) {
		super();
		this.id = id;
		this.phonenum = phonenum;
		this.value = value;
		this.sendDate = sendDate;
	}
	public SMSBean() {
		super();
	}

}

