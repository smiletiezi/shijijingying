package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.EvaluateDao;
import com.py.shijijingying.entity.Evaluate;

@Service
public class EvaluateService {
	@Autowired  
    private EvaluateDao evaluateDao;  
	public int deleteByPrimaryKey(Integer evaluateId) {
		return evaluateDao.deleteByPrimaryKey(evaluateId);
	}
	
	public int insert(Evaluate evaluate) {
		return evaluateDao.insert(evaluate);
	}
	
	public int insertSelective(Evaluate evaluate) {
		return evaluateDao.insertSelective(evaluate);
	}
	
	public Evaluate selectByPrimaryKey(Integer evaluateId) {
		return evaluateDao.selectByPrimaryKey(evaluateId);
	}
	
	public int updateByPrimaryKeySelective(Evaluate evaluate) {
		return evaluateDao.updateByPrimaryKeySelective(evaluate);
	}
	
	public int updateByPrimaryKey(Evaluate evaluate) {
		return evaluateDao.updateByPrimaryKey(evaluate);
	}
	
	public List<Evaluate> selectByExample(Evaluate evaluate){
		return evaluateDao.selectByExample(evaluate);
	}
}
