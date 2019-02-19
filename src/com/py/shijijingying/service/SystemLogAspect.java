package com.py.shijijingying.service;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.py.shijijingying.annotation.SystemControllerLog;
import com.py.shijijingying.annotation.SystemServiceLog;
import com.py.shijijingying.entity.SysLog;
import com.py.shijijingying.entity.User;
import com.py.shijijingying.service.ShiroDbRealm.ShiroUser;
import com.py.shijijingying.utils.AESUtil;
import com.py.shijijingying.utils.Utils;


/**
 * 自定义切面类
 * @author Mr Liu hao
 *
 * 2018年6月25日
 */
@Aspect
@Component
public class SystemLogAspect {
	
	@Autowired
	private SysLogService sysLogService;
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
	
	
	
	/**
	 * Service层切点
	 */
	@Pointcut("@annotation(com.py.shijijingying.annotation.SystemServiceLog)")
	public void serviceAspect() {
	}

	/**
	 * Controller层切点
	 */
	@Pointcut("@annotation(com.py.shijijingying.annotation.SystemControllerLog)")
	public void controllerAspect() {
	}
	
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		
		String token = request.getParameter("token");
		String userid = request.getParameter("userid");

		// 请求的IP
		String ipAddr = null;
		try {
			ipAddr = Utils.getIpAddr(request);
		} catch (Exception e1) {
			ipAddr = "0.0.0.0";
		}
		try {
			ShiroUser shirouser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			User user = userService.selectByLoginName(shirouser.loginName);
				
			// *========控制台输出=========*//
			logger.info("===============系统日志开始===============");
			logger.info("请求方法:"
					+ (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			logger.info("方法描述:" + getControllerMethodDescription(joinPoint));
			logger.info("请求人:" + (null != user ? user.getLoginName() : ""));
			logger.info("请求IP:" + ipAddr);
			// *========数据库日志=========*//
			SysLog log=new SysLog();
			log.setIpAddr(ipAddr);
			log.setCreateTime(new Date());
			log.setType((byte) 0);
			log.setOperateDesc(getControllerMethodDescription(joinPoint));
			log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			if(null == user) {
				//可能是手机端请求
				if(null == userid) {
					try {
						//获取解密密钥
						String aesKey = Utils.getProperties("aes_key");
						log.setCreateUser(Integer.parseInt(AESUtil.decode(token, aesKey).split("&")[0]));
					} catch (Exception e1) {
						logger.warn("请求者ID获取失败");
					}
				}else {
					log.setCreateUser(Integer.parseInt(userid));
				}
			}else {
				log.setCreateUser(user.getId());
			}
			// 保存数据库
			sysLogService.insertSelective(log);
			logger.info("===============系统日志结束===============");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("===============系统日志异常===============");
			logger.error("异常信息:{}", e.getMessage());
		}
	}
	
	
	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemServiceLog.class).description();
					break;
				}
			}
		}
		return description;
	}
	
	
	
	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemControllerLog.class).description();
					break;
				}
			}
		}
		return description;
	}
	
	


}
