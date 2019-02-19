package com.py.shijijingying.dao;

import java.util.List;
import java.util.Map;

import com.py.shijijingying.entity.SysLog;

public interface SysLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
    //根据条件查询
    List<SysLog>  conditionSelect(Map<String , Object> map);

}