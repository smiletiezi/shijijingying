package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.TradingRecord;


public interface TradingRecordDao {
	public int deleteByPrimaryKey(Integer tradingRecordId);

	public int insert(TradingRecord record);

	public int insertSelective(TradingRecord record);

	public  TradingRecord selectByPrimaryKey(Integer tradingRecordId);

	public int updateByPrimaryKeySelective(TradingRecord record);

	public int updateByPrimaryKey(TradingRecord record);

	public List<TradingRecord> selectByExample(TradingRecord record);
}
