package com.py.shijijingying.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 角色权限关联表
 * @author LiuHao
 *
 * 2018年9月10日
 */

import com.py.shijijingying.dao.RolePrivilegeMapper;
import com.py.shijijingying.entity.RolePrivilege;
@Service
public class RoleRrivilegeService {
	
	@Autowired
	private  RolePrivilegeMapper rolePrivilegeMapper ;
	
	/**
	 * 增加角色权限关系
	 * @param record
	 * @return
	 */
	public  int insertSelective(RolePrivilege record) {
		return rolePrivilegeMapper.insertSelective(record);
	}
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	public  int deleteByPrimaryKey(Integer id) {
		return  rolePrivilegeMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据角色id 删除所有此角色的权限
	 * @param roleId
	 * @return
	 */
	public  int deleteByUserIdAll(Integer roleId) {
		return rolePrivilegeMapper.deleteByUserIdAll(roleId);
	}
}
