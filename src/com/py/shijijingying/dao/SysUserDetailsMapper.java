package com.py.shijijingying.dao;

import com.py.shijijingying.entity.SysUserDetails;

public interface SysUserDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserDetails record);

    int insertSelective(SysUserDetails record);

    SysUserDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserDetails record);

    int updateByPrimaryKey(SysUserDetails record);
    //通过userid 查找用户详情
    SysUserDetails   selectByUser(Integer userId);
 
}