package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.ProductDiscount;

public interface ProductDiscountDao {
	public int deleteByPrimaryKey(Integer discountId);

	public int insert(ProductDiscount productDiscount);

	public int insertSelective(ProductDiscount productDiscount);

	public  ProductDiscount selectByPrimaryKey(Integer discountId);

	public int updateByPrimaryKeySelective(ProductDiscount productDiscount);

	public int updateByPrimaryKey(ProductDiscount productDiscount);

	public List<ProductDiscount> selectByExample(ProductDiscount productDiscount);
	public List<ProductDiscount> selectByList(List<Integer> ids);
	public List<ProductDiscount> selectByNum(ProductDiscount productDiscount);
}
