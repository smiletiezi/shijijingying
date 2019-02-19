package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.MyVoucher;



public interface MyVoucherDao {
	public int deleteByPrimaryKey(Integer myVoucherId);

	public int insert(MyVoucher myVoucher);

	public int insertSelective(MyVoucher myVoucher);

	public  MyVoucher selectByPrimaryKey(Integer voucherId);

	public int updateByPrimaryKeySelective(MyVoucher myVoucher);

	public int updateByPrimaryKey(MyVoucher myVoucher);

	public List<MyVoucher> selectByExample(MyVoucher myVoucher);
}
