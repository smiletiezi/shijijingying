<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>博信购后台管理</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="shortcut icon" href="${ctx}/static/img/favicon.ico" type="image/x-icon" />
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin.css">

</head>

<body class="layui-layout-body">
  
  <div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
      <jsp:include page="top.jsp"></jsp:include>
      
      <!-- 侧边菜单 -->
      <jsp:include page="left.jsp"></jsp:include>

      <!-- 页面标签 -->
      <jsp:include page="page_label.jsp"></jsp:include>
      
      <!-- 主体内容 -->
      <div class="layui-body" id="LAY_app_body">
        <div class="layadmin-tabsbody-item layui-show">
        <iframe src="${ctx }/product/toList"  frameborder="0" class="layadmin-iframe"></iframe>
        </div>
      </div>
    </div>
  </div>
  <script src="${ctx}/static/lib/jquery-1.11.3.min.js" charset="utf-8"></script>
  <script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
  <script src="${ctx}/static/layui/layui.js"></script>
  <script>
	 layui.config({
	    base: '${ctx}/static/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use('index');
  	 //修改密码	
	 function updatePassWord(){
		layer.open({
			  type: 2,
			  title: '修改密码',
			  shade: 0.8,
			  maxmin: true,
			  area: ['40%','40%'],
			  content: '${ctx}/user/toUpdatePassWord',
			  btn: ['立即提交'],
			  yes: function(index, layero){ 
				  var nodeName = window["layui-layer-iframe" + index];
				  nodeName.submitForm(index);
				  //window.location.href='${ctx}/logout';
			  }
		
		 });
		 
	}
  </script>
</body>
</html>


