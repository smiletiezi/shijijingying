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
  <title>订单详情</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding"> 
                  
	<form class="layui-form" id="form">
	<c:if test="${obj!=null}">
	<c:forEach items="${obj}" var="obj">
	  <div class="layui-form-item">
	    <label class="layui-form-label">订单编号：</label>
	    <div class="layui-input-block" id="orderNumber">
	      ${obj.orderNumber}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品名称：</label>
	    <div class="layui-input-block" id="productName">
	      ${obj.productName}
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">购买数量：</label>
	    <div class="layui-input-block" id="num">
	      ${obj.num}
	    </div>
	  </div>
	  
	   <div class="layui-form-item">
	    <label class="layui-form-label">产品规格：</label>
	    <div class="layui-input-block" id="size">
	      ${obj.size}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">下单日期：</label>
	    <div class="layui-input-block" id="createDate">
	      ${obj.createDate}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">购买单位/购买人：</label>
	    <div class="layui-input-block" id="createDate">
	      ${obj.userName}
	    </div>
	  </div>
	  </c:forEach>
	  </c:if>
	</form>
	<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
	<script src="${ctx}/static/layui/layui.js" charset="utf-8"></script>
	<script src="${ctx }/static/lib/jquery-form.js"></script>
	<script>
	
	</script>
	
	
	
</body>
</html>