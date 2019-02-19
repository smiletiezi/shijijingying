package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.BxNewsMapper;
import com.py.shijijingying.entity.BxNews;

@Service
public class BxNewsService {
@Autowired
private BxNewsMapper bxNewsMapper;

public int deleteByPrimaryKey(Integer id) {
	return bxNewsMapper.deleteByPrimaryKey(id);
}

public BxNews selectByPrimaryKey(Integer id) {
	return bxNewsMapper.selectByPrimaryKey(id);
}
public int updateByPrimaryKeySelective(BxNews record) {
	return bxNewsMapper.updateByPrimaryKey(record);
}
public int insertSelective(BxNews record) {
	return bxNewsMapper.insertSelective(record);
}
public  List<BxNews> selectByTitle(BxNews record){
	return bxNewsMapper.selectByTitle(record);
}

}
