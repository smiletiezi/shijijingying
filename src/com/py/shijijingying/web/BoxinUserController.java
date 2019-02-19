package com.py.shijijingying.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Maps;
import com.py.shijijingying.annotation.SystemControllerLog;
import com.py.shijijingying.entity.BoxinUser;
import com.py.shijijingying.entity.User;
import com.py.shijijingying.service.BoxinUserService;
import com.py.shijijingying.service.UserService;
import com.py.shijijingying.utils.CommonUtil;
import com.py.shijijingying.utils.EmptyUtil;
import com.py.shijijingying.utils.JpushUtil;
import com.py.shijijingying.utils.Msg;
import com.py.shijijingying.utils.SMSBean;
import com.py.shijijingying.utils.UUIDUtils;

@Controller  
@RequestMapping("/sysuser") 
public class BoxinUserController extends BaseController{
	@Autowired  
	 private BoxinUserService boxinUserService;
	@Autowired  
	 private UserService userService;
	
	
	
	/**
	 * 自行注册
	 * @param userPhoneNumber
	 * @param userPassword
	 * @param code
	 * @param userBusinessName
	 * @param request
	 * @param rePassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/register")
	public Msg register(@RequestParam(value="userBusinessName",required=false)String userBusinessName,
			@RequestParam(value="userPhoneNumber",required=false)String userPhoneNumber,
			@RequestParam(value="userPassword",required=false)String userPassword,
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="rePassword",required=false)String rePassword){
		BoxinUser user = new BoxinUser();
		if (userBusinessName != null&& userBusinessName!="") {
			user.setUserbusinessname(userBusinessName);
		}else{
			return Msg.fail().add("mag", "企业名称不能为空");
		}
		if (userPhoneNumber != null && userPhoneNumber!="") {
			user.setUserphonenumber(userPhoneNumber);
		}else{
			return Msg.fail().add("mag", "电话号码不能为空");
		}
		if (userPassword != null && userPassword!="") {
			user.setUserpassword(userPassword);
		}else{
			return Msg.fail().add("mag", "密码不能为空");
		}
		if(code == null) {
			return Msg.fail().add("mag", "请输入验证码");
		}
		if(rePassword.equals(userPassword)==false){
			return Msg.fail().add("mag", "两次输入的密码不一致");
		}
		Msg msg = CommonUtil.verifyMobileCode(userPhoneNumber, code );
		if(msg.getCode() != 100) {
			return msg;
		}
		BoxinUser u = boxinUserService.selectByPrimary(user);
		if(u != null) {
			return Msg.fail().add("mag", "该号码已注册过");
		}
		user.setUserbusinessname(userBusinessName);
		user.setUsernumber(userPhoneNumber);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setUsercreationtime(format.format(new Date()));
		try {
			boxinUserService.insertUser(user);
			BoxinUser	use=new BoxinUser();
			use.setUserphonenumber(userPhoneNumber);
			use.setUserpassword(userPassword);
			BoxinUser ser= boxinUserService.selectByPrimary(use);
			return Msg.success(ser.getUserid());
		} catch (Exception e) {
			return Msg.fail().add("mag", "注册失败");
		}
		
	}
	
	
	/**
	 * 邀请注册
	 * @param userPhoneNumber
	 * @param userPassword
	 * @param code
	 * @param userBusinessName
	 * @param userGrourId
	 * @param rePassword
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/registerY")
	public Msg registerY(@RequestParam(value="userBusinessName",required=false)String userBusinessName,
			@RequestParam(value="userPhoneNumber",required=false)String userPhoneNumber,
			@RequestParam(value="userPassword",required=false)String userPassword,
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="userGrourId",required=false)String userGrourId,
			@RequestParam(value="rePassword",required=false)String rePassword){
		BoxinUser user = new BoxinUser();
		if (userGrourId != null && userGrourId!="") {
			user.setUsergrourid(userGrourId);
		}else{
			return Msg.fail().add("mag", "邀请码不能为空");
		}
		if (userBusinessName != null && userBusinessName!="") {
			user.setUserbusinessname(userBusinessName);
		}else{
			return Msg.fail().add("mag", "企业名称不能为空");
		}
		if (userPhoneNumber != null && userPhoneNumber!="") {
			user.setUserphonenumber(userPhoneNumber);
		}else{
			return Msg.fail().add("mag", "电话号码不能为空");
		}
		if (userPassword != null && userPassword!="" ) {
			user.setUserpassword(userPassword);
		}else{
			return Msg.fail().add("mag", "密码不能为空");
		}
		if(code == null) {
		return Msg.fail().add("mag", "请输入验证码");
		}
		if(rePassword.equals(userPassword)==false){
			return Msg.fail().add("mag", "两次输入的密码不一致");
		}
		
		Msg msg = CommonUtil.verifyMobileCode(userPhoneNumber, code );
		if(msg.getCode() != 100) {
			return msg;
		}
		BoxinUser u = boxinUserService.selectByPrimary(user);
		if(u != null) {
			return Msg.fail().add("mag", "该号码已注册过");
		}
		user.setUserbusinessname(userBusinessName);
		user.setUsernumber(userPhoneNumber);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setUsercreationtime(format.format(new Date()));
		user.setUsergrourid(userGrourId);
		try {
			boxinUserService.insertUser(user);
			BoxinUser	use=new BoxinUser();
			use.setUserphonenumber(userPhoneNumber);
			use.setUserpassword(userPassword);
			BoxinUser ser= boxinUserService.selectByPrimary(use);
			return Msg.success(ser.getUserid());
		} catch (Exception e) {
			return Msg.fail().add("mag", "注册失败");
		}
		
		
	}
	
	/**
	 * 登录
	 * @param userPhoneNumber
	 * @param userPassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login")
	public Msg login(@RequestParam(value="userBusinessName",required=false)String userBusinessName,
			                   @RequestParam(value="userPassword",required=false)String userPassword){
		BoxinUser user = new BoxinUser();
		if (userBusinessName != null && userBusinessName!="") {
			user.setUserbusinessname(userBusinessName);
			BoxinUser us = boxinUserService.selectByPrimary(user);
			if(EmptyUtil.isEmpty(us)){
				return Msg.fail().add("msg", "用户名不正确");
			}
		}else{
			return Msg.fail().add("msg", "企业名称不能为空");
		}
		if (userPassword != null && userPassword!="" ) {
			user.setUserpassword(userPassword);
			BoxinUser usr = boxinUserService.selectByPrimary(user);
			if(EmptyUtil.isEmpty(usr)){
				return Msg.fail().add("msg", "密码不正确");
			}
		}else{
			return Msg.fail().add("msg", "密码不能为空");
		}
		BoxinUser u = boxinUserService.selectByPrimary(user);
		if(u==null){
			return Msg.fail().add("msg", "用户名或者密码不正确");
		}
		SMSBean smsBean = (SMSBean) CommonUtil.MSG_MAP.get(u.getUsernumber());
		if(smsBean != null) {
			if(u != null) {
				//推送信息系统检测到您的账号在另外一台手机登录,如非本人操作请修改密码
				try {
             		JpushUtil.pushToAliasMessage("系统检测到您的账号在另外一台手机登录,如非本人操作请修改密码",u.getUserid().toString());
 				} catch (Exception e) {
 				}
			}
			CommonUtil.MSG_MAP.remove(u.getUsernumber());
		}
		SMSBean bean = new SMSBean(u.getUserid(),u.getUsernumber(),UUIDUtils.getUUID(),null);
		CommonUtil.MSG_MAP.put(u.getUsernumber(), bean);
		
		try {
			boxinUserService.updateByPrimaryKey(u);
		} catch (Exception e) {}
		//userAccount身份验证 0普通用户，1业务员，已提交待审核，2审核通过，身份为业务员,3合伙人
		//返回登陆着身份 1业务员，2是合伙人，3是普通用户 0去设置
		Msg msg = Msg.success();
		
		if(EmptyUtil.isEmpty(u.getUsergrourid())==false){
			BoxinUser ur=new BoxinUser();
			ur.setUsernumber(u.getUsergrourid());
			BoxinUser us=boxinUserService.selectByPrimary(ur);
			if(EmptyUtil.isEmpty(us.getUsergrourid())){
				msg.add("type", 2);
				msg.add("userBusinessName", us.getUserbusinessname());
				u.setUseraccount("3");
				boxinUserService.updateByPrimaryKey(u);
			}else{
				msg.add("type", 3);
				u.setUseraccount("0");
				boxinUserService.updateByPrimaryKey(u);
			}
		}else if(u.getUseraccount()==null){
			msg.add("type", 0);
		}else if(u.getUseraccount().equals("0") || u.getUseraccount().equals("1")){
			msg.add("type", 3);
		}else{
			msg.add("type", 1);
		}
		
		msg.add("userNumber", u.getUsernumber());
		msg.add("remark", u.getRemark());
		msg.add("token", bean.getId());
         return msg;
	}
	
/*
 *忘记密码	
 */
	@ResponseBody
	@RequestMapping(value="/reset")
	public Msg reset(@RequestParam(value="userPhoneNumber",required=false)String userPhoneNumber,
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="password",required=false)String password,
			@RequestParam(value="repassword",required=false)String repassword){
		BoxinUser use=new BoxinUser();
		use.setUserphonenumber(userPhoneNumber);
		BoxinUser us = boxinUserService.selectByPrimary(use);
		if(EmptyUtil.isEmpty(us)){
			return Msg.fail().add("msg", "该用户不存在");
		}else{
			if(code == null) {
				return Msg.fail().add("msg", "请输入验证码");
			}
			if(EmptyUtil.isEmpty(password)){
				return Msg.fail().add("msg", "请输入密码");
			}
			if(EmptyUtil.isEmpty(repassword)){
				return Msg.fail().add("msg", "请确认密码");
			}
			if(repassword.equals(password)==false){
				return Msg.fail().add("msg", "两次输入的密码不一致");
			}
			Msg msg = CommonUtil.verifyMobileCode(userPhoneNumber, code );
			if(msg.getCode() != 100) {
				return msg;
			}
			us.setUserpassword(password);
			try {
				boxinUserService.updateByPrimaryKey(us);
			
			} catch (Exception e) {}
			return Msg.success();
			
		}
	}
	
	/*
	 * 点击业务员身份
	 */
	@ResponseBody
	@RequestMapping(value="/partnerUpdate")
	public Msg partnerUpdate(@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="userAccount",required=false)String userAccount){
		BoxinUser user=boxinUserService.getUserById(userId);
		 if(userAccount!=null && userAccount!=""){
			  user.setUseraccount(userAccount);
		  }else{
			  user.setUseraccount("0");
		  }
		 boxinUserService.updateByPrimaryKey(user);
		   return Msg.success();
	}
	
	
	
	
 /*
  * 修改个人信息 新加业务范围
  */
	@ResponseBody
	@RequestMapping(value="/update")
	  public Msg update(@RequestParam(value="userId",required=false)Integer userId,
			  @RequestParam(value="userName",required=false)String userName,
			  @RequestParam(value="userBusinessName",required=false)String userBusinessName,
			  @RequestParam(value="userDutyNumber",required=false)String userDutyNumber,
			  @RequestParam(value="penNumber",required=false)Integer penNumber,
			  @RequestParam(value="pigNumber",required=false)Integer pigNumber, 
			  @RequestParam(value="userAddress",required=false)String userAddress,
			  @RequestParam(value="insurance",required=false)String insurance,
			  @RequestParam(value="businessRemark",required=false)String businessRemark,
			  @RequestParam(value="feedBack",required=false)String feedBack,
			  @RequestParam(value="sphere",required=false)String sphere,
			  @RequestParam(value="year",required=false)Double year
			 ) throws IllegalStateException, IOException{
	   
		BoxinUser user=boxinUserService.getUserById(userId);
	   if(userName!=null && userName!="" ){
		   user.setUsername(userName);
	   }
	 
	   if(userDutyNumber!=null &&userDutyNumber!="" ){
		   user.setUserdutynumber(userDutyNumber);
	   }
	   if(penNumber!=null){
		   user.setPennumber(penNumber);
	   }
	   if(pigNumber!=null){
		   user.setPignumber(pigNumber);
	   }
	   if(userAddress!=null && userAddress !=""){
		   user.setUseraddress(userAddress);
	   }
	  if(insurance!=null && insurance!="" ){
		  user.setInsurance(insurance);
	  }
	  if(businessRemark!=null && businessRemark!="" ){
		  user.setBusinessremark(businessRemark);
	  }
	  if(feedBack!=null && feedBack!=""){
		  user.setFeedback(feedBack);
	  }
	  if(sphere!=null && sphere!=""){
		  user.setSphere(sphere);
	  }
	   if(year!=null){
		   user.setYear(year);
	   }
	  user.setRemark("1");
	   try{
		   boxinUserService.updateByPrimaryKey(user);
		   return Msg.success();
	   }catch(Exception e){
		   return Msg.fail().add("msg", "处理失败");
	   }
	  }
	
	/*
	 * 修改头像
	 */
	@ResponseBody
	@RequestMapping(value="/img")
	public Msg img(@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="userImg",required=false)String userImg){
		if(userId!=null){
			BoxinUser user=boxinUserService.getUserById(userId);
			if(userImg!=null && userImg!=""){
				user.setUserimg(userImg);
				try{
					boxinUserService.updateByPrimaryKey(user);
					return Msg.success();
				}catch(Exception e){
					return Msg.fail().add("msg", "处理失败");
				}
				
			}else{
				return Msg.fail().add("msg", "请上传一张头像");
			}
		}else{
			return Msg.fail().add("msg", "用户Id为空");
		}
		
	}
	/*
	 * 点击设置
	 */
	@ResponseBody
	@RequestMapping(value="/select")
	public Msg select(@RequestParam(value="userId",required=false)Integer userId){
		BoxinUser u=new BoxinUser();
		   u.setUserid(userId);
		   try{
			  BoxinUser user=boxinUserService.selectByPrimary(u);
		   return Msg.success(user);
		   }catch(Exception e){
			   return Msg.fail().add("msg", "处理失败");
		   }
	}
	
	/*
	 * 设置完个人信息后执行 用户区域管理 把自行注册的普通用户归属到他所在区域的合伙人下
	 */
	@ResponseBody
	@RequestMapping(value="/area")
	public Msg area(@RequestParam(value="userId",required=false)Integer userId){
		BoxinUser user=boxinUserService.getUserById(userId);
		if(StringUtil.isEmpty(user.getUsergrourid()) && user.getUseraccount().equals("0") && user.getUseraddress()!=null){
			BoxinUser use=new BoxinUser();//获取所有合伙人
			use.setUseraccount("3");
			List<BoxinUser> users=boxinUserService.selectByExample(use);
			if(users.size()>0) {
				for(int i=0; i<users.size();i++){
					if(StringUtil.isNotEmpty(users.get(i).getSphere())){
						//合伙人业务范围不为空
						String[] spheres=users.get(i).getSphere().split(",");
						for(int k=0;k<spheres.length;k++) {
							if(user.getUseraddress().contains(spheres[k])){
								user.setUsergrourid(users.get(i).getUsernumber());
								boxinUserService.updateByPrimaryKey(user);
							}
						}
					}
				}
			}
			
		}else{
			return Msg.fail().add("msg", "请先完善个人信息");
		}
		return Msg.success();
	}
	
	/*
	 * 获取客服电话
	 */
	@ResponseBody
	@RequestMapping(value="/getAccount")
	public Msg getAccount() {
		User user=userService.selectByPrimaryKey(1);
		return Msg.success(user.getPhone());
	}
