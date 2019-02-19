package com.py.shijijingying.entity;

public class ProductType {
	//类型id
		private Integer productTypeId;
		//类型名称
		private String name;
		//产品图标
		private String img;
		//父级类型
		private String typeCode;
		
		
		public Integer getProductTypeId() {
			return productTypeId;
		}
		public void setProductTypeId(Integer productTypeId) {
			this.productTypeId = productTypeId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		public String getTypeCode() {
			return typeCode;
		}
		public void setTypeCode(String typeCode) {
			this.typeCode = typeCode;
		}
}
