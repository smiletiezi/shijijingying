package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.Evaluate;




public interface EvaluateDao {
	public int deleteByPrimaryKey(Integer evaluateId);

	public int insert(Evaluate evaluate);

	public int insertSelective(Evaluate evaluate);

	public  Evaluate selectByPrimaryKey(Integer evaluateId);

	public int updateByPrimaryKeySelective(Evaluate evaluate);

	public int updateByPrimaryKey(Evaluate evaluate);

	public List<Evaluate> selectByExample(Evaluate evaluate);
	
}
