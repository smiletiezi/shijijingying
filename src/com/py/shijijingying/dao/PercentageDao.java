package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.Percentage;




public interface PercentageDao {
	public int deleteByPrimaryKey(Integer percentageId);
	
	public int insertSelective(Percentage tage);
	
	public int updateByPrimaryKeySelective(Percentage tage);
	
	public List<Percentage> selectByExample(Percentage tage);
	
	public Percentage selectByName(String name);
	
	public  Percentage selectByPrimaryKey(Integer percentageId);
}
