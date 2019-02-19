package com.py.shijijingying.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("upload")
public class UploadController extends BaseController {
	
	/**
	 * 异步上传文件带监听
	 * @param request
	 * @return
	 * @throws FileUploadException 
	 */
	@RequestMapping("/uploadFile")
	@ResponseBody
	public Map<String,Object> uploadFileMonitor(HttpServletRequest request) throws FileUploadException{
		String name=request.getParameter("name");
		return uploadFile(request,name);
	}
	
	
	
	
	/**
	 * 异步上传文件带监听
	 * @param request
	 * @return
	 * @throws FileUploadException 
	 */
	@RequestMapping("/uploadVideo")
	@ResponseBody
	public Map<String,Object> uploadVideo(HttpServletRequest request) throws FileUploadException{
		String name=request.getParameter("name");
		return uploadVideo(request,name);
	}
	
}
