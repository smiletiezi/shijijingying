package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.ShopCart;




public interface ShopCartDao {
	public int deleteByPrimaryKey(Integer shopCartId);
	
	public int deleteByList(List<Integer> ids);
	
	public int insert(ShopCart cart);

	public int insertSelective(ShopCart cart);

	public  ShopCart selectByPrimaryKey(Integer shopCartId);

	public int updateByPrimaryKeySelective(ShopCart cart);

	public int updateByPrimaryKey(ShopCart cart);

	public List<ShopCart> selectByExample(ShopCart cart);
	
	public List<ShopCart> selectByList(List<Integer> ids);
}
