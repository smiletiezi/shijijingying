package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.Order;

public interface OrderDao {
	public int deleteByPrimaryKey(Integer orderId);

	public int insert(Order order);

	public int insertSelective(Order order);

	public  Order selectByPrimaryKey(Integer orderId);

	public int updateByPrimaryKeySelective(Order order);

	public int updateByPrimaryKey(Order order);

	public List<Order> selectByExample(Order order);
	
	public List<Order> selectByList(List<Integer> ids);
}
