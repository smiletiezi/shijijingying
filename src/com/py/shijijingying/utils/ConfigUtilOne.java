package com.py.shijijingying.utils;

public class ConfigUtilOne {
	 /**
     * 服务号相关信息
     */
     public final static String APPID = "wx0e240185ed7c9d43";								//服务号的应用号/开放平台里的APPid
     public final static String MCH_ID = "1502521071";										//商户号
     public final static String API_KEY = "KNq0npohom0VpgE0wgGujkR2bmxNXIfc";				//API密钥
     public final static String App_Secret = "e831b63c6a82739aa34fb315fc1a235e";//私钥
     public final static String SIGN_TYPE = "MD5";											//签名加密方式
     
     public final static String notify_url = "http://47.106.189.37:80/shijijingying/wxpay/wxNotify";			//回调接口

     public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
