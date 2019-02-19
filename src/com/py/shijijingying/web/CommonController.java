package com.py.shijijingying.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.exceptions.ClientException;
import com.py.shijijingying.entity.BoxinUser;
import com.py.shijijingying.service.BoxinUserService;
import com.py.shijijingying.utils.JpushUtil;
import com.py.shijijingying.utils.Msg;
import com.py.shijijingying.utils.SMSBean;
import com.py.shijijingying.utils.SendMSMUtil;

@Controller
@RequestMapping("/common")
public class CommonController {
	@Autowired  
	 private BoxinUserService boxinUserService;

	/**
	 * 获取验证码
	 * @param phonenum
	 * @param type
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCode")
	public Msg getCode(@RequestParam(value="userPhoneNumber",required=false)String userPhoneNumber,@RequestParam(value="type",required=false)String type) {
		if(userPhoneNumber == null || "".equals(userPhoneNumber)) {
			return Msg.fail().add("msg", "电话号码不能为空");
		}
		BoxinUser user = new BoxinUser();
		user.setUserphonenumber(userPhoneNumber);
		 String smsTpl = null;
		if("register".equals(type)) {
			smsTpl = SendMSMUtil.COMMON_TEMPLATE;
			BoxinUser u = boxinUserService.selectByPrimary(user);
			if(u != null) {
				return Msg.fail().add("msg", "该手机已注册");
			}
		}else {
			BoxinUser u = boxinUserService.selectByPrimary(user);
			if(u == null) {
				return Msg.fail().add("msg", "该手机未注册");
			}
			smsTpl = SendMSMUtil.COMMON_TEMPLATE_UPDATE;
		}try{
	        SMSBean smsBean = SendMSMUtil.sendMSM(userPhoneNumber, smsTpl, true, null);
	        if (smsBean == null) {
	        	return Msg.fail().add("msg", "短信发送失败");
	        } else { 
	        	return Msg.success();
	        }
	      }catch (ClientException e){
	        e.printStackTrace();
	        return Msg.fail().add("msg", "短信发送失败");
	      }
	}
	
	/*
	 * 后台选择用户发送手机短信
	 */
	@ResponseBody
	@RequestMapping(value="/sendMsg")
	public Msg sendMsg(@RequestParam(value="userPhoneNumber",required=false)String userPhoneNumber,
			@RequestParam(value="tpl_id",required=false)String tpl_id) throws ClientException{
		List<String> phones=Arrays.asList(userPhoneNumber.split(","));
		for(int i=0;i<phones.size();i++){
			SMSBean smsBean = SendMSMUtil.sendMSM(phones.get(i), tpl_id, true, null);
			 if (smsBean == null) {
		        	return Msg.fail().add("msg", "用户"+phones.get(i)+"短信发送失败");
		        } else { 
		        	return Msg.success();
		        }
		}
		return Msg.success();
	}
	/*
	 * 后台选择用户 推送消息
	 */
	@ResponseBody
	@RequestMapping(value="/sendCode")
	public Msg sendCode(@RequestParam(value="userIds",required=false)String userIds,
			@RequestParam(value="msg",required=false)String msg){
		List<String> users=Arrays.asList(userIds.split(","));
		for(int i=0;i<users.size();i++){
			JpushUtil.pushToAliasMessage(msg,users.get(i).toString());
		}
		return Msg.success();
	}
}
