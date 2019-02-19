package com.py.shijijingying.utils;

public class AlipayConfig {
    // 1.商户appid
    public static String APPID = "2018112762319918";    
    
    // 2.私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCiVpESKQV3f+sz29mRcHJPk1zw6afnZb0/fJOF/HKMKRSTYph9nC6x62cs6WMr9JRGBQdStzxxr9NEuXLnW7zOeCO77rEGCrLLfqaw1RT9TrIbMqTfhRgJXpSryTQ9OvsuTN7AUIaye/emfzspZHGalALLBhp8ibU/dXZLIXfsh6b0pc6EQFX6PZLY6/HTHRXcg3TT8iEq5Ef7FTQqm/bIxMwteBAZuysyTUgOOwQIcx7R17ljrLoFgfyjRFpUi0e/mrGZ6EAT3bm4wWSrlvNPK1EgyQsb1Ytvai454sJsjTiYQ+gwWI3g4WkK5l8jwAc/AXEDEmR2A13pJB3r8BL3AgMBAAECggEAHTjB8mfp8QHYv8QCXPjFyuc24rLco32f3E67a3Hu2KYIayNjWbEEgshiESaN0Hq9OgFruQr82WYR/Jv5QAY2bWAY5ppnyTOhzZmlvc0Ncy94RgcOwKF/mygoUOft/FuinUn/CtUz+erwwErExoWrIW0lN5CIsgRD2QF24r1KjgvuOhzhIO2XA9P0kjGLrd1BtuRH8ku/onBv11Pofxcf30U9ufqvzadwDHa7IY4DhGFfc9rRjwP+FyrAkWDlRi7bBXdbnUD2vxwtHBW0nF5FwM5ovXeX+3bN9+nXeNk1SWLaW+SOXWsn8WzWafJGhomjHUqdYBe2kPJ5BTbG5abeoQKBgQD4zYapABIyhs1vOrUY+q2PDRzZQYo/D0GRp+FueBKdTKl1g4e2kkw3RswPk5LBrfd7NnEFuI2vNPkE+L8x94Qlm/CAMTDZh6hpuD6Ry49q75WUQ0MBZm0oxidR+JAQodqvaOLYD+uEvvto3KqaqxWUOdBw8453Ww+QPKB4+9FLJwKBgQCnCL0e1jbGx/CvD9BACzdPVzPEYL14o8hVrSfteW3gq3+B/quK2MLrEXw9AiFCXdlSz7cMr+1CV7BQ6slpvr36DTflI/T29mPgelHPHo0H+F/HU97ZGTDjVEPDb8TV+adz1l2mgt6WEGmjluxP23bf+s2Cz+NDevnWOwwNAQIbsQKBgAdO2ia6ps4vIXej7M/YBSMPiopjH3FsvEtP+BIrmrheGRgnjePx9iUlgjbu6b+goYVP+w/k9NxxnOLlf5AzKgGEzBKn3zx0fKDmVYgox4Snlugs9Znj7U7mSx9gQrMKXgcK+KbKCOv0RCQ3Xxd98PtSm2nF7ahZ12IwNbJ4CxS/AoGAMDqiuMOrf0v9395WCBf0+XC5Xst8CdhooOxlafk007lREMzi5S1fYpZi5A64YbxNYs7dEMm/XPeKzmJsk57ipc+pQv1V3vUXs5o+AurUV+fMZ/Z8kvzmE82h301nbU0vn4/W7Jw+/t9wUS7jvs8kjNmS+2FhQnvApa3Y9exDmpECgYEAshpDgpqOSXYnSzIapLFkPReeXTbRrWuf7ckk0iZx0MAHNj+SctLdJ5vK9UYCVF958v8755KB61wNNR2fcWeuiz8VQTH9KFkaCpTsHVRA7s5yDGn3umQ5gilr52uIoZx0EK+1KAxJEd12UPf9T/1Tz6PzwXD4aBnvMT3KyE0WKjA=";
    
    // 3.支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtt40McTHMLX5vxKaguDLb8Xf/v8In2636SkzSQsqh9a/Q8q4eS6iPUtNDnWzwoIQH6vVHOsKK3ObHF4KvYKKY6SMulQa90aRioB+sZSUDcowZNSC/LGKFrcbNcJeGyAWBckYhf3ydYG354bE/C1Lbanv6/qY+CAATlItcUhMY80HCNg1VydoDOqwTMX8w16fr1qAEjzCJ6u9TvMv4uvHLZmlxCVipmtQwNTdnlEDLR83ThDdGxP1U3+hUWHJxBl/9FlAU6xE3htBHLeAC4kwX4oJPAwuHjRLOsqosBGUfrRDg84YDQdLyiI8gmG4qgr6jD4EuS+rO+jhIlXwpHMgAwIDAQAB";
    
    //(转账)网关地址
  	public static final  String gateway_url = "	https://openapi.alipay.com/gateway.do";
    
    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://47.106.189.37:80/shijijingying/alipay/alipayNotify";
  	//public static String notify_url = "http://1f9w438490.imwork.net/shijijingying/alipay/alipayNotify";
    
    // 5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://39.108.239.193:8080/alipay/return_url";
    
    // 6.请求网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";    
    //沙箱支付网关
    public static String sandbox_url = "https://openapi.alipaydev.com/gateway.do";
    // 7.编码
    public static String CHARSET = "utf-8";
    
    // 8.返回格式
    public static String FORMAT = "json";
    
    // 9.加密类型
    public static String SIGNTYPE = "RSA2";
    
    public static boolean falg = true;
}

