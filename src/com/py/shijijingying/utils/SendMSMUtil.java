package com.py.shijijingying.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

public class SendMSMUtil{
  public static final String COMMON_TEMPLATE = "SMS_150455566";//验证码模板
  public static final String COMMON_TEMPLATE_UPDATE = "SMS_150455565";//验证码模板_修改
  static final String product = "Dysmsapi";
  static final String domain = "dysmsapi.aliyuncs.com";
  static final String accessKeyId = "LTAIcwl3gr76hhLG";//
  static final String accessKeySecret = "90IAiuJWxXFb0IKk5wj910VxaaiDjy";//
  
  static final boolean flag = true;//false:测试模式，默认验证码为0000;
  
  public static SMSBean sendMSM(String recMobile,String tpl_id,boolean is_code,String code) throws ClientException{
	    if(!flag) {
		    if(is_code){
				SMSBean smsBean = new SMSBean(null ,recMobile,"0000",new Date());
				CommonUtil.MSG_MAP.put(smsBean.getPhonenum(), smsBean);
				return smsBean;
			}
	    }
	  
		if(tpl_id==null)tpl_id=COMMON_TEMPLATE;
	
		if (code == null) {
	      code = String.format("%04d", new Object[] { Integer.valueOf(new Random().nextInt(10000)) });
	    }
	    System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	    System.setProperty("sun.net.client.defaultReadTimeout", "10000");
	    
	
	    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
	    DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
	    IAcsClient acsClient = new DefaultAcsClient(profile);
	    
	
	    SendSmsRequest request = new SendSmsRequest();
	    
	    request.setPhoneNumbers(recMobile);
	    
	    request.setSignName("博信药业");//签名
	    
	    request.setTemplateCode(tpl_id);
	    
	    request.setTemplateParam("{\"code\":\"" + code + "\"}");
	
	    request.setOutId("yourOutId");
	    
	    SendSmsResponse sendSmsResponse = (SendSmsResponse)acsClient.getAcsResponse(request);
	    System.out.println(sendSmsResponse.getMessage());
	    boolean success = "OK".equals(sendSmsResponse.getCode());
		if(success){
			SMSBean smsBean = new SMSBean(null ,recMobile,code,new Date());
			CommonUtil.MSG_MAP.put(recMobile, smsBean);
			return smsBean;
		} 
		return null;
	}
	
  public static void clearSendMsg(HttpSession session, String mobile){
      session.removeAttribute(mobile);
      session.removeAttribute(mobile + "Time");
  }
}
