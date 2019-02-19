package com.py.shijijingying.dao;

import java.util.List;
import java.util.Map;

import com.py.shijijingying.entity.Sysrole;

public interface SysroleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sysrole record);

    int insertSelective(Sysrole record);

    Sysrole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sysrole record);

    int updateByPrimaryKey(Sysrole record);
    //根据那么查询    没有name 查询所以   不包含管理员
    List<Sysrole>  selectRoleListAndName(Map<String, Object> map );
    
    //查询所有Role
    List<Sysrole> selectAllRole();
    
    //根据登录用户查询下级角色
    List<Sysrole>  selectUserIdRole(Integer userId);
    
    //根据用户ID 查询此用户下所有的角色 可模糊查询Name 
    List<Sysrole>  selectUserIdRoleByName(Map<String , Object> map);
    
    //根据userid 查询自己的权限
    Sysrole selectByUseridRole(Integer userId);
    
}