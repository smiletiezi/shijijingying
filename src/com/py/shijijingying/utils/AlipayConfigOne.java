package com.py.shijijingying.utils;

public class AlipayConfigOne {
	  // 1.商户appid
    public static String APPID = "2019011563012586";    
    
    // 2.私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCt23/alVrUBDoSk7W+hd8vi4ai7pAg4QYfHFyPTibkj9ohv+hmU0FlBwj22W78ulW27PrL4tZS48LSkMXxCywOqJPeL8mzaBNlyHeNgPH4c+ywdBjPZCAvUYo13RM+6eBqaB7TiN0YoCq14GaAVNaucS67Tx0R+Yet1gN7mqFIna2ee+Z1TT9eK+EyECjq3DTdHsusekvnTNjPjU+GKRm9U4W4RPJSIDg4O+9UqUdlrdf2XauqC1vjTYV3sgzmYdTvh5H/PuNumEb/l7cj/aNhedXMnJ3Tzj8O3ZhO+0GZL5imP9ZG+Ty0VakqBoYpf3rK26QYiNm3BH/MzpCoP/1pAgMBAAECggEAd1Iljqxg2RBJlwRfgPI6OlRlRB+9NYObAvEhKY12rfl4uR8P7mKHiEHqyhhyzutH9rLwccwuUldVF9UatRN42lqimaRspz/vyDRssOZlq6JMONKFUJkkabVpyQwAsHYtenoZZg8Rx1vt1EO9tagEa1bsvstOZU6/6vPuEgAPX1kMMPoItzIBCnYppYnC2RD/mXrw13Ga3CLf1gpiqO9cErrIuZdItaP29kQABHnQqvf0NencLzwYwIu/umldGTyknAtpyHfV4GNYxGWH/8u5kL5lCSj8Zoj6uZ6CM+5qg8iEPQ10PWqndzut5o/48iQIOtXhj7otDdp/4L2psyh98QKBgQDyfQl+irPlHMGSlhammheg8+fnEQ0qqooeeZ3PJZccuf26bJK2aFze2QY457+WFSATvsZ4um+kSBRPYwfgHRUO5IPQuktXOv+aYPDzInHVqPQa0fLINHa6/QNwMlxUL0vyZs1SmUC6ZbdMqfxLtDt/gfXyOXwX+bSrSgWD0jw0dQKBgQC3i3qmJvK1XmsQPMIq/y4ymwm5Gr5dEAm65NlXHg2V+/LFIS4qbGco49iF+HnneI/R8idDRp53X6Q7xuxkpq91zi7tIn815MjHQfOX851h+ihSbwbkywoUuP6T8Pl6MtQ+BWJJPoaPiO7vjpI5lLeXgcz5Y0WH/LrFJHBxEKu2pQKBgQCfMYlXIxYhNvS6GIC8VXNnvlijsOUU+qr8gNkikkoXmN9DUV4sFG3L+2yDVVnI3QuXCurffX4nuw5iY+BauJ0Hjuwezv8BQIFnx3bIF/flziqbpPOfjhnYfpyQPu+wcPCKLgVj0arV9EsXMI2q4aMh/Z8Kq8SVNODwhwMB3LabtQKBgQCgC3VlEC0uzSfaPsCosV5dLidqStGXYHa7ivWhK0l5cPodvnL89w108EhLeYECv3PZA6gM17UyLimNHkXe9iGlPaZZjG0UV2xjozR/WSPw3SrvxnRiaFFgBHeoFMXVMiEz+4naiDTZT/PY257RqsXzzHEpPNDSZSOO59lMHdc9KQKBgD/snXbtDu9L1w5mtxU6gq7JcPbWU+iP+OyO6RtvnEvc9Kq6pqrVBh3EDqDR+X3+rs/TWRG5x3Qh/qlYnlgtfP0j2UpiPlaitccelwBI32ADf4BzIJ7btYQPbvPNV7cNlznYrAp3N9QM1qZxy2fCD4g+IZV2c+c4Jki5+q2GO402";
    // 3.支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqh91a5eOfbD1RZtmfs37g6BRYVw7IyPtlWNtwLSZXHVAQU17Pmen9EnwJKUNr6Uy5Vk9bTlitX0tyBfKshhZ4UIpEbSldv6iFN0pB2h6733YMpF6PpSEi4tKSdG2+2cbWjj5hosQQLDv6VKsFkjSPGh1U81TMMjBBD/83VJwCA/TmKqRkkPAZGCPzo6Feg7jeDEyUna/4SONokQ7NL1Wr1hUh8MwMe0ZB+4V9NjVXYqF0wG+RS//OAlLv2cfAA0GYB/p2zduUvicTamsR5b6fb3pLy8INlMS854rpuNSCnMKTxENjWoD2np/QPM2Zu8aq/Oli5BBdqXpkVZB/ogD+wIDAQAB";
    //(转账)网关地址
  	public static final  String gateway_url = "https://openapi.alipay.com/gateway.do";
    
    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://47.106.189.37:80/shijijingying/alipay/alipayNotifyOne";
  	//public static String notify_url ="http://1f9w438490.imwork.net/shijijingying/alipay/alipayNotifyOne";
    
    // 5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://15386nv005.iok.la/testWeb/alipay/return_url";
    
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
