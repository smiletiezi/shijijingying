package com.py.shijijingying.web;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.py.shijijingying.Constants;
import com.py.shijijingying.annotation.SystemControllerLog;
import com.py.shijijingying.entity.SysRoleUser;
import com.py.shijijingying.entity.Sysrole;
import com.py.shijijingying.entity.User;
import com.py.shijijingying.service.SysRoleService;
import com.py.shijijingying.service.SysRoleUserService;
import com.py.shijijingying.service.UserService;
import com.py.shijijingying.utils.Utils;

/**
 * 用户控制器
 */

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleUserService sysRoleUserService;
	
	
	/***************************************************list*********************************************************/
	
	/**
	 * 跳转用户列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toList")
	public String toList(HttpServletRequest request,Model model) {
		return "jsp/userList";
	}
	
	/**
	 * 获取用户列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询用户列表")
	@RequestMapping(value = "toListData")
	@ResponseBody
	public Map<String,Object> toListData(HttpServletRequest request) {
		//获取登录用户id
		User loginuser=getCurrentUser();
		//返回map
		Map<String,Object> resultMap = Maps.newHashMap();
		//条件map
		Map<String,Object> searchMap = Maps.newHashMap();
		//获取分页和排序条件
		LayerPage(request);
		//获取搜索条件
		searchMap.put("userId", loginuser.getId());
		//排序插件
		PageHelper.orderBy("id ASC");
		//分页插件
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		List<User> list = userService.selectuserIdrole(searchMap);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}
	
	/***************************************************add or update*********************************************************/
	
	/**
	 * 跳转form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toForm")
	public String getUserList(HttpServletRequest request,Model model) {
		int id = 0;
		try {
			id= Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			id = 0;
		}
		if(id > 0) {
			User user = userService.selectByPrimaryKey(id);
			model.addAttribute("obj", user);
		}
		//获取登录用户id
		User loginuser=getCurrentUser();
		List<Sysrole> role=sysRoleService.selectUserIdRole(loginuser.getId());
		model.addAttribute("role",role);
		return "jsp/userForm";
	}
	
	
	/**
	 * 新增用户  
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="新增用户")
	@RequestMapping(value="insert")
	@ResponseBody
	public Map<String, Object> insert(HttpServletRequest request,@ModelAttribute("obj") User user) {
		int type=0;
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		//当前登录用户
		User loginuser=getCurrentUser();
		//获取该新增用户的角色id
		type=Integer.parseInt(request.getParameter("type"));
		//获取配置文件里的初始密码
		String initPassWord = Utils.getProperties("init_password");
		user.setPlainPassword(initPassWord);
		user.setCreateUser(loginuser.getId());
		user.setCreateTime(new Date());
		user.setIsDelete(false);
		userService.saveUser(user);
		//新增用户角色表数据
		SysRoleUser roleUser=new SysRoleUser();
		roleUser.setRoleId(type);
		roleUser.setUserId(user.getId());
		sysRoleUserService.insertSelective(roleUser);
		resultMap.put("type", "add");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	
	/**
	 * 修改用户  
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="修改用户")
	@RequestMapping(value="update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request,@ModelAttribute("obj") User user) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		//当前登录用户
		User loginuser=getCurrentUser();
		user.setUpdateUser(loginuser.getId());
		user.setUpdateTime(new Date());
		userService.updateByPrimaryKeySelective(user);
		resultMap.put("type", "update");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/***************************************************delete*********************************************************/
	
	
	/**
	 * 删除用户 (软删)
	 * @param request
	 * @param model
	 * @return
	 */
	@SystemControllerLog(description="删除用户")
	@RequestMapping(value = "delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,Model model) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			id = 0;
		}
		User user = new User();
		user.setId(id);
		user.setIsDelete(true);
		userService.updateByPrimaryKeySelective(user);
		resultMap.put("type", "delete");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/**
	 * 批量删除用户 (软删)
	 * @param request
	 * @param model
	 * @return
	 */
	@SystemControllerLog(description="批量删除用户")
	@RequestMapping(value = "batchDelete")
	@ResponseBody
	public Map<String, Object> batchDelete(HttpServletRequest request,Model model,@RequestParam("ids[]") int[] ids) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		for (int id : ids) {
			User user = new User();
			user.setId(id);
			user.setIsDelete(true);
			userService.updateByPrimaryKeySelective(user);
		}
		resultMap.put("type", "delete");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	
	/***************************************************details*********************************************************/
	
	
	/**
	 * 根据ID查询详情
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@SystemControllerLog(description="查询用户详情")
	@RequestMapping("toDetails")
	public String toDetails(HttpServletRequest request, Model model) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		User obj = userService.selectByPrimaryKey(id);
		SysRoleUser roleUs=new SysRoleUser();
		roleUs.setUserId(id);
		//用户角色对应表
		SysRoleUser roleUser=sysRoleUserService.selectBySysRoleUser(roleUs);
		//角色表
		Sysrole role =sysRoleService.selectByPrimaryKey(roleUser.getRoleId());
		model.addAttribute("role",role);
		model.addAttribute("obj",obj);
		return "jsp/userDetails";
	}
	
	
	/***************************************************password*********************************************************/
	
	/**
	 * 批量重置密码
	 * @param request
	 * @param model
	 * @param ids
	 * @return
	 */
	@SystemControllerLog(description="批量重置用户密码")
	@RequestMapping(value = "batchResetPassword")
	@ResponseBody
	public Map<String, Object> batchResetPassword(HttpServletRequest request,Model model,@RequestParam("ids[]") int[] ids) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		//获取配置文件里的初始密码
		String initPassWord = Utils.getProperties("init_password");
		for (int id : ids) {
			User user = new User();
			user.setId(id);
			user.setPlainPassword(initPassWord);
			userService.updatePassWord(user);
		}
		resultMap.put("type", "reset");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/**
	 * 跳转修改密码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toUpdatePassWord")
	public String toUpdatePassWord(HttpServletRequest request,Model model) {
		User user = getCurrentUser();
		model.addAttribute("obj", user);
		return "jsp/userPassWord";
	}
	
	
	/**
	 * 修改密码
	 * @param request
	 * @param model
	 * @return
	 */
	@SystemControllerLog(description="修改密码")
	@RequestMapping(value = "updatePassWord")
	@ResponseBody
	public Map<String, Object> updatePassWord(HttpServletRequest request,Model model) {
		User user = getCurrentUser();
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		String oldpass = request.getParameter("oldpass");		
		String newpass = request.getParameter("newpass");		
		String repeatpass = request.getParameter("repeatpass");		
		String[] values = {oldpass,newpass,repeatpass};
		if(!Utils.isNotNull(values)) {
			//参数为空
			resultMap.put("type", "password");
			resultMap.put("code", "parameter");
			return resultMap;
		}
		//验证旧密码是否正确
		User obj = new User();
		obj.setPlainPassword(oldpass);
		obj.setSalt(user.getSalt());
		obj = userService.encryptionPassword(obj);
		if(!obj.getPassword().equals(user.getPassword())) {
			//旧密码不正确
			resultMap.put("type", "password");
			resultMap.put("code", "incorrect");
			return resultMap;
		}
		
		//验证新密码与旧密码是否一致
		obj.setPlainPassword(newpass);
		obj.setSalt(user.getSalt());
		obj = userService.encryptionPassword(obj);
		if(obj.getPassword().equals(user.getPassword())) {
			//新密码与旧密码不能相同
			resultMap.put("type", "password");
			resultMap.put("code", "agreement");
			return resultMap;
		}
		
		//验证新密码和重复密码是否一致
		if(!newpass.equals(repeatpass)) {
			//新密码和重复密码不一致
			resultMap.put("type", "password");
			resultMap.put("code", "atypism");
			return resultMap;
		}
		
		//修改密码
		obj.setId(user.getId());
		userService.updateByPrimaryKeySelective(obj);
		//更新session中的旧密码
		user.setPassword(obj.getPassword());
		Session session  = SecurityUtils.getSubject().getSession();
		session.removeAttribute(Constants.CURRENT_USER);
		session.setAttribute(Constants.CURRENT_USER, user);
		resultMap.put("type", "password");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	
	/***************************************************state*********************************************************/
	/**
	 * 修改用户状态
	 * @param request
	 * @param model
	 * @return
	 */
	@SystemControllerLog(description="修改用户状态")
	@RequestMapping(value = "updateState")
	@ResponseBody
	public Map<String, Object> updateState(HttpServletRequest request,Model model) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			id = 0;
		}
		int state = -1;
		try {
			state = Integer.parseInt(request.getParameter("state"));
		} catch (Exception e) {
			state = -1;
		}
		User user = new User();
		user.setId(id);
		if(state == 0) {
			user.setState(false);
		}
		if(state == 1) {
			user.setState(true);
		}
		userService.updateByPrimaryKeySelective(user);
		resultMap.put("type", "reset");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	
}
