package com.py.shijijingying.dao;

import com.py.shijijingying.entity.SysToken;

public interface SysTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysToken record);

    int insertSelective(SysToken record);

    SysToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysToken record);

    int updateByPrimaryKey(SysToken record);
    
    //根据用户ID查询token  手机端
    SysToken  selectByIdMobileTerminal(Integer userId);
}