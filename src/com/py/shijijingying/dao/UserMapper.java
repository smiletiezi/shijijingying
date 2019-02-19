package com.py.shijijingying.dao;

import java.util.List;
import java.util.Map;

import com.py.shijijingying.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByLoginName(String loginName);
    
    
    //根据条件查询列表
    List<User> selectConditionList(Map<String, Object> searchMap);
    //根据id 查询用户表 和详情表
    User  selectByIdDetails(Integer userId);
    //根据userid 查询自己角色的下级用户
    List<User>  selectuserIdrole(Map<String, Object> map );
    //查询手机号码是否存在
    int  selectPhone(String phone);
    //根据手机号码查询用户
    User  selectByPhone(String phone);
    
    
    
}