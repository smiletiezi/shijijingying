package com.py.shijijingying.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.py.shijijingying.utils.Utils;

/**
 * 用户登陆后根据用户类型跳转到相应页面
 */

@Controller
@RequestMapping(value = "/index")
public class IndexController{
	@RequestMapping(value = "")
	public String loginSuccess(HttpServletRequest request,Model model) {
		String portalPath=Utils.getProperties("portal_path");
		model.addAttribute("portalPath", portalPath);
		return "index";
	}
}
