<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户详情</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">                   
	<form class="layui-form" id="form">


	<div class="layui-form-item">
	    <label class="layui-form-label">登录名：</label>
	    <div class="layui-input-block">
	      ${obj.loginName}
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">手机号：</label>
	    <div class="layui-input-block">
	      ${obj.phone}
	    </div>
	  </div>
	    
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户角色：</label>
	    <div class="layui-input-block">
	      ${role.name}
	    </div>
	    </div>

	</form>
</body>
</html>