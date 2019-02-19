package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.PercentageDao;
import com.py.shijijingying.entity.Percentage;

@Service
public class PercentageService {
	@Autowired  
    private PercentageDao percentageDao;  
	
	public int deleteByPrimaryKey(Integer percentageId) {
		return percentageDao.deleteByPrimaryKey(percentageId);
	}
	
	public int insertSelective(Percentage percentage) {
		return percentageDao.insertSelective(percentage);
	}
	
	public Percentage selectByPrimaryKey(Integer percentageId) {
		return percentageDao.selectByPrimaryKey(percentageId);
	}
	
	public int updateByPrimaryKeySelective(Percentage percentage) {
		return percentageDao.updateByPrimaryKeySelective(percentage);
	}
	
	public List<Percentage> selectByExample(Percentage percentage){
		return percentageDao.selectByExample(percentage);
	}

	public Percentage selectByName(String name) {
		return percentageDao.selectByName(name);
	}
}
