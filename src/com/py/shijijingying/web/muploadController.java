package com.py.shijijingying.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("mupload")
public class muploadController extends BaseController {
	/**
	 * 手机端上传 base64 异步上传文件带监听
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws FileUploadException 
	 */
	@RequestMapping("/uploadBase")
	@ResponseBody
	public Map<String,Object> uploadBase(HttpServletRequest request) throws Exception {
		String pic=request.getParameter("pic");
		return analysisPic(pic);
	}
	
}
