package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.ConversioneDao;
import com.py.shijijingying.entity.Conversione;
@Service
public class ConversioneService {
	@Autowired 
    private ConversioneDao conversionDao;  
	
	public int deleteByPrimaryKey(Integer conversionId) {
		return conversionDao.deleteByPrimaryKey(conversionId);
	}
	
	public int insertSelective(Conversione conversion) {
		return conversionDao.insertSelective(conversion);
	}
	
	public Conversione selectByPrimaryKey(Integer conversionId) {
		return conversionDao.selectByPrimaryKey(conversionId);
	}
	
	public int updateByPrimaryKeySelective(Conversione conversion) {
		return conversionDao.updateByPrimaryKeySelective(conversion);
	}
	
	public List<Conversione> selectByExample(Conversione conversion){
		return conversionDao.selectByExample(conversion);
	}
}
