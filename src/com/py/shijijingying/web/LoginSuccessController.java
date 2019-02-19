package com.py.shijijingying.web;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.py.shijijingying.Constants;
import com.py.shijijingying.service.ShiroDbRealm.ShiroUser;


/**
 * 用户登录成功处理器
 */

@Controller
@RequestMapping(value = "/loginSuccess")
public class LoginSuccessController {
	
	@RequestMapping(value = "")
	public String loginSuccess(HttpServletRequest request,Model model) {
		ShiroUser user = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		if(user != null){
			return "redirect:/index";
		}
		SecurityUtils.getSubject().getSession().setAttribute(Constants.CURRENT_USER, null);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, "typeError");
		return "login";
	}
}
