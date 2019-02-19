package com.py.shijijingying.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.ProductDiscountDao;
import com.py.shijijingying.entity.ProductDiscount;

@Service
public class ProductDiscountService {
	@Autowired 
    private ProductDiscountDao productDiscountDao;  
	
	public int deleteByPrimaryKey(Integer discountId) {
		return productDiscountDao.deleteByPrimaryKey(discountId);
	}
	
	public int insert(ProductDiscount productDiscount) {
		return productDiscountDao.insert(productDiscount);
	}
	
	public int insertSelective(ProductDiscount productDiscount) {
		return productDiscountDao.insertSelective(productDiscount);
	}
	
	public ProductDiscount selectByPrimaryKey(Integer discountId) {
		return productDiscountDao.selectByPrimaryKey(discountId);
	}
	
	public int updateByPrimaryKeySelective(ProductDiscount productDiscount) {
		return productDiscountDao.updateByPrimaryKeySelective(productDiscount);
	}
	
	public int updateByPrimaryKey(ProductDiscount productDiscount) {
		return productDiscountDao.updateByPrimaryKey(productDiscount);
	}
	
	public List<ProductDiscount> selectByExample(ProductDiscount productDiscount){
		return productDiscountDao.selectByExample(productDiscount);
	}
	
	public List<ProductDiscount> selectByList(List<Integer> ids){
		//List<Integer> list=getList(ids);
		return productDiscountDao.selectByList(ids);
	}
	
	public List<ProductDiscount> selectByNum(ProductDiscount productDiscount){
		return productDiscountDao.selectByNum(productDiscount);
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
