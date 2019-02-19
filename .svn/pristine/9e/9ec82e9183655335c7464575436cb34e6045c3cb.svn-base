package com.py.shijijingying.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.py.shijijingying.Constants;


/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 */
@Controller()
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,HttpServletRequest request, RedirectAttributes model) {
		model.addFlashAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		model.addFlashAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME));
		return "redirect:/toLogin"; 
	}
	
	@RequestMapping("/toLogin")  
    public String toLogin(@ModelAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,@ModelAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME) String error,HttpServletRequest request, Model model) {  
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);  
        request.setAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, error);
        return "login";  
    }  
	
	@RequestMapping(value = "/logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(Constants.CURRENT_USER, null);
		return "login";
	}
	

}
