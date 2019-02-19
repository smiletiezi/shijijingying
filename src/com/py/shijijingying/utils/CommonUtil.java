package com.py.shijijingying.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

public class CommonUtil {
	public static ConcurrentHashMap<String, Object> MSG_MAP = new ConcurrentHashMap<String, Object>();
	public static long MOBILECODEEXPIRETIME=15*60*1000;
	
	
	/**
	 * 获取客户端IP地址
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static String getIpAddress(final HttpServletRequest request) throws Exception{
		if (request == null) {
			throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
		}
		String ipString = request.getHeader("x-forwarded-for");
		if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
			ipString = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
			ipString = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
			ipString = request.getRemoteAddr();
		}
		// 多个路由时，取第一个非unknown的ip
		final String[] arr = ipString.split(",");
		for (final String str : arr) {
			if (!"unknown".equalsIgnoreCase(str)) {
				ipString = str;
				break;
			}
		}
		return ipString;
	}
	/****
	 * @param msg_uuid 验证码的uuid
	 * 验证手机验证码***/
	public static Msg verifyMobileCode(String mobile,String mobileCode){
		if(mobile==null||mobileCode==null){
			return Msg.fail().add("msg", "请输入手机号和手机验证码");
		}
		SMSBean smsBean = (SMSBean) MSG_MAP.get(mobile);
		if(smsBean==null){
			return Msg.fail().add("msg", "请获取验证码");
		}
		if(!smsBean.getValue().equals(mobileCode)){
			return Msg.fail().add("msg", "验证码错误");
		}
		if( new Date().getTime()-smsBean.getSendDate().getTime() > MOBILECODEEXPIRETIME){
			return Msg.fail().add("msg", "验证码过期,请重新获取");
		}
		MSG_MAP.remove(mobile);
		return Msg.success();
		
	}
	
	public static String saveFile(MultipartFile pic) throws IllegalStateException, IOException{
		Date date = new Date();
		String path = "C:\\upload\\";
		String filePath = DateUtil.formatTime(date, "yyyy-MM") +"/" +DateUtil.formatTime(date, "yyyy-MM-dd");
		File fileDirDay = new File((path.replace("\\","/") + filePath));
		if(!fileDirDay.exists()){
			fileDirDay.mkdirs();
		}
		path += filePath.replace("/","\\") + "\\";
        String originalFileName = pic.getOriginalFilename();
        // 新的图片名称
        String newFileName = UUIDUtils.getUUID()+ originalFileName.substring(originalFileName.lastIndexOf("."));
        // 新的图片
        File newFile = new File(path + newFileName);
        // 将内存中的数据写入磁盘
        pic.transferTo(newFile);
		return   filePath + "/" + newFileName;
	}
	public static String videoStr(String imgStr) throws IOException{
        OutputStream out=null;
        
        Date date = new Date();
        String path = "C:\\upload\\";
        String filePath = DateUtil.formatTime(date, "yyyy-MM") +"/" +DateUtil.formatTime(date, "yyyy-MM-dd");
        File fileDirDay = new File((path.replace("\\","/") + filePath));
        if(!fileDirDay.exists()){
        	fileDirDay.mkdirs();
        }
        path += filePath.replace("/","\\") + "\\";
        String newFileName = UUIDUtils.getUUID()+".avi";//新生成的视频
        if(imgStr.indexOf("base64") > -1) {
        	imgStr = imgStr.split(",")[1];
		}
        BASE64Decoder decoder = new BASE64Decoder();
        try {
        	byte[] bytes = decoder.decodeBuffer(imgStr);
            for(int i = 0; i < bytes.length; ++i){
    			if (bytes[i] < 0) {
    				bytes[i] += 256;
    			}
    		}
            out = new FileOutputStream(path + newFileName);
            out.write(bytes);  
            out.flush();
            out.close();
		} finally {
			if(out!=null)out.close();
		}
        return  filePath + "/" + newFileName;
	}
	
	
	//多图上传
		public static List<String> saveFiles(MultipartFile[] pics) throws IllegalStateException, IOException{
			List<String> imageUrls = new ArrayList<String>();
			for(MultipartFile multipartFile : pics){
				Date date = new Date();
				String path = "C:\\upload\\";
				String filePath = DateUtil.formatTime(date, "yyyy-MM") +"/" +DateUtil.formatTime(date, "yyyy-MM-dd");
				File fileDirDay = new File((path.replace("\\","/") + filePath));
				if(!fileDirDay.exists()){
					fileDirDay.mkdirs();
				}
				path += filePath.replace("/","\\") + "\\";
		      
				  String originalFileName = multipartFile.getOriginalFilename();
			        // 新的图片名称
			        String newFileName = UUIDUtils.getUUID()+ originalFileName.substring(originalFileName.lastIndexOf("."));
		        // 新的图片
		        File newFile = new File(path + newFileName);
		        // 将内存中的数据写入磁盘
		        multipartFile.transferTo(newFile);
		        String url= filePath + "/" + newFileName;
		        imageUrls.add(url);
			}
			
			return  imageUrls;
		}
}
