package com.py.shijijingying.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;


/**
 * 工具类
 */
public class Utils {
	
	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private static SecureRandom random = new SecureRandom();
	static Properties prop = null;
	
	private Utils() {}

	static {
		prop = new Properties();
		InputStream in = Utils.class.getResourceAsStream("/paramConfig.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			logger.error("工具类：Utils 读取属性文件时出现异常");
			logger.error(e.getMessage());
		}
	}

	/**
	 * 删除ArrayList中重复元素，保持顺序
	 * @param list
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void removeDuplicateWithOrder(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
	}
	
	
	/** 
     * 产生随机的数 
     * @return 
     */  
    public static String getSix(int num){  
        Random rad=new Random();  
        String result  = rad.nextInt(1000000) +"";  
        if(result.length()!=num){  
            return getSix();  
        }  
        return result;
    } 
	
	/**
	 * 生成8位字节数
	 * @param nums
	 * @return
	 */
	public static byte[] generateSalt(int nums){
		byte[] bytes = new byte[nums];
		random.nextBytes(bytes);
		return bytes;
	}
	
	/**
	 * 非空判断
	 * @param values
	 * @return
	 */
	public static boolean isNotNull(List<String> values){
		for (String str : values) {
			if (str == null || "".equals(str.trim()) || str.trim().equalsIgnoreCase("null")) return false;
			str.trim();//去除首位空格
		}
		return true;
	}
	
