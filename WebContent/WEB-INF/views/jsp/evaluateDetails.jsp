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
  <title>评价审核</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">                   
	<form class="layui-form" id="form" action="${ctx }/evaluate/set" commandName="obj" lay-filter="formFilter">
	<input type="hidden" id="evaluateId" name="evaluateId" value="${obj.evaluateId }">
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品Id：</label>
	    <div class="layui-input-block" id="productId">
	      ${obj.productId}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">评价：</label>
	    <div class="layui-input-block" id="evaluate">
	      ${obj.evaluate}
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">评价人：</label>
	    <div class="layui-input-block" id="userName">
	      ${obj.userName}
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">评价时间：</label>
	    <div class="layui-input-block" id="date">
	      ${obj.date}
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品描述（几颗星）：</label>
	    <div class="layui-input-block" id="productBrand">
	      ${obj.description}
	    </div>
	  </div>
	  
	
	  <div class="layui-form-item">
	    <label class="layui-form-label">物流服务（几颗星）：</label>
	    <div class="layui-input-block" id="logistics">
	      ${obj.logistics}
	    </div>
	  </div>
	  
	 
	  <div class="layui-form-item">
	    <label class="layui-form-label">服务态度（几颗星）：</label>
	    <div class="layui-input-block" id="attitude">
	      ${obj.attitude}
	    </div>
	  </div>
	  
	   <div class="layui-form-item">
    <label class="layui-form-label">审核</label>
    <div class="layui-input-block">
     <input type="radio" lay-filter="auditing" name="auditing" value="1" title="通过" checked="" />
     <input type="radio" lay-filter="auditing" name="auditing" value="0" title="不通过" />
    </div>
   </div>
   
	   <button class="layui-btn" lay-submit lay-filter="submmitFilter" style="display: none;" id="sunmitbtn"></button>
	</form>
	<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
	<script src="${ctx}/static/layui/layui.js" charset="utf-8"></script>
	<script src="${ctx }/static/lib/jquery-form.js"></script>
	<script>
	var parentIndex;
	layui.use(['form'], function(){
		  var form = layui.form
		  ,layer = layui.layer
		  
		  //监听提交
		  form.on('submit(submmitFilter)', function(data){
			  $("#form").ajaxSubmit({
		    		url: "${ctx }/evaluate/set",
		    		type: "post",
		    		success: function(result) {
		    			var code = result.code;
		    			var type = result.type;
		    			if(code == 'success'){
		    				if(type == 'add'){
		    					layer.msg('审核成功', {
		    		    			  icon: 6
		    		    			  ,time: 1500
		    		    		});
		    				}
		    				setTimeout(function(){
		    					parent.conditionReset();
		    					parent.layer.close(parentIndex);
		    				}, 1500);
		    			}
		    		},
		    		error: function() {
		    			layer.msg('审核失败', {
			    			  icon: 5
			    			  ,time: 1500
			    		});
		    		}
		    	});
			    return false;
		  });
		  
	});
	//模拟按钮提交 
	function submitForm(index){
		parentIndex = index;
		$("#sunmitbtn").click();
	}
	
	</script>
	
	
	
</body>
</html>