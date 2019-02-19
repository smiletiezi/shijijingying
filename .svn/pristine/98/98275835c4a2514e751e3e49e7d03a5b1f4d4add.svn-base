package com.py.shijijingying.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.PrivilegeMapper;
import com.py.shijijingying.dao.SysroleMapper;
import com.py.shijijingying.entity.Privilege;
import com.py.shijijingying.entity.Sysrole;

/**
 * 
 * @author LiuHao
 *
 * 2018年9月8日
 */



@Service
public class SysRoleService {
	@Autowired
	private SysroleMapper sysroleMapper;
	
	@Autowired
	private PrivilegeMapper privilegeMapper;
	
	/**
	 * 根据用户ID 查询此用户下所有的角色 可模糊查询Name 
	 * @param map
	 * @return
	 */
    public   List<Sysrole>  selectUserIdRoleByName(Map<String , Object> map){
    	return sysroleMapper.selectUserIdRoleByName(map);
    }

	
	/**
	 * 增加角色
	 * @param record
	 * @return
	 */
	public  int insertSelective(Sysrole record) {
		return sysroleMapper.insertSelective(record);
	}
	/**
	 * 修改
	 * @param record
	 * @return
	 */
    public   int updateByPrimaryKeySelective(Sysrole record) {
    	return  sysroleMapper.updateByPrimaryKeySelective(record);
    }
	
	/**
	 * 查询所有角色
	 * @return
	 */
	
   public  List<Sysrole>selectAllRole(){
	   return   sysroleMapper.selectAllRole();
   }
	
	
	
	/**
	 * 
	 * 根据name查询    没有name 查询所以   不包含管理员
	 * @param map
	 * @return
	 */
	 public  List<Sysrole>  selectRoleListAndName(Map<String, Object> map ) {
		 return  sysroleMapper.selectRoleListAndName(map);
	 }
	
		 
	/**
	 * 根据主检查询角色
	 * 
	 * @param id
	 * @return
	 */
	 public    Sysrole selectByPrimaryKey(Integer id) {
		 return sysroleMapper.selectByPrimaryKey(id);
	 }
	 
	 /**
	  * 查询所有的权限
	  * @return
	  */
	 public List<Privilege> selectAllprivilege(){
		 return privilegeMapper.selectAllprivilege();
	 }  
	 
	 /**
	  * 角色查询权限
	  * @param roleId
	  * @return
	  */
	 public List<Privilege> selectRoleAndPrivilege(Integer roleId){
		 return privilegeMapper.selectRoleAndPrivilege(roleId);
	  }
	 
	 /**
	  * 根据登录用户查询下级角色
	  * @param userId
	  * @return
	  */
	 public  List<Sysrole>  selectUserIdRole(Integer userId){
		 return sysroleMapper.selectUserIdRole(userId);
	 }
	 
	 
	  /**
	   * 根据userid 查询自己的权限
	   * @param userId
	   * @return
	   */
	 public  Sysrole selectByUseridRole(Integer userId) {
		 return sysroleMapper.selectByUseridRole(userId);
	 }
	 
}
