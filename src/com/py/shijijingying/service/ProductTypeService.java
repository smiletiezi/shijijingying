package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.ProductTypeDao;
import com.py.shijijingying.entity.ProductType;

@Service
public class ProductTypeService {
	@Autowired   
    private ProductTypeDao productTypeDao;  
	
	public int deleteByPrimaryKey(Integer productTypeId) {
		return productTypeDao.deleteByPrimaryKey(productTypeId);
	}
	
	public int insert(ProductType productType) {
		return productTypeDao.insert(productType);
	}
	
	public int insertSelective(ProductType productType) {
		return productTypeDao.insertSelective(productType);
	}
	
	public ProductType selectByPrimaryKey(Integer productTypeId) {
		return productTypeDao.selectByPrimaryKey(productTypeId);
	}
	
	public int updateByPrimaryKeySelective(ProductType productType) {
		return productTypeDao.updateByPrimaryKeySelective(productType);
	}
	
	public int updateByPrimaryKey(ProductType productType) {
		return productTypeDao.updateByPrimaryKey(productType);
	}
	
	public List<ProductType> selectByExample(ProductType productType){
		return productTypeDao.selectByExample(productType);
	}
	
	public List<ProductType> selectTwoAll(){
		return productTypeDao.selectTwoAll();
	}
}
