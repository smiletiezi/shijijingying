package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.Exchange;



public interface ExchangeDao {
	public int deleteByPrimaryKey(Integer exchangeId);

	public int insert(Exchange change);

	public int insertSelective(Exchange change);

	public  Exchange selectByPrimaryKey(Integer exchangeId);

	public int updateByPrimaryKeySelective(Exchange change);

	public int updateByPrimaryKey(Exchange change);

	public List<Exchange> selectByExample(Exchange change);
}
