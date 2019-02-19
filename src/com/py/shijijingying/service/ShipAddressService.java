package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.ShipAddressDao;
import com.py.shijijingying.entity.ShipAddress;


@Service
public class ShipAddressService{
	@Autowired
    private ShipAddressDao shipAddressDao;  
	
	public int deleteByPrimaryKey(Integer shipId) {
		return shipAddressDao.deleteByPrimaryKey(shipId);
	}
	
	public int insert(ShipAddress address) {
		return shipAddressDao.insert(address);
	}
	
	public int insertSelective(ShipAddress address) {
		return shipAddressDao.insertSelective(address);
	}
	
	public ShipAddress selectByPrimaryKey(Integer shipId) {
		return shipAddressDao.selectByPrimaryKey(shipId);
	}
	
	public int updateByPrimaryKeySelective(ShipAddress address) {
		return shipAddressDao.updateByPrimaryKeySelective(address);
	}
	
	public int updateByPrimaryKey(ShipAddress address) {
		return shipAddressDao.updateByPrimaryKey(address);
	}
	
	public List<ShipAddress> selectByExample(ShipAddress address){
		return shipAddressDao.selectByExample(address);
	}
	
}
