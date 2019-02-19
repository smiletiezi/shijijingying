package com.py.shijijingying.dao;

import java.util.List;
import java.util.Map;

import com.py.shijijingying.entity.UserPrivilege;

public interface UserPrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPrivilege record);

    int insertSelective(UserPrivilege record);

    UserPrivilege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPrivilege record);

    int updateByPrimaryKey(UserPrivilege record);
    
    //根据用户标识查询按钮权限
    List<String> selectButtonPermission(Map<String, Object> map);
}