package com.py.shijijingying.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Description: 自定义注解 拦截service 
 * @author Mr Liu hao
 *
 * 2018年6月25日
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented 
public @interface SystemServiceLog {

	String description() default ""; 
}
