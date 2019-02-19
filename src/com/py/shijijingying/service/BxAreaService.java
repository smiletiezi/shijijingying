package com.py.shijijingying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.BxAreaMapper;
import com.py.shijijingying.entity.BxArea;

@Service
public class BxAreaService {
 @Autowired
 private BxAreaMapper bxAreaMapper;
 
 //根据id删除
 public int deleteByPrimaryKey(Integer id) {
	 return bxAreaMapper.deleteByPrimaryKey(id);
 }
 
 //新增
 public int insert(BxArea record) {
	 return bxAreaMapper.insert(record);
 }
 
 //新增
 public int insertSelective(BxArea record) {
	 return bxAreaMapper.insertSelective(record);
 }
 
 //根据id查询
  public BxArea selectByPrimaryKey(Integer id) {
	  return bxAreaMapper.selectByPrimaryKey(id);
  }
  
  //修改
  
  public int updateByPrimaryKeySelective(BxArea record) {
	  return bxAreaMapper.updateByPrimaryKeySelective(record);
  }
 
  //根据名字模糊查询
  public BxArea selectByName(String name) {
	  return bxAreaMapper.selectByName(name);
  }
  
  //查询列表
  public List<BxArea> selectByExample(BxArea record){
	  return bxAreaMapper.selectByExample(record);
  }
}