/********************************************后台管理************************************/	
	
	/**
	 * 跳转业务员列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toList")
	public String toList(HttpServletRequest request,Model model) {
		return "jsp/boxinUserList";
	}	
	
	/**
	 * 获取业务员列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询业务员列表")
	@RequestMapping(value = "toListData")
	@ResponseBody
	public Map<String,Object> toListData(HttpServletRequest request) {
		String userbusinessname=request.getParameter("userbusinessname");
		BoxinUser user=new BoxinUser();
		user.setUseraccount("2");
		if(StringUtil.isNotEmpty(userbusinessname)) {
			user.setUserbusinessname(userbusinessname);
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("userid ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<BoxinUser> list = boxinUserService.selectByExample(user);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}
	
	/**
	 * 根据ID查询用户详情
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@SystemControllerLog(description="查询用户详情")
	@RequestMapping("toDetails")
	public String toDetails(HttpServletRequest request, Model model) {
		Integer id=Integer.parseInt(request.getParameter("userid"));
		BoxinUser obj = boxinUserService.getUserById(id);
		model.addAttribute("obj",obj);
		return "jsp/boxinUserDetails";
	}
	
	
	/**
	 * 跳转业务员form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toForm")
	public String getUserList(HttpServletRequest request,Model model) {
			Integer id= Integer.parseInt(request.getParameter("id"));
			BoxinUser obj=boxinUserService.getUserById(id);
		    model.addAttribute("obj",obj);
		return "jsp/boxinUserForm";
	}
	
	
	/**
	 * 跳转到合伙人form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toDown")
	public String toDown(HttpServletRequest request,Model model) {
			Integer id= Integer.parseInt(request.getParameter("userid"));
			BoxinUser obj=boxinUserService.getUserById(id);
		    model.addAttribute("obj",obj);
		return "jsp/boxinDownList";
	}
	
	/**
	 * 跳转到普通用户form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toDownOne")
	public String toDownOne(HttpServletRequest request,Model model) {
			Integer id= Integer.parseInt(request.getParameter("userid"));
			BoxinUser obj=boxinUserService.getUserById(id);
		    model.addAttribute("obj",obj);
		return "jsp/boxinDownListOne";
	}
	
	
	/**
	 * 获取合伙人列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询业务员列表")
	@RequestMapping(value = "toDownData")
	@ResponseBody
	public Map<String,Object> toDownData(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("userid"));
		BoxinUser boxin=boxinUserService.getUserById(id);
		String userbusinessname=request.getParameter("userbusinessname");
		BoxinUser user=new BoxinUser();
		user.setUsergrourid(boxin.getUsernumber());
		if(StringUtil.isNotEmpty(userbusinessname)) {
			user.setUserbusinessname(userbusinessname);
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("userid ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<BoxinUser> list = boxinUserService.selectByExample(user);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}
	
	/**
	 * 获取普通用户列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询普通用户列表")
	@RequestMapping(value = "toDownDataOne")
	@ResponseBody
	public Map<String,Object> toDownDataOne(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("userid"));
		BoxinUser boxin=boxinUserService.getUserById(id);
		String userbusinessname=request.getParameter("userbusinessname");
		BoxinUser user=new BoxinUser();
		user.setUsergrourid(boxin.getUsernumber());
		if(StringUtil.isNotEmpty(userbusinessname)) {
			user.setUserbusinessname(userbusinessname);
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("userid ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<BoxinUser> list = boxinUserService.selectByExample(user);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}
	/*
	 * 设定年目标
	 */
	@SystemControllerLog(description="设定用户")
	@RequestMapping(value="set")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request,@ModelAttribute("obj") BoxinUser user) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		BoxinUser boxin=boxinUserService.getUserById(user.getUserid());
		boxin.setYear(user.getYear());
		boxin.setSphere(user.getSphere());
		boxinUserService.updateByPrimaryKey(boxin);
		resultMap.put("type", "update");
		resultMap.put("code", "success");
		return resultMap;
	} 
	

	
	/**
	 * 跳转待审核业务员列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toPartenerList")
	public String toPartenerList(HttpServletRequest request,Model model) {
		return "jsp/boxinPartenerList";
	}	
	
	/**
	 * 获取待审核业务员列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询业务员列表")
	@RequestMapping(value = "toExamineList")
	@ResponseBody
	public Map<String,Object> toExamineList(HttpServletRequest request) {
		String userbusinessname=request.getParameter("userbusinessname");
		BoxinUser user=new BoxinUser();
		user.setUseraccount("1");
		if(StringUtil.isNotEmpty(userbusinessname)) {
			user.setUserbusinessname(userbusinessname);
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("userid ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<BoxinUser> list = boxinUserService.selectByExample(user);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}
	
	/**
	 * 跳转业务员审核form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toDetailsForm")
	public String toDetailsForm(HttpServletRequest request,Model model) {
			Integer id= Integer.parseInt(request.getParameter("id"));
			BoxinUser obj=boxinUserService.getUserById(id);
		    model.addAttribute("obj",obj);
		return "jsp/boxinDetailsForm";
	}
	
	/*
	 * 业务员审核
	 */
	@SystemControllerLog(description="设定用户")
	@RequestMapping(value="examine")
	@ResponseBody
	public Map<String, Object> examine(HttpServletRequest request,@ModelAttribute("obj") BoxinUser user) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		BoxinUser boxin=boxinUserService.getUserById(user.getUserid());
		boxin.setUseraccount(user.getUseraccount());
		boxinUserService.updateByPrimaryKey(boxin);
		resultMap.put("type", "update");
		resultMap.put("code", "success");
		return resultMap;
	} 
	
	
	/*************************************************合伙人管理*******************************/
	/**
	 * 跳转合伙人列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toPartnerList")
	public String toPartnerList(HttpServletRequest request,Model model) {
		return "jsp/partnerList";
	}	
	
	/**
	 * 获取合伙人列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询合伙人列表")
	@RequestMapping(value = "toPartnerListData")
	@ResponseBody
	public Map<String,Object> toPartnerListData(HttpServletRequest request) {
		String userbusinessname=request.getParameter("userbusinessname");
		BoxinUser user=new BoxinUser();
		user.setUseraccount("3");
		if(StringUtil.isNotEmpty(userbusinessname)) {
			user.setUserbusinessname(userbusinessname); 
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("userid ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<BoxinUser> list = boxinUserService.selectByExample(user);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}
	
	
	/**
	 * 跳转到业务员form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toUp")
	public String toUp(HttpServletRequest request,Model model) {
			Integer id= Integer.parseInt(request.getParameter("userid"));
			BoxinUser obj=boxinUserService.getUserById(id);
		    model.addAttribute("obj",obj);
		return "jsp/toUpList";
	}	
	
	/**
	 * 获取合伙人的上级列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询业务员列表")
	@RequestMapping(value = "toUpListData")
	@ResponseBody
	public Map<String,Object> toUpListData(HttpServletRequest request) {
		//返回map
		Map<String,Object> resultMap = Maps.newHashMap();
		Integer id=Integer.parseInt(request.getParameter("userid"));
		BoxinUser users=boxinUserService.getUserById(id);
		BoxinUser user=new BoxinUser();
		if(StringUtil.isNotEmpty(users.getUsergrourid())) {
			user.setUsernumber(users.getUsergrourid());
			
			//获取分页和排序条件
			LayerPage(request);
			//排序插件
			PageHelper.orderBy("userid ASC");
			//分页插件
			Page<?> page = PageHelper.startPage(pageNum, pageSize);
	//调用service
	
	List<BoxinUser> list = boxinUserService.selectByExample(user);
	//返回layui数据
			resultMap.put("code", 0);
			resultMap.put("msg", "查询成功");
			resultMap.put("count", page.getTotal());
			resultMap.put("data", list);
			return resultMap;
		}else {
			//获取分页和排序条件
			LayerPage(request);
			//排序插件
			PageHelper.orderBy("userid ASC");
			//分页插件
			Page<?> page = PageHelper.startPage(pageNum, pageSize);
			List<BoxinUser> list =new ArrayList<BoxinUser>();
			//返回layui数据
			resultMap.put("code", 0);
			resultMap.put("msg", "查询成功");
			resultMap.put("count", page.getTotal());
			resultMap.put("data", list);
			return resultMap;
		}
	}
	
	/*************************************************普通用户管理************************/
	
	/**
	 * 跳转所有普通用户列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toAllUserList")
	public String toAllUserList(HttpServletRequest request,Model model) {
		return "jsp/allUserList";
	}	
	
	/**
	 * 获取普通用户-列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询普通用户列表")
	@RequestMapping(value = "toAllUserListData")
	@ResponseBody
	public Map<String,Object> toAllUserListData(HttpServletRequest request) {
		String userbusinessname=request.getParameter("userbusinessname");
		BoxinUser user=new BoxinUser();
		user.setUseraccount("0");
		if(StringUtil.isNotEmpty(userbusinessname)) {
			user.setUserbusinessname(userbusinessname);
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("userid ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<BoxinUser> list = boxinUserService.selectByExample(user);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}
	
	/**
	 * 跳转到业务员form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toUpAll")
	public String toUpAll(HttpServletRequest request,Model model) {
			Integer id= Integer.parseInt(request.getParameter("userid"));
			BoxinUser obj=boxinUserService.getUserById(id);
		    model.addAttribute("obj",obj);
		return "jsp/toUpAllList";
	}	
	
	/**********************************************用户删除************************************/
	
	/**
	 * 跳转所有用户列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toUserList")
	public String toUserList(HttpServletRequest request,Model model) {
		return "jsp/boxinToUserList";
	}	
	
	/**
	 * 获取所有用户-列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询用户列表")
	@RequestMapping(value = "boxinToUserList")
	@ResponseBody
	public Map<String,Object> boxinToUserList(HttpServletRequest request) {
		String userbusinessname=request.getParameter("userbusinessname");
		BoxinUser user=new BoxinUser();
		if(StringUtil.isNotEmpty(userbusinessname)) {
			user.setUserbusinessname(userbusinessname);
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("userid ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<BoxinUser> list = boxinUserService.selectByExample(user);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}

	
	/**
	 * 删除用户 
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="删除用户")
	@RequestMapping(value="redelete")
	@ResponseBody
	public Map<String, Object> redelete(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		boxinUserService.deleteByPrimaryKey(id);
		resultMap.put("type", "delete");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * 获取待审核业务员
	 */
	@ResponseBody
	@RequestMapping(value="/salesmanExamine")
	public Msg salesmanExamine(@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="pageSize",required=false)Integer pageSize){
		BoxinUser user=new BoxinUser();
		user.setUseraccount("1");
		//排序插件
		PageHelper.orderBy("userId asc");
		//分页插件
		if(pageNum==null){
			pageNum=1;
		}
		if(pageSize==null){
			pageSize=10;
		}
		
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		List<BoxinUser> users=new ArrayList<BoxinUser>();
		try{
		  users=boxinUserService.selectByExample(user);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
		return Msg.success(users);
	}
	
	/*
	 * 后台用户检索/根据用户名查询
	 */
	@ResponseBody
	@RequestMapping(value="/userList")
	public Msg userList(@RequestParam(value="userBusinessName",required=false)String userBusinessName){
		BoxinUser user=new BoxinUser();
		user.setUserbusinessname(userBusinessName);
		try {
			BoxinUser users=boxinUserService.selectByPrimary(user);
			return Msg.success(users);
		} catch (Exception e) {
			return Msg.fail().add("msg", "处理失败");
		}
		
		
		
	}
	
	
	/*
	 * 获取所有业务员
	 */
	@ResponseBody
	@RequestMapping(value="/salesmanList")
	public Msg salesmanList(@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="pageSize",required=false)Integer pageSize){
		BoxinUser user=new BoxinUser();
		user.setUseraccount("2");
		//排序插件
				PageHelper.orderBy("userId asc");
				//分页插件
				if(pageNum==null){
					pageNum=1;
				}
				if(pageSize==null){
					pageSize=10;
				}
				
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		List<BoxinUser> users=new ArrayList<BoxinUser>();
		try{
		  users=boxinUserService.selectByExample(user);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
		return Msg.success(users);
	}
	
	
	/*
	 * 点击某个业务员 产看该业务员下所有合伙人
	 */
	@ResponseBody
	@RequestMapping(value="/partnerList")
	public Msg partnerList(@RequestParam String userNumber,
			@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="pageSize",required=false)Integer pageSize){
		BoxinUser user =new BoxinUser();
		user.setUsergrourid(userNumber);
		//排序插件
		PageHelper.orderBy("userId asc");
		//分页插件
		if(pageNum==null){
			pageNum=1;
		}
		if(pageSize==null){
			pageSize=10;
		}
		
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		List<BoxinUser> users=new ArrayList<BoxinUser>();
		try{
		  users=boxinUserService.selectByExample(user);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
		return Msg.success(users);
	}
	
	/*
	 * 点击某个合伙人 产看该合伙人下所有普通用户
	 */
	@ResponseBody
	@RequestMapping(value="/mynormalList")
	public Msg mynormalList(@RequestParam String userNumber,
			@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="pageSize",required=false)Integer pageSize){
		BoxinUser user =new BoxinUser();
		user.setUsergrourid(userNumber);
		//排序插件
				PageHelper.orderBy("userId asc");
				//分页插件
				if(pageNum==null){
					pageNum=1;
				}
				if(pageSize==null){
					pageSize=10;
				}
				
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		List<BoxinUser> users=new ArrayList<BoxinUser>();
		try{
		  users=boxinUserService.selectByExample(user);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
		return Msg.success(users);
	}
	
	
	
	
}
