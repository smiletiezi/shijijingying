package com.py.shijijingying.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.SysUserDetailsMapper;
import com.py.shijijingying.entity.SysUserDetails;

@Service
public class SysUserDetailsService {
	@Autowired
	private SysUserDetailsMapper sysUserDetailsMapper;
	
	/**
	 * 增加方法
	 * @param record
	 * @return
	 */
	public  int insertSelective(SysUserDetails record) {
		return  sysUserDetailsMapper.insertSelective(record);
	}
	/**
	 * 根据主见查询
	 * @param id
	 * @return
	 */
	public  SysUserDetails selectByPrimaryKey(Integer id) {
		return sysUserDetailsMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 修改
	 * @param record
	 * @return
	 */
	public   int updateByPrimaryKeySelective(SysUserDetails record) {
		return sysUserDetailsMapper.updateByPrimaryKeySelective(record);
    }
	/**
	 * 通过userid 查找用户详情
	 * @param userId
	 * @return
	 */
	public SysUserDetails  selectByUser(Integer userId) {
		return  sysUserDetailsMapper.selectByUser(userId);
	}
}
