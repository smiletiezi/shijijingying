package com.py.shijijingying.entity;

public class OrderProduct {
	//id
		private Integer orderProductId;
		//订单编号
		private String orderNumber;
		//支付标识
		private String type;
		//产品名称
		private  String productName;
		//产品图片
		private String img;
		//购买数量
		private String  num;
		//产品类别
		private String remark;
		//产品价格
		private Double price;
		//产品价格
		private Double salePrice;
		//产品规格
		private String size;
		//用户id
		private Integer userId;
		//用户名称
		private String userName;
		//该产品提成类别名称
		private String percentage;
		//合伙人提成
		private Double partner;
		//业务员提成
		private Double salesman;
		//订单创建日期
		private String createDate;
		
		
		public Integer getOrderProductId() {
			return orderProductId;
		}
		public void setOrderProductId(Integer orderProductId) {
			this.orderProductId = orderProductId;
		}
		public String getOrderNumber() {
			return orderNumber;
		}
		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public String getNum() {
			return num;
		}
		public void setNum(String num) {
			this.num = num;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		
		public Double getSalePrice() {
			return salePrice;
		}
		public void setSalePrice(Double salePrice) {
			this.salePrice = salePrice;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		public String getPercentage() {
			return percentage;
		}
		public void setPercentage(String percentage) {
			this.percentage = percentage;
		}
		public Double getPartner() {
			return partner;
		}
		public void setPartner(Double partner) {
			this.partner = partner;
		}
		public Double getSalesman() {
			return salesman;
		}
		public void setSalesman(Double salesman) {
			this.salesman = salesman;
		}
		public String getCreateDate() {
			return createDate;
		}
		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		
		
}
