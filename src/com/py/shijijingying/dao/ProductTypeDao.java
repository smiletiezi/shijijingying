package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.ProductType;

public interface ProductTypeDao {
	public int deleteByPrimaryKey(Integer productTypeId);

	public int insert(ProductType productType);

	public int insertSelective(ProductType productType);

	public  ProductType selectByPrimaryKey(Integer productTypeId);

	public int updateByPrimaryKeySelective(ProductType productType);

	public int updateByPrimaryKey(ProductType productType);

	public List<ProductType> selectByExample(ProductType productType);
	
	//获取所有二级类型
	public List<ProductType> selectTwoAll();
}
