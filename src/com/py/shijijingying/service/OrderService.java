package com.py.shijijingying.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.OrderDao;
import com.py.shijijingying.entity.Order;

@Service
public class OrderService {
	@Autowired  
    private OrderDao orderDao; 
	
	public int deleteByPrimaryKey(Integer orderId) {
		return orderDao.deleteByPrimaryKey(orderId);
	}
	public int insert(Order oeder) {
		return orderDao.insert(oeder);
	}
	public int insertSelective(Order oeder) {
		return orderDao.insertSelective(oeder);
	}
	public Order selectByPrimaryKey(Integer orderId) {
		return orderDao.selectByPrimaryKey(orderId);
	}
	public int updateByPrimaryKey(Order order) {
		return orderDao.updateByPrimaryKey(order);
	}
	
	public List<Order> selectByExample(Order oeder){
		return orderDao.selectByExample(oeder);
	}
	public List<Order> selectByList(String ids){
		List<Integer> list=getList(ids);
		return orderDao.selectByList(list);
	}
	/**
	   * id放入list
	  * 
	  * @param id
	  *            id(多个已逗号分隔)
	  * @return List集合
	  */
	 public List<Integer> getList(String id) {
	     List<Integer> list = new ArrayList<Integer>();
	    String[] str = id.split(",");
	     for (int i = 0; i < str.length; i++) {
	         list.add(Integer.parseInt(str[i]));
	     }
	     return list;
	 }
}
