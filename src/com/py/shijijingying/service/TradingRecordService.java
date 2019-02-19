package com.py.shijijingying.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.TradingRecordDao;
import com.py.shijijingying.entity.TradingRecord;


@Service
public class TradingRecordService{
	
	@Autowired 
    private TradingRecordDao tradingRecordDao;  
	
	public int deleteByPrimaryKey(Integer tradingRecordId) {
		return tradingRecordDao.deleteByPrimaryKey(tradingRecordId);
	}
	
	public int insert(TradingRecord record) {
		return tradingRecordDao.insert(record);
	}
	
	public int insertSelective(TradingRecord record) {
		return tradingRecordDao.insertSelective(record);
	}
	
	public TradingRecord selectByPrimaryKey(Integer tradingRecordId) {
		return tradingRecordDao.selectByPrimaryKey(tradingRecordId);
	}
	
	public int updateByPrimaryKeySelective(TradingRecord record) {
		return tradingRecordDao.updateByPrimaryKeySelective(record);
	}
	
	public int updateByPrimaryKey(TradingRecord record) {
		return tradingRecordDao.updateByPrimaryKey(record);
	}
	
	public List<TradingRecord> selectByExample(TradingRecord record){
		return tradingRecordDao.selectByExample(record);
	}	
}
