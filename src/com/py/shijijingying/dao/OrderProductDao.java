package com.py.shijijingying.dao;

import java.util.List;
import java.util.Map;

import com.py.shijijingying.entity.OrderProduct;

public interface OrderProductDao {
public int deleteByPrimaryKey(Integer orderProductId);
	
	public int insertSelective(OrderProduct conversion);
	
	public int updateByPrimaryKeySelective(OrderProduct conversion);
	
	public List<OrderProduct> selectByExample(OrderProduct conversion);
	
	public  OrderProduct selectByPrimaryKey(Integer orderProductId);
	
	public List<OrderProduct> selectByList(Map<String,Object> map);
	
	public List<OrderProduct> selectByMonth(Map<String,Object> map);
}
