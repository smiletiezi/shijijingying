package com.py.shijijingying.utils;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;

/**
 * Cookie 操作
 */
public class CookieUtil {
	
	private static Map<String,Cookie> cookieMap = null;
	
	static{
		cookieMap = Maps.newHashMap();
	}
	
	/**
     * 根据名字获取cookie (暂不使用)
     * @param name cookie名字
     * @return
     */
    public static Cookie getCookieByName(String name){
    	System.out.println("map size:" + cookieMap.size());
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }   
    }
    
    /**
     * 设置cookie
     * @param response
     * @param name  cookie名字
     * @param value cookie值
     * @param maxAge cookie生命周期  以秒为单位
     */
    public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        //如果 maxAge = 0，cookie将在浏览器关闭后过期
        if(maxAge>0)  cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
        cookieMap.put(name, cookie);//将cookie放入map中
    }

}
