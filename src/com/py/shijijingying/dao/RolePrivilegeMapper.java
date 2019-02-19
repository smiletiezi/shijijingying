package com.py.shijijingying.dao;

import com.py.shijijingying.entity.RolePrivilege;

public interface RolePrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePrivilege record);

    int insertSelective(RolePrivilege record);

    RolePrivilege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePrivilege record);

    int updateByPrimaryKey(RolePrivilege record);
    
    int   deleteByUserIdAll(Integer roleId);
    
}