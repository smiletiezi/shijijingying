package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.Conversione;



public interface ConversioneDao {
	
	public int deleteByPrimaryKey(Integer conversionId);
	
	public int insertSelective(Conversione conversion);
	
	public int updateByPrimaryKeySelective(Conversione conversion);
	
	public List<Conversione> selectByExample(Conversione conversion);
	
	public  Conversione selectByPrimaryKey(Integer conversionId);
}
