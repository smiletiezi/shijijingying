package com.py.shijijingying.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.py.shijijingying.annotation.SystemControllerLog;
import com.py.shijijingying.entity.Privilege;
import com.py.shijijingying.entity.RolePrivilege;
import com.py.shijijingying.entity.Sysrole;
import com.py.shijijingying.entity.User;
import com.py.shijijingying.service.RoleRrivilegeService;
import com.py.shijijingying.service.SysRoleService;
import com.py.shijijingying.service.UserService;
import com.py.shijijingying.utils.Utils;

@Controller
@RequestMapping("sysRole")
public class SysRoleController  extends BaseController{
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private RoleRrivilegeService roleRrivilegeService;
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转角色列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "roletoList")
	public String roleToList(HttpServletRequest request,Model model) {
		
		return "jsp/roleList";
	}
	
	
	
	/**
	 * 获取角色列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询用户列表")
	@RequestMapping(value = "toListData")
	@ResponseBody
	public Map<String,Object> toListData(HttpServletRequest request) {
		//获取用户id
		User user=getCurrentUser();
		//返回map
		Map<String,Object> resultMap = Maps.newHashMap();
		//条件map
		Map<String,Object> searchMap = Maps.newHashMap();
		//获取分页和排序条件
		LayerPage(request);
		//获取搜索条件
		String name = request.getParameter("name");
		searchMap.put("name", name);
		searchMap.put("userId", user.getId());
		//排序插件
		PageHelper.orderBy("id");
		//分页插件
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		List<Sysrole> roleList = sysRoleService.selectUserIdRoleByName(searchMap);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", roleList);
		return resultMap;
	}
	
	
	/**
	 * 跳转form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "roleToForm")
	public String getUserList(HttpServletRequest request,Model model) {
		int id = 0;
		try {
			id= Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			id = 0;
		}
		if(id > 0) {
			Sysrole sysrole = sysRoleService.selectByPrimaryKey(id);
			model.addAttribute("obj", sysrole);
			//根据角色查询全选 并拼接
			List<Privilege> privilege= sysRoleService.selectRoleAndPrivilege(id);
		    StringBuffer privilegeId = new StringBuffer();
		    StringBuffer privilegeName = new StringBuffer();
		    for (int i = 0; i < privilege.size(); i++) {
		    	privilegeId.append(privilege.get(i).getId());
		    	privilegeName.append(privilege.get(i).getName());
	    	   if ((i + 1) != privilege.size()) {
	    		   privilegeId.append(",");
	    		   privilegeName.append(",");
	            }
		    }
			model.addAttribute("privilegeId", privilegeId);
			model.addAttribute("privilegeName", privilegeName);
		}
		List<Sysrole> allsysrole=sysRoleService.selectAllRole();
		model.addAttribute("allrole",allsysrole);
		return "jsp/roleFrom";
	}
	
	
	//跳转角色from
	@RequestMapping(value = "toSelectRoleForm")
	public String toSelectRoleForm(HttpServletRequest request,Model model) {
/*		int id = 0;
		try {
			id= Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			id = 0;
		}
		if(id > 0) {
			Role role = roleSerivce.selectByPrimaryKey(id);
			model.addAttribute("role", role);
		}*/
	
		return "jsp/selectPrivilegeFrom";
	}
	
	/**
	 * 权限列表数据
	 */
	@SystemControllerLog(description="权限列表数据")
	@RequestMapping(value = "rolePrivilege")
	@ResponseBody
	public List<Map<String, Object>>  role1(HttpServletRequest req,Model model) {
		List<Map<String, Object>> objlist = new ArrayList<Map<String, Object>>();
		List<Privilege> list=sysRoleService.selectAllprivilege();
		for (Privilege  privilege: list) {
			Map<String, Object> map=Maps.newHashMap();
			map.put("id", privilege.getId() == null ? "":privilege.getId());
			map.put("name", privilege.getName() == null ? "":privilege.getName());
			map.put("pId", privilege.getParentId() == null ? "": privilege.getParentId());
			objlist.add(map);
		}
		
		return objlist;
	}
	
	/**
	 * 跳转选择所有角色
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toSelectAllRoleForm")
	public String toSelectAllRoleForm(HttpServletRequest request,Model model) {
		return "jsp/Role/selectAllRoleFrom";
	}
	
	
	
	
	/**
	 * 选择下级角色
	 */
	@SystemControllerLog(description="选择下级角色")
	@RequestMapping(value = "belowRole")
	@ResponseBody
	public List<Map<String, Object>>  belowRole(HttpServletRequest req,Model model) {
		List<Map<String, Object>> objlist = new ArrayList<Map<String, Object>>();
		List<Sysrole> list=sysRoleService.selectAllRole();
		for (Sysrole  sysrole: list) {
			Map<String, Object> map=Maps.newHashMap();
			map.put("id", sysrole.getId() == null ? "":sysrole.getId());
			map.put("name", sysrole.getName() == null ? "":sysrole.getName());
		//	map.put("pId", sysrole.getParentId() == null ? "": sysrole.getParentId());
			map.put("pId", 0);
			objlist.add(map);
		}
		
		return objlist;
	}
	
	
	/**
	 * 新增角色  
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="新增角色 ")
	@Transactional
	@RequestMapping(value="insertRole")
	@ResponseBody
	public Map<String, Object> insertRole(HttpServletRequest request,Sysrole role) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		User user =getCurrentUser();
		//增加角色
		role.setCreateUserId(user.getId());
		role.setCreateTime(new Date());
		//当前登录id的角色
	    Sysrole sysrole =sysRoleService.selectByUseridRole(user.getId());
		//获取页面数据
		String superior =request.getParameter("parentId");
		//如果选择上级 就选择上级的权限拿来
		if(Utils.isNotNull(superior)  ) {
		    Sysrole sysrole1=sysRoleService.selectByPrimaryKey(Integer.parseInt(superior));
			if(sysrole!=null) {
				role.setRemark(sysrole1.getRemark()+sysrole1.getId()+".");;
			}
		}else {
			//不选择 默认为当前登录用户的下一级
			role.setRemark(sysrole.getRemark()+sysrole.getId()+".");
			
		}
		sysRoleService.insertSelective(role);
		String opt=request.getParameter("optid");
		if(Utils.isNotNull(opt)) {
			String[] strarr = opt.split(",");
			//遍历选择权限 存入
			for (int i = 0; i < strarr.length; i++) {
				RolePrivilege rolePrivilege=new RolePrivilege();
				rolePrivilege.setRoleId(role.getId());
				rolePrivilege.setPrivilegeId(Integer.parseInt(strarr[i]));
				rolePrivilege.setCreateUserId(user.getId());
				rolePrivilege.setCreateTime(new Date());
				roleRrivilegeService.insertSelective(rolePrivilege);
			}
		}
		resultMap.put("type", "add");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	
	

	/**
	 * 修改角色  
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="修改角色 ")
	@Transactional
	@RequestMapping(value="updateRole")
	@ResponseBody
	public Map<String, Object> updateRole(HttpServletRequest request, Sysrole  role) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		//当前登录用户
		User user=getCurrentUser();
		role.setUpdateUserId(user.getId());
		role.setUpdateTime(new Date());
		sysRoleService.updateByPrimaryKeySelective(role);
		roleRrivilegeService.deleteByUserIdAll(role.getId());
		String opt=request.getParameter("optid");
		if(Utils.isNotNull(opt)) {
			String[] strarr = opt.split(",");
			//遍历选择权限 存入
			for (int i = 0; i < strarr.length; i++) {
				RolePrivilege rolePrivilege=new RolePrivilege();
				rolePrivilege.setRoleId(role.getId());
				rolePrivilege.setPrivilegeId(Integer.parseInt(strarr[i]));
				rolePrivilege.setCreateUserId(user.getId());
				rolePrivilege.setCreateTime(new Date());
				roleRrivilegeService.insertSelective(rolePrivilege);
			}
			resultMap.put("type", "update");
			resultMap.put("code", "success");
		}else {
			resultMap.put("type", "update");
			resultMap.put("code", "mistake");
		}
		return resultMap;
	}
	
	/**
	 * 删除角色 (软删)
	 * @param request
	 * @param model
	 * @return
	 */
	@SystemControllerLog(description="删除角色 (软删)")
	@RequestMapping(value = "deleteRole")
	@ResponseBody
	public Map<String, Object> deleteRole(HttpServletRequest request,Model model) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			id = 0;
		}
		Sysrole  role=new Sysrole();
		role.setId(id);
		role.setIsDelete(1);
		sysRoleService.updateByPrimaryKeySelective(role);
		resultMap.put("type", "delete");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/**
	 * 批量删除角色 (软删)
	 * @param request
	 * @param model
	 * @return
	 */
	@SystemControllerLog(description="批量删除角色 (软删)")
	@RequestMapping(value = "batchDelete")
	@ResponseBody
	public Map<String, Object> batchDelete(HttpServletRequest request,Model model,@RequestParam("ids[]") int[] ids) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		for (int id : ids) {
			Sysrole role=new Sysrole();
			role.setId(id);
			role.setIsDelete(1);
			sysRoleService.updateByPrimaryKeySelective(role);
		}
		resultMap.put("type", "delete");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	
}
