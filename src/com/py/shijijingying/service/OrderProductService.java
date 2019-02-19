package com.py.shijijingying.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.OrderProductDao;
import com.py.shijijingying.entity.OrderProduct;

@Service
public class OrderProductService {
	@Autowired 
    private OrderProductDao orderProductDao;  
	
	public int deleteByPrimaryKey(Integer orderProductId) {
		return orderProductDao.deleteByPrimaryKey(orderProductId);
	}
	
	public int insertSelective(OrderProduct orderProduct) {
		return orderProductDao.insertSelective(orderProduct);
	}
	
	public OrderProduct selectByPrimaryKey(Integer orderProductId) {
		return orderProductDao.selectByPrimaryKey(orderProductId);
	}
	
	public int updateByPrimaryKeySelective(OrderProduct orderProduct) {
		return orderProductDao.updateByPrimaryKeySelective(orderProduct);
	}
	
	public List<OrderProduct> selectByExample(OrderProduct orderProduct){
		return orderProductDao.selectByExample(orderProduct);
	}

	public List<OrderProduct> selectByList(Map<String,Object> map) {
		return orderProductDao.selectByList(map);
	}
	
	public List<OrderProduct> selectByMonth(Map<String,Object> map) {
	
		return orderProductDao.selectByList(map);
		
	}
}
