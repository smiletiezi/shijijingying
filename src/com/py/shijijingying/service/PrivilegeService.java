package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.PrivilegeMapper;
/**
 * 
 * @author LiuHao
 *
 * 2018年9月11日
 */

@Service
public class PrivilegeService {
	@Autowired
	private  PrivilegeMapper privilegeMapper;
	
	/**
	 * 查询所有权限
	 * @return
	 */
	public  List<String> selectAllprivilegeGrade(){
		return privilegeMapper.selectAllprivilegeGrade();
	}
	/**
	 * 根据用户id 查询权限
	 * @param userId
	 * @return
	 */
	public   List<String>selectAllButtonPrivileg(Integer userId){
		return privilegeMapper.selectAllButtonPrivileg(userId);
	}
}
