package com.py.shijijingying.dao;

import java.util.List;
import java.util.Map;

import com.py.shijijingying.entity.BoxinUser;

public interface BoxinUserMapper {
	 public List<BoxinUser> getAllUser();
	    public List<BoxinUser> queryUserByBatch(Map<String,Object> params);  
	    
	    public void insertUser(BoxinUser user); 
	    
	    public void insertUserByBatch(List<BoxinUser> list);  
	      
	    public int deleteByPrimaryKey(Integer id);  
	      
	    public void delteUserByBatch(Map<String,Object> params);  
	      
	    public void updateByPrimaryKey(BoxinUser user); 
	    
	    public BoxinUser queryByPrimaryKey(Integer id);
	    
	    public BoxinUser selectByPrimary(BoxinUser user);
	    
	    public List<BoxinUser> selectByExample(BoxinUser user);
	    
}