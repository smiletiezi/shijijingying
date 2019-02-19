package com.py.shijijingying.web.mobile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ="test")
public class testController {
	
	@RequestMapping("test1")
	public String test1() {
		return "jsp/webSocketTest";
	}
	
}
