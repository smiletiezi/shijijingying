package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.BxNews;

public interface BxNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BxNews record);

    int insertSelective(BxNews record);

    BxNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BxNews record);

    int updateByPrimaryKey(BxNews record);
    
    //根据新闻标题模糊查询
    List<BxNews> selectByTitle(BxNews record);
}