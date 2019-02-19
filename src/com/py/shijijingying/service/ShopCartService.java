package com.py.shijijingying.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.ShopCartDao;
import com.py.shijijingying.entity.ShopCart;

@Service
public class ShopCartService{
	@Autowired  
    private ShopCartDao shopCartDao;  
	
	public int deleteByPrimaryKey(Integer shopCartId) {
		return shopCartDao.deleteByPrimaryKey(shopCartId);
	}
	
	public int insert(ShopCart cart) {
		return shopCartDao.insert(cart);
	}
	
	public int insertSelective(ShopCart cart) {
		return shopCartDao.insertSelective(cart);
	}
	
	public ShopCart selectByPrimaryKey(Integer shopCartId) {
		return shopCartDao.selectByPrimaryKey(shopCartId);
	}
	
	public int updateByPrimaryKeySelective(ShopCart cart) {
		return shopCartDao.updateByPrimaryKeySelective(cart);
	}
	
	public int updateByPrimaryKey(ShopCart cart) {
		return shopCartDao.updateByPrimaryKey(cart);
	}
	
	public List<ShopCart> selectByExample(ShopCart cart){
		return shopCartDao.selectByExample(cart);
	}
	
	public int deleteByList(String ids){
		List<Integer> list=getList(ids);
		return shopCartDao.deleteByList(list);
	}
	
	public List<ShopCart> selectByList(String ids){
		List<Integer> list=getList(ids);
		return shopCartDao.selectByList(list);
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
