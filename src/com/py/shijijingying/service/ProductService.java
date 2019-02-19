package com.py.shijijingying.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.ProductDao;
import com.py.shijijingying.entity.Product;

@Service
public class ProductService {
	@Autowired   
    private ProductDao productDao;
	
	
	public int deleteByPrimaryKey(Integer productId) {
		return productDao.deleteByPrimaryKey(productId);
	}
	
	
	public int insert(Product product) {
		return productDao.insert(product);
	}
	
	
	public int insertSelective(Product product) {
		return productDao.insertSelective(product);
	}
	
	
	public Product selectByPrimaryKey(Integer productId) {
		return productDao.selectByPrimaryKey(productId);
	}
	
	
	public int updateByPrimaryKeySelective(Product product) {
		return productDao.updateByPrimaryKeySelective(product);
	}
	
	
	public int updateByPrimaryKey(Product product) {
		return productDao.updateByPrimaryKey(product);
	}
	
	
	public List<Product> selectByExample(Product product){
		return productDao.selectByExample(product);
	}
	
	
	public List<Product> selectByList(List<String> ids){
		List<Integer> list=getList(ids);
		return productDao.selectByList(list);
	}
	
	public List<Product> selectByLimt(){
		return productDao.selectByLimt();
	}
	
	/**
	   * id放入list
	  * 
	  * @param id
	  *            id(多个已逗号分隔)
	  * @return List集合
	  */
	 public List<Integer> getList(List<String> ids) {
	     List<Integer> list = new ArrayList<Integer>();
	     for (int i = 0; i < ids.size(); i++) {
	         list.add(Integer.parseInt(ids.get(i)));
	     }
	     return list;
}
}
