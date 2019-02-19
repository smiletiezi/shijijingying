package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.MyVoucherDao;
import com.py.shijijingying.entity.MyVoucher;

@Service
public class MyVoucherService{
	@Autowired
    private MyVoucherDao myVoucherDao;  
	

	public int deleteByPrimaryKey(Integer myVoucherId) {
		return myVoucherDao.deleteByPrimaryKey(myVoucherId);
	}
	

	public int insert(MyVoucher myVoucher) {
		return myVoucherDao.insert(myVoucher);
	}
	

	public int insertSelective(MyVoucher myVoucher) {
		return myVoucherDao.insertSelective(myVoucher);
	}
	

	public MyVoucher selectByPrimaryKey(Integer myVoucherId) {
		return myVoucherDao.selectByPrimaryKey(myVoucherId);
	}
	

	public int updateByPrimaryKeySelective(MyVoucher myVoucher) {
		return myVoucherDao.updateByPrimaryKeySelective(myVoucher);
	}
	

	public int updateByPrimaryKey(MyVoucher myVoucher) {
		return myVoucherDao.updateByPrimaryKey(myVoucher);
	}
	
	public List<MyVoucher> selectByExample(MyVoucher myVoucher){
		return myVoucherDao.selectByExample(myVoucher);
	}
}
