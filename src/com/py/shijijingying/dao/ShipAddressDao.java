package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.ShipAddress;




public interface ShipAddressDao {
	public int deleteByPrimaryKey(Integer shipId);

	public int insert(ShipAddress shipAddress);

	public int insertSelective(ShipAddress shipAddress);

	public  ShipAddress selectByPrimaryKey(Integer shipId);

	public int updateByPrimaryKeySelective(ShipAddress shipAddress);

	public int updateByPrimaryKey(ShipAddress shipAddress);

	public List<ShipAddress> selectByExample(ShipAddress shipAddress);
	
}
