package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.YearEndDao;
import com.py.shijijingying.entity.YearEnd;

@Service
public class YearEndService {
	@Autowired  
    private YearEndDao yearEndDao;  
	public int deleteByPrimaryKey(Integer yearId) {
		return yearEndDao.deleteByPrimaryKey(yearId);
	}
	
	public int insertSelective(YearEnd end) {
		return yearEndDao.insertSelective(end);
	}
	
	public YearEnd selectByPrimaryKey(Integer yearId) {
		return yearEndDao.selectByPrimaryKey(yearId);
	}
	
	public int updateByPrimaryKeySelective(YearEnd end) {
		return yearEndDao.updateByPrimaryKeySelective(end);
	}
	
	
	public List<YearEnd> selectByExample(YearEnd end){
		return yearEndDao.selectByExample(end);
	}
}
