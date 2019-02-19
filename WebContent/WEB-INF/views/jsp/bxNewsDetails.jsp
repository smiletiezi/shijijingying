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
  <title>新闻详情</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">                   
	<form class="layui-form" id="form">
	<input type="hidden" id="newsImg" value="${obj.newsImg }">
	  <div class="layui-form-item">
	    <label class="layui-form-label">新闻标题：</label>
	    <div class="layui-input-block" id="title">
	      ${obj.title}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">新闻内容：</label>
	    <div class="layui-input-block" id="newsText">
	      ${obj.newsText}
	    </div>
	  </div>
	  
	 
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品图片：</label>
	    <div class="layui-upload-list"  id="productImg_one">
      </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品视频：</label>
	    <div class="layui-input-block" id="usernumber">
	      <video  width="290px"  controls autoplay height="220px"  id="demo4"  src="${path }${obj.newsVideo}"/>
	    </div>
	  </div>
	
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">创建时间：</label>
	    <div class="layui-input-block" id="createDate">
	      ${obj.createDate}
	    </div>
	  </div>
	 
	</form>
	<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
	<script src="${ctx}/static/layui/layui.js" charset="utf-8"></script>
	<script src="${ctx }/static/lib/jquery-form.js"></script>
	<script>
	   $(function(){
		 var array=$("#newsImg").val().split(',');
		 for(var i in array){
			 if(array[i]){
				 var item=$("<img  width='90px' height='95px' class='layui-upload-img' src='${path }"+array[i]+"'/>");
				 $("#productImg_one").append(item); 
			 }
		 }
	});
	</script>
	
	
	
</body>
</html>