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
	    <label class="layui-form-label">用户唯一识别码：</label>
	    <div class="layui-input-block" id="usernumber">
	      ${obj.usernumber}
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">业务员身份验证：</label>
	    <div class="layui-input-block">
	    <c:if test="${obj.useraccount == '0'}">普通用户</c:if>	
	      <c:if test="${obj.useraccount == '1'}">业务员待审核</c:if>
	      <c:if test="${obj.useraccount == '2'}">业务员</c:if>
	      <c:if test="${obj.useraccount == '3'}">合伙人</c:if>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">企业法人：</label>
	    <div class="layui-input-block">
	      ${obj.username}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户名：</label>
	    <div class="layui-input-block">
	      ${obj.userbusinessname}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户电话：</label>
	    <div class="layui-input-block">
	      ${obj.userphonenumber}
	    </div>
	  </div>
	  
	  <!-- 
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户密码：</label>
	    <div class="layui-input-block">
	      ${obj.userpassword}
	    </div>
	  </div>
	   -->
	   
	  <div class="layui-form-item">
	    <label class="layui-form-label">邀请人唯一识别码：</label>
	    <div class="layui-input-block">
	      ${obj.usergrourid}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">注册时间：</label>
	    <div class="layui-input-block">
	      ${obj.usercreationtime}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">税号：</label>
	    <div class="layui-input-block">
	      ${obj.userdutynumber}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">开户行：</label>
	    <div class="layui-input-block">
	      ${obj.userbank}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">银行帐号：</label>
	    <div class="layui-input-block">
	      ${obj.userbanknumber}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户地址：</label>
	    <div class="layui-input-block">
	      ${obj.useraddress}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">业务范围：</label>
	    <div class="layui-input-block">
	      ${obj.sphere}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户消费金额：</label>
	    <div class="layui-input-block">
	      ${obj.userbalance}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">养猪场存栏数：</label>
	    <div class="layui-input-block">
	      ${obj.pennumber}
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">经产母猪数：</label>
	    <div class="layui-input-block">
	      ${obj.pignumber}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">个人积分：</label>
	    <div class="layui-input-block">
	      ${obj.personal}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">我的抽奖次数：</label>
	    <div class="layui-input-block">
	      ${obj.drawnum}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">年目标：</label>
	    <div class="layui-input-block">
	      ${obj.year}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">月目标：</label>
	    <div class="layui-input-block">
	      ${obj.month}
	    </div>
	  </div>
	  
	  
	  

	</form>
</body>
</html>