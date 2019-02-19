package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.BoxinUserMapper;
import com.py.shijijingying.entity.BoxinUser;

@Service
public class BoxinUserService {
	@Autowired  
    private BoxinUserMapper boxinUserMapper;  
      
      
    public List<BoxinUser> getAllUser() {  
        return boxinUserMapper.getAllUser();  
    }  
    
    
    public void insertUser(BoxinUser user) {  
    	boxinUserMapper.insertUser(user);  
    }  
  
    
    public void addUser(BoxinUser user) {  
    	boxinUserMapper.insertUser(user);  
    }  
     
    public BoxinUser getUserById(Integer id) {  
        return boxinUserMapper.queryByPrimaryKey(id);  
    } 
    
    public BoxinUser selectByPrimary(BoxinUser user) {  
        return boxinUserMapper.selectByPrimary(user);
    }
    
    public void updateByPrimaryKey(BoxinUser user) {  
    	boxinUserMapper.updateByPrimaryKey(user);
    }
    
    
	public List<BoxinUser> selectByExample(BoxinUser user){
		return boxinUserMapper.selectByExample(user);
	}
	
	public int deleteByPrimaryKey(Integer id) {
		return boxinUserMapper.deleteByPrimaryKey(id);
	}
    
}
