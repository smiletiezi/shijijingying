package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.Product;

public interface ProductDao {
	public int deleteByPrimaryKey(Integer productId);

	public int insert(Product product);

	public int insertSelective(Product product);

	public  Product selectByPrimaryKey(Integer productId);

	public int updateByPrimaryKeySelective(Product product);

	public int updateByPrimaryKey(Product product);

	public List<Product> selectByExample(Product product);
	
	public List<Product> selectByList(List<Integer> ids);
	public List<Product> selectByLimt();
}
