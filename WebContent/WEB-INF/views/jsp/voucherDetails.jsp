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
  <title>代金券详情</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding"> 
                  
	<form class="layui-form" id="form">
	
	  <div class="layui-form-item">
	    <label class="layui-form-label">代金券名称：</label>
	    <div class="layui-input-block" id="voucherName">
	      ${obj.voucherName}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">代金券金额：</label>
	    <div class="layui-input-block" id="voucherPar">
	      ${obj.voucherPar}
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">满多少使用：</label>
	    <div class="layui-input-block" id="voucherMore">
	      ${obj.voucherMore}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">有效期：</label>
	    <div class="layui-input-block" id="voucherTime">
	      ${obj.voucherTime}
	    </div>
	  </div>
	  
	   <div class="layui-form-item">
	    <label class="layui-form-label">创建时间：</label>
	    <div class="layui-input-block" id="voucherCreateTime">
	      ${obj.voucherCreateTime}
	    </div>
	  </div>
	  
	   <div class="layui-form-item">
	    <label class="layui-form-label">到期时间：</label>
	    <div class="layui-input-block" id="voucherExpiry">
	      ${obj.voucherExpiry}
	    </div>
	  </div>
	  
	   <div class="layui-form-item">
	    <label class="layui-form-label">是否使用：</label>
	    <div class="layui-input-block" id="voucherUse">
	      <c:if test="${obj.voucherUse == '0'}">未使用</c:if>	
	      <c:if test="${obj.voucherUse == '1'}">使用中</c:if>
	    </div>
	  </div>
	  
	   <div class="layui-form-item">
	    <label class="layui-form-label">是否可用于抽奖：</label>
	    <div class="layui-input-block" id="voucherType">
	      <c:if test="${obj.voucherType == '0'}">不可以</c:if>	
	      <c:if test="${obj.voucherType == '1'}">可以</c:if>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">是否可用于积分兑换：</label>
	    <div class="layui-input-block" id="present">
	      <c:if test="${obj.present == '0'}">不可以</c:if>	
	      <c:if test="${obj.present == '1'}">可以</c:if>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">兑换所用积分：</label>
	    <div class="layui-input-block" id="integration">
	      ${obj.integration}
	    </div>
	  </div>

	</form>
	<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
	<script src="${ctx}/static/layui/layui.js" charset="utf-8"></script>
	<script src="${ctx }/static/lib/jquery-form.js"></script>
	<script>
	
	</script>
	
	
	
</body>
</html>