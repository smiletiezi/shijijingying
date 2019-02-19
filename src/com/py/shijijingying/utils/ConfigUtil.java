package com.py.shijijingying.utils;

public class ConfigUtil {
    /**
     * 服务号相关信息
     */
     public final static String APPID = "wx47096570cdd36307";								//开放平台里的APPid
     public final static String MCH_ID = "1519738341";										//商户号
     public final static String API_KEY = "boxinMIIBIjANBgkqhkiG9w0BAQEFAAO";				//API密钥
     public final static String App_Secret = "79a95a881bd95145c1bb4f4cb8497a42";//私钥
     public final static String SIGN_TYPE = "MD5";											//签名加密方式
     
     public final static String notify_url = "http://47.106.189.37:80/shijijingying/wxpay/wxNotify";			//回调接口
    // public final static String notify_url ="http://1f9w438490.imwork.net/shijijingying/wxpay/wxNotify";
     public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