	/**
	 * 非空判断
	 * @param values
	 * @return
	 */
	public static boolean isNotNull(String[] values){
		for (String str : values) {
			if (str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("null")) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 非空判断
	 * @param values
	 * @return
	 */
	public static boolean isNotNull(String str){
		if (str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("null")) {
			return false;
		}
		return true;
	}
	
	/**
	 * 非空判断
	 * @param values
	 * @return
	 */
	public static boolean isNotNull(Long lng){
		if (null == lng || "".equals(lng) || lng <= 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 非空判断
	 * @param values
	 * @return
	 */
	public static boolean isNotNull(Object[] objArry){
		for (Object object : objArry) {
			if (object == null) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 获取客户端IP地址
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static String getIpAddr(final HttpServletRequest request) throws Exception{
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
	
	/**
	 * 根据参数名称返回/paramConfig.properties中配置的参数值
	 * @param 参数名称
	 * @return 参数值
	 */
	public static String getProperties(String key) {
		return prop.getProperty(key).trim();
	}
	
	/**
	 * 获取当前系统时间，格式为：yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	/**
	 * 获取当前系统日期，格式为：yyyy-MM-dd HH:mm:ss
	 */
	public static Date getCurrentDate() {
		return new Date();
	}
	/**
	 * 获取当前系统时间
	 */
	public static String getCurrentTime(String formatStr) {
		Date date = new Date();
		SimpleDateFormat format = null;
		if(null != formatStr && !"".equals(formatStr)){
			format = new SimpleDateFormat(formatStr);
		}else{
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		return format.format(date);
	}
	
	/**
	 * 字符串转换成日期
	 * @param str
	 * @return date
	 */
	public static Date strToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = format.parse(str);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return date;
	}
	
	/**
     * 当浮点型数据位数超过10位之后，数据变成科学计数法显示。用此方法可以使其正常显示。
     * @param value
     * @return Sting
     */
    public static String formatFloatNumber(double value) {
        if(value != 0.00){
            java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
            return df.format(value);
        }else{
            return "0.00";
        }

    }
    
    public static String formatFloatNumber(Double value) {
        if(value != null){
            if(value.doubleValue() != 0.00){
                java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
                return df.format(value.doubleValue());
            }else{
                return "0.00";
            }
        }
        return "";
    }
	
	/**
	 * 字符替换(将数据库中存的  HTML 标签转义)
	 * @param str
	 * @return
	 */
	public static String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}
	
	/**
	 * 去除html代码
	 * @param inputString
	 * @return
	 */
	public static String HtmltoText(String inputString) {
	    String htmlStr = inputString; //含html 标签的字符串
	    String textStr ="";
	    java.util.regex.Pattern p_script;
	    java.util.regex.Matcher m_script;
	    java.util.regex.Pattern p_style;
	    java.util.regex.Matcher m_style;
	    java.util.regex.Pattern p_html;
	    java.util.regex.Matcher m_html;         
	    java.util.regex.Pattern p_ba;
	    java.util.regex.Matcher m_ba;
	   
	    try {
	        String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>" ; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
	        String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>" ; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
	        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
	        String patternStr = "\\s+";
	       
	        p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
	        m_script = p_script.matcher(htmlStr);
	        htmlStr = m_script.replaceAll( ""); //过滤script标签

	        p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
	        m_style = p_style.matcher(htmlStr);
	        htmlStr = m_style.replaceAll( ""); //过滤style标签
	    
	        p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
	        m_html = p_html.matcher(htmlStr);
	        htmlStr = m_html.replaceAll( ""); //过滤html 标签
	       
	        p_ba = Pattern.compile(patternStr,Pattern.CASE_INSENSITIVE);
	        m_ba = p_ba.matcher(htmlStr);
	        htmlStr = m_ba.replaceAll( ""); //过滤空格
	    
	     textStr = htmlStr;
	    
	    }catch(Exception e) {
	         System. err.println( "Html2Text: " + e.getMessage());
	         return textStr; //返回文本字符串
	    }         
	    return textStr; //返回文本字符串
	 }


	
	/** 
     * 产生随机的六位数 
     * @return 
     */  
    public static String getSix(){  
        Random rad=new Random();  
        String result  = rad.nextInt(1000000) +"";  
        if(result.length()!=6){  
            return getSix();  
        }  
        return result;
    }  
	
	
	/**
	 * 根据session和cookie检验是否需要维护阅读次数
	 * @param request
	 * @param response
	 * @param cookieName cookie的名字
	 * @return 是否需要维护阅读次数，false：不需要；true：需要
	 */
	public static boolean validRead(HttpServletRequest request,HttpServletResponse response,String cookieName){
		
		boolean isAdd = true;//是否需要维护阅读次数，false：不需要；true：需要
		
		// 开始cookie判断
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (null != cookie) {
					if (cookieName.equals(cookie.getName()))
						isAdd = false;
				}
			}
			// 如果不存在 名字 为：newscookie 的cookie信息，则增加一次访问记录
			if (isAdd) {
				//第一次请求,通过当前系统时间加密 cookie 的value
				String cookieValue = AESUtil.encode(cookieName+ ";" + System.currentTimeMillis(), Utils.getProperties("aes_password"));
				CookieUtil.addCookie(response, cookieName, cookieValue, 0);// 给客户端添加cookie信息
				return isAdd;
			}
		}
		return isAdd;
	} 
	
	/**
	 * 去掉字符串数组中的重复值
	 * @param str
	 * @return
	 */
	public static String[] removeRepeatStr(String[] str){
        List<String> list = new LinkedList<String>();  
        for(int i = 0; i < str.length; i++) {  
            if(!list.contains(str[i])) {  
                list.add(str[i]);  
            }  
        }  
        String[] rowsTemp = list.toArray(new String[list.size()]);  
        return rowsTemp;
	}
	
	/**
	 * 生成唯一标识   uuid 并用“”替换“-”
	 *  uuid :xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx (8-4-4-4-12)
	 * @return 唯一标识
	 */
	public static String getUuid() {
		String id = UUID.randomUUID().toString();
		id = id.replace("-","");
		return id;
	}
	
	
	
	/**
	 * 生成十六进制的字符串
	 * @param data
	 * @return
	 */
	public static String encodeHex(byte[] data){
		int l = data.length;
		char[] out = new char[l << 1];
		int i = 0; for (int j = 0; i < l; i++) {
			out[(j++)] = DIGITS_LOWER[((0xF0 & data[i]) >>> 4)];
			out[(j++)] = DIGITS_LOWER[(0xF & data[i])];
		}
		return new String(out);
	}
	
	
	/**
	 * base64转化为图片
	 * @param pic
	 * @return
	 * @throws Exception
	 */
	
	public String analysisPic(String pic) throws Exception{
		String newFileName = "";
		BASE64Decoder base = new BASE64Decoder();
        byte[] decode = base.decodeBuffer(pic);
        
        // 图片输出路径
        String savePath = Utils.getProperties("file_upload_path");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		newFileName = sdf.format(new Date()) + "_" + new Random().nextInt(1000) + ".jpg";
		File path = new File(savePath);
		if(!path.exists()){
			path.mkdirs();
		}
		File file = new File(savePath,newFileName);
		file.createNewFile();
        // 定义图片输入流
        InputStream fin = new ByteArrayInputStream(decode);
        // 定义图片输出流
        FileOutputStream fout = new FileOutputStream(file);
        // 写文件
        byte[] b = new byte[1024];
        int length=0;
		while((length = fin.read(b))>0){
		    fout.write(b, 0, length);
		}
        fin.close();
        fout.close();
		return newFileName;
	}
}
