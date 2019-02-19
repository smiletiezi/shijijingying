package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.IntegralDetail;



public interface IntegralDetailDao {
	public int deleteByPrimaryKey(Integer detailId);

	public int insert(IntegralDetail detail);

	public int insertSelective(IntegralDetail detail);

	public  IntegralDetail selectByPrimaryKey(Integer detailId);

	public int updateByPrimaryKeySelective(IntegralDetail detail);

	public int updateByPrimaryKey(IntegralDetail detail);

	public List<IntegralDetail> selectByExample(IntegralDetail detail);
}
