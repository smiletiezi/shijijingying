<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%response.setStatus(200);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
  <meta charset="utf-8">
  <title>500 页面出错了</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin.css" media="all">
<link id="layuicss-layer" rel="stylesheet" href="${ctx}/static/layui/css/modules/layer/default/layer.css" media="all"></head>
<body layadmin-themealias="default">


<div class="layui-fluid">
  <div class="layadmin-tips">
    <i class="layui-icon" face=""></i>
    
    <div class="layui-text" style="font-size: 20px;">
      好像出错了哦
    </div>
    
  </div>
</div>

  <script src="${ctx}/static/layui/layui.js"></script>  
  <script>
  layui.config({
    base: '${ctx}/static/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index']);
  </script>
</body>
</html>