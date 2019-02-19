package com.py.shijijingying.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.google.common.collect.Maps;
import com.py.shijijingying.Constants;
import com.py.shijijingying.dao.PrivilegeMapper;
import com.py.shijijingying.dao.UserMapper;
import com.py.shijijingying.dao.UserPrivilegeMapper;
import com.py.shijijingying.entity.Privilege;
import com.py.shijijingying.entity.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PrivilegeMapper privilegeMapper;
	@Autowired
	private UserPrivilegeMapper userPrivilegeMapper;
	
	
	
	
     /**
      * 根据用户id 查询用户表  和详情表
      * @param userId
      * @return
      */
	 public   User  selectByIdDetails(Integer userId) {
		 return userMapper.selectByIdDetails(userId);
	 }
	
	/**
	 * 根据userid 查询自己角色的下级用户
	 * 
	 * @param userId
	 * @return
	 */
	 public  List<User>  selectuserIdrole(Map<String, Object> map ) {
		 return  userMapper.selectuserIdrole(map);
	 }
	
	
	
	/**
	 * 根据用户id获取菜单列表
	 * @param userId
	 * @return
	 */
	public Map<String,Object> getMenuByUserId(Integer userId,String type){
		Map<String,Object> resultMap = Maps.newHashMap();
		List<Privilege> menuList=null;
		if("0".equals(type)) {//超级管理员有全部权限
			menuList=privilegeMapper.selectAllMenu();
		}else {
			menuList = privilegeMapper.getMenuByUserId(userId);
		}
		//暂遍历3级菜单
		List<Privilege> menu1 = new ArrayList<Privilege>();
		List<Privilege> menu2 = new ArrayList<Privilege>();
		List<Privilege> menu3 = new ArrayList<Privilege>();
//		List<Privilege> menu4 = new ArrayList<Privilege>();
//		List<Privilege> menu5 = new ArrayList<Privilege>();
//		List<Privilege> menu6 = new ArrayList<Privilege>();
		Privilege privilege = new Privilege();
		//一级菜单
		if(menuList!=null && menuList.size() > 0) {
			for (int i = 0; i < menuList.size(); i++) {
				privilege = new Privilege();
				privilege = menuList.get(i);
				if(privilege.getParentId() == 0){
					menu1.add(privilege);
				}
			}
			menuList.removeAll(menu1);
			resultMap.put("menu1", menu1);
		}
		
		//二级菜单
		if(menuList!=null && menuList.size() > 0){
			for (int i = 0; i < menu1.size(); i++) {
				privilege = new Privilege();
				privilege = menu1.get(i);
				for (int j = 0; j < menuList.size(); j++) {
					Privilege p = menuList.get(j);
					if(privilege.getId() == p.getParentId()){
						menu2.add(p);
					}
				}
			}
			menuList.removeAll(menu2);
			resultMap.put("menu2", menu2);
		}
		//三级菜单
		if(menuList!=null && menuList.size() > 0){
			for (int i = 0; i < menu2.size(); i++) {
				privilege = new Privilege();
				privilege = menu2.get(i);
				for (int j = 0; j < menuList.size(); j++) {
					Privilege p = menuList.get(j);
					if(privilege.getId() == p.getParentId()){
						menu3.add(p);
					}
				}
			}
			menuList.removeAll(menu3);
			resultMap.put("menu3", menu3);
		}
		//四级菜单
//		if(menuList!=null && menuList.size() > 0){
//			for (int i = 0; i < menu3.size(); i++) {
//				privilege = new Privilege();
//				privilege = menu3.get(i);
//				for (int j = 0; j < menuList.size(); j++) {
//					Privilege p = menuList.get(j);
//					if(privilege.getId() == p.getParentId()){
//						menu4.add(p);
//					}
//				}
//			}
//			menuList.removeAll(menu4);
//			resultMap.put("menu4", menu4);
//		}
//		//五级菜单
//		if(menuList!=null && menuList.size() > 0){
//			for (int i = 0; i < menu4.size(); i++) {
//				privilege = new Privilege();
//				privilege = menu4.get(i);
//				for (int j = 0; j < menuList.size(); j++) {
//					Privilege p = menuList.get(j);
//					if(privilege.getId() == p.getParentId()){
//						menu5.add(p);
//					}
//				}
//			}
//			menuList.removeAll(menu5);
//			resultMap.put("menu5", menu5);
//		}
//		//六级菜单
//		if(menuList!=null && menuList.size() > 0){
//			for (int i = 0; i < menu5.size(); i++) {
//				privilege = new Privilege();
//				privilege = menu5.get(i);
//				for (int j = 0; j < menuList.size(); j++) {
//					Privilege p = menuList.get(j);
//					if(privilege.getId() == p.getParentId()){
//						menu6.add(p);
//					}
//				}
//			}
//			resultMap.put("menu6", menu6);
//		}
		return resultMap;
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(Constants.SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));
		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, Constants.HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 根据原始的盐值，加密密码
	 * @param user
	 * @return
	 */
	public User encryptionPassword(User user) {
		byte[] salt = Encodes.decodeHex(user.getSalt());
		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, Constants.HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
		return user;
	}
	
	
	/**
	 * 保存用户
	 */
	public void saveUser(User user){
		// 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		userMapper.insert(user);
	}
	
	/**
	 * 修改密码
	 * @param user
	 */
	public void updatePassWord(User user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		userMapper.updateByPrimaryKeySelective(user);
	}
	
	
	/**
	 * 根据登录名查询用户
	 * @param loginName
	 * @return
	 */
	public User selectByLoginName(String loginName){
		return userMapper.selectByLoginName(loginName);
	}
	
	/**
	 * 根据条件查询列表
	 * @param searchMap
	 * @return
	 */
	public List<User> selectConditionList(Map<String, Object> searchMap){
		return userMapper.selectConditionList(searchMap);
	}
	
	/**
	 * 查询手机号是否存在
	 * @param phone
	 * @return
	 */
	public  int  selectPhone(String phone) {
		return userMapper .selectPhone(phone);
	}
	
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public User selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	public int updateByPrimaryKeySelective(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}
	
	/**
	 * 根据用户标识查询按钮权限
	 * @param permission
	 * @return
	 */
	public List<String> selectButtonPermission(Map<String, Object> map){
		return userPrivilegeMapper.selectButtonPermission(map);
	 }
	/**
	 * 根据手机号码 查询
	 * @param phone
	 * @return
	 */
	public  User  selectByPhone(String phone) {
		return userMapper.selectByPhone(phone);
	}
}
