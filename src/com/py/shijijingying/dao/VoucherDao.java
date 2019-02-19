package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.Voucher;




public interface VoucherDao {
	public int deleteByPrimaryKey(Integer voucherId);

	public int insert(Voucher voucher);

	public int insertSelective(Voucher voucher);

	public  Voucher selectByPrimaryKey(Integer voucherId);

	public int updateByPrimaryKeySelective(Voucher voucher);

	public int updateByPrimaryKey(Voucher voucher);

	public List<Voucher> selectByExample(Voucher voucher);
}
