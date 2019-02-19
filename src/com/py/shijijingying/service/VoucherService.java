package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.VoucherDao;
import com.py.shijijingying.entity.Voucher;

@Service
public class VoucherService {
	@Autowired  
    private VoucherDao voucherDao;  
	
	public int deleteByPrimaryKey(Integer voucherId) {
		return voucherDao.deleteByPrimaryKey(voucherId);
	}
	
	public int insert(Voucher voucher) {
		return voucherDao.insert(voucher);
	}
	
	public int insertSelective(Voucher voucher) {
		return voucherDao.insertSelective(voucher);
	}
	
	public Voucher selectByPrimaryKey(Integer voucherId) {
		return voucherDao.selectByPrimaryKey(voucherId);
	}
	
	public int updateByPrimaryKeySelective(Voucher voucher) {
		return voucherDao.updateByPrimaryKeySelective(voucher);
	}
	
	public int updateByPrimaryKey(Voucher voucher) {
		return voucherDao.updateByPrimaryKey(voucher);
	}
	
	public List<Voucher> selectByExample(Voucher voucher){
		return voucherDao.selectByExample(voucher);
	}
}
