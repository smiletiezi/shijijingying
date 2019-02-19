package com.py.shijijingying.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class kd100Util {
	
	//key
	private static String key="NpyrYsNe2546";
	//公司编号
	private static String customer="F22E8169EBCB8DBCB3436669CA7C3277";
	//请求url
	private static String URL="https://poll.kuaidi100.com/poll/query.do";	
	
	public static void main(String[] args) {
		String result="";
		try {
			result = kd100Util.query("803655204357538227", "yuantong");
		} catch (Exception e) {}
		System.out.println(result);
	}
	
	
	/**
     * Json方式 查询快递信息
	 * @throws Exception 
     */
	public static String query(String courierCode, String companyCode) throws Exception{
		//添加参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("com", companyCode);
		params.put("num", courierCode);
		params.put("from", "");
		params.put("to", "");
		params.put("resultv2", 1);
		//转JSON 生成签名
		String param = JSON.toJSONString(params);
		String sign = encrypt(param);
		//待传递map
		Map<String, String> data = new HashMap<String, String>();
		data.put("param",param);
		data.put("sign",sign);
		data.put("customer",customer);
		return sendPost(URL, data);
	}
	
	
	/**
	 * Sign签名生成
	 * @param param
	 * @return
	 * @throws Exception
	 */
	private static String encrypt (String param) throws Exception{
		String str = param+key+customer;
		return MD5(str, "UTF-8");
	}
	
	/**
     * MD5加密
     * @param str 内容       
     * @param charset 编码方式
	 * @throws Exception 
     */
	private static String MD5(String str, String charset) throws Exception {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(str.getBytes(charset));
	    byte[] result = md.digest();
	    StringBuffer sb = new StringBuffer(32);
	    for (int i = 0; i < result.length; i++) {
	        int val = result[i] & 0xff;
	        if (val <= 0xf) {
	            sb.append("0");
	        }
	        sb.append(Integer.toHexString(val));
	    }
	    return sb.toString().toUpperCase();
	}
	
	 /**
     * 向指定 URL 发送POST方法的请求     
     * @param url 发送请求的 URL    
     * @param params 请求的参数集合     
     * @return 远程资源的响应结果
     */
	private static String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;        
        StringBuilder result = new StringBuilder(); 
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数            
            if (params != null) {
		          StringBuilder param = new StringBuilder(); 
		          for (Map.Entry<String, String> entry : params.entrySet()) {
		        	  if(param.length()>0){
		        		  param.append("&");
		        	  }	        	  
		        	  param.append(entry.getKey());
		        	  param.append("=");
		        	  param.append(entry.getValue());		        	  
		          }
		          out.write(param.toString());
            }
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {            
            e.printStackTrace();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
	
}
