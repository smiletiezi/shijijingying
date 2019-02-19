package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.Privilege;

public interface PrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);
    
    //根据用户id获取菜单
    List<Privilege> getMenuByUserId(Integer userId);
    
    //查询所有菜单
    List<Privilege> selectAllMenu();
    
    //查询所有菜单
    List<Privilege> selectAllprivilege();
    
    //根据角色ID 查询权限
    List<Privilege> selectRoleAndPrivilege(Integer roleId);
    
    //查询所有权限
    List<String> selectAllprivilegeGrade();
    
    //根据用户id 查询权限
    List<String>selectAllButtonPrivileg(Integer userId);
    

}