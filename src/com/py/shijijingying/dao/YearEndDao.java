package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.YearEnd;

public interface YearEndDao {
	public int deleteByPrimaryKey(Integer yearId);
	
	public int insertSelective(YearEnd end);
	
	public int updateByPrimaryKeySelective(YearEnd end);
	
	public List<YearEnd> selectByExample(YearEnd end);
	
	public  YearEnd selectByPrimaryKey(Integer yearId);
}
