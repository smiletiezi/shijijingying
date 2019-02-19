package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.BxArea;

public interface BxAreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BxArea record);

    int insertSelective(BxArea record);

    BxArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BxArea record);

    int updateByPrimaryKey(BxArea record);
    
    //根据名字模糊查询
    BxArea selectByName(String name);
    //查询列表
    List<BxArea> selectByExample(BxArea record);
}