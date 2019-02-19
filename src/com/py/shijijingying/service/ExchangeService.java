package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.ExchangeDao;
import com.py.shijijingying.entity.Exchange;

@Service
public class ExchangeService{
	@Autowired  
    private ExchangeDao exchangDao;  
	
	public int deleteByPrimaryKey(Integer exchangeId) {
		return exchangDao.deleteByPrimaryKey(exchangeId);
	}
	public int insert(Exchange change) {
		return exchangDao.insert(change);
	}
	
	public int insertSelective(Exchange change) {
		return exchangDao.insertSelective(change);
	}
	
	public Exchange selectByPrimaryKey(Integer exchangeId) {
		return exchangDao.selectByPrimaryKey(exchangeId);
	}
	
	public int updateByPrimaryKeySelective(Exchange change) {
		return exchangDao.updateByPrimaryKeySelective(change);
	}
	
	public int updateByPrimaryKey(Exchange change) {
		return exchangDao.updateByPrimaryKey(change);
	}
	
	public List<Exchange> selectByExample(Exchange change){
		return exchangDao.selectByExample(change);
	}
	
}
