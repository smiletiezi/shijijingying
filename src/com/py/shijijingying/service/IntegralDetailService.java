package com.py.shijijingying.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.IntegralDetailDao;
import com.py.shijijingying.entity.IntegralDetail;


@Service
public class IntegralDetailService{
	@Autowired  
    private IntegralDetailDao integralDetailDao;
	
	public int deleteByPrimaryKey(Integer detailId) {
		return integralDetailDao.deleteByPrimaryKey(detailId);
	}
	
	public int insert(IntegralDetail detail) {
		return integralDetailDao.insert(detail);
	}
	
	public int insertSelective(IntegralDetail detail) {
		return integralDetailDao.insertSelective(detail);
	}
	
	public IntegralDetail selectByPrimaryKey(Integer detailId) {
		return integralDetailDao.selectByPrimaryKey(detailId);
	}
	
	public int updateByPrimaryKeySelective(IntegralDetail detail) {
		return integralDetailDao.updateByPrimaryKeySelective(detail);
	}
	
	public int updateByPrimaryKey(IntegralDetail detail) {
		return integralDetailDao.updateByPrimaryKey(detail);
	}
	
	public List<IntegralDetail> selectByExample(IntegralDetail detail){
		return integralDetailDao.selectByExample(detail);
	}
}
