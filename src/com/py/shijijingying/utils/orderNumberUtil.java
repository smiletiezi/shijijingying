package com.py.shijijingying.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class orderNumberUtil {
	
	 private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	 public static String gens() {
	        //生成
	        String orderNo = sdf.format(new Date()) + (1 + (int) (Math.random() * 10000));
	        return orderNo;
	    }
	 
	 public static void main(String[] args) {
	        System.out.println(gens());
	      
	    }
 
}
