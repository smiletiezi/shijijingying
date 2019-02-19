package com.py.shijijingying.entity;

public class Product {
	//产品Id
		private Integer productId;
		//产品名称
		private String productName;
		//产品促销价格
		private Double productSalePrice;
		//产品原价
		private Double productPrice;
		//产品规格
		private String productSize;
		//产品营销方式<前端下拉列表框写死，自营，经销>
		private String productSaleType;
		//产品品牌
		private String productBrand;
		//是否促销<前端标记框，1 or 0>
		private String productSale;
		//产品描述
		private String productRemark;
		//产品类型
		private Integer productType;
		//产品首页图片
		private String productImg;
		//产品图片
		private String productImgOne;
		//产品详情图片
		private String productImgTwo;
		//产品视频
		private String productVideo;
		//产品归属<前端下拉列表框写死，只有两家公司的产品>
		private String productBelong;
		//产品归属（1总公司，0子公司）
		private String belong;
		//产品折扣率、优惠政策
		private String productDiscount;
		//产品提成策略
		private String percentage;
		//产品起售数量
		private Integer salesVolume;
		//是否标记为推荐<前端标记框，1 or 0>
		private String recommend;
		//是否可作为兑奖礼品<前端标记框，1 or 0>
		private String  present;
		//兑奖积分点数
		private Integer integration;
		//是否可作为抽奖产品（1可以。0不可以）
		private String draw;
		// 折扣详情
		private String discountName;
		
		public Integer getProductId() {
			return productId;
		}
		public void setProductId(Integer productId) {
			this.productId = productId;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public Double getProductSalePrice() {
			return productSalePrice;
		}
		public void setProductSalePrice(Double productSalePrice) {
			this.productSalePrice = productSalePrice;
		}
		public Double getProductPrice() {
			return productPrice;
		}
		public void setProductPrice(Double productPrice) {
			this.productPrice = productPrice;
		}
		public String getProductSize() {
			return productSize;
		}
		public void setProductSize(String productSize) {
			this.productSize = productSize;
		}
		public String getProductSaleType() {
			return productSaleType;
		}
		public void setProductSaleType(String productSaleType) {
			this.productSaleType = productSaleType;
		}
		public String getProductBrand() {
			return productBrand;
		}
		public void setProductBrand(String productBrand) {
			this.productBrand = productBrand;
		}
		public String getProductSale() {
			return productSale;
		}
		public void setProductSale(String productSale) {
			this.productSale = productSale;
		}
		public String getProductRemark() {
			return productRemark;
		}
		public void setProductRemark(String productRemark) {
			this.productRemark = productRemark;
		}
		public Integer getProductType() {
			return productType;
		}
		public void setProductType(Integer productType) {
			this.productType = productType;
		}
		public String getProductImg() {
			return productImg;
		}
		public void setProductImg(String productImg) {
			this.productImg = productImg;
		}
		public String getProductImgOne() {
			return productImgOne;
		}
		public void setProductImgOne(String productImgOne) {
			this.productImgOne = productImgOne;
		}
		public String getProductImgTwo() {
			return productImgTwo;
		}
		public void setProductImgTwo(String productImgTwo) {
			this.productImgTwo = productImgTwo;
		}
		public String getProductBelong() {
			return productBelong;
		}
		public void setProductBelong(String productBelong) {
			this.productBelong = productBelong;
		}
		public String getProductDiscount() {
			return productDiscount;
		}
		public void setProductDiscount(String productDiscount) {
			this.productDiscount = productDiscount;
		}
		
		public String getPercentage() {
			return percentage;
		}
		public void setPercentage(String percentage) {
			this.percentage = percentage;
		}
		public String getProductVideo() {
			return productVideo;
		}
		public void setProductVideo(String productVideo) {
			this.productVideo = productVideo;
		}
		public Integer getSalesVolume() {
			return salesVolume;
		}
		public void setSalesVolume(Integer salesVolume) {
			this.salesVolume = salesVolume;
		}
		public String getRecommend() {
			return recommend;
		}
		public void setRecommend(String recommend) {
			this.recommend = recommend;
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
		public String getDraw() {
			return draw;
		}
		public void setDraw(String draw) {
			this.draw = draw;
		}
		public String getBelong() {
			return belong;
		}
		public void setBelong(String belong) {
			this.belong = belong;
		}
		public String getDiscountName() {
			if(discountName==null) {
				return "";
			}
			return discountName;
		}
		public void setDiscountName(String discountName) {
			this.discountName = discountName;
		}
		
}
