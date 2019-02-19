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
  <title>${text }用户</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">         
       <div class="layui-form-item">
	    <label class="layui-form-label">用户名：</label>
	    <div class="layui-input-block">
	      ${obj.userbusinessname}
	    </div>
	  </div>
	  
	 <div class="layui-form-item">
	    <label class="layui-form-label">身份验证：</label>
	    <div class="layui-input-block">
	    <c:if test="${obj.useraccount == '0'}">普通用户</c:if>	
	      <c:if test="${obj.useraccount == '1'}">业务员待审核</c:if>
	      <c:if test="${obj.useraccount == '2'}">业务员</c:if>
	      <c:if test="${obj.useraccount == '3'}">合伙人</c:if>
	    </div>
	  </div>
	  
	<form class="layui-form" id="form" action="${ctx }/sysuser/set" commandName="obj" lay-filter="formFilter">
	<div class="layui-form-item" style="display: none;">
	    <label class="layui-form-label"><span style="color:red;">*</span>ID</label>
	    <div class="layui-input-block">
	      <input type="text" name="userid" lay-verify="userid" autocomplete="off" class="layui-input" value="${obj.userid}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>年目标</label>
	    <div class="layui-input-block">
	      <input type="text" name="year" lay-verify="year" autocomplete="off" placeholder="请输入年目标" class="layui-input" value="${obj.year}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>业务范围</label>
	    <div class="layui-input-block">
	      <input type="text" name="sphere" lay-verify="sphere" autocomplete="off" placeholder="请输入业务范围" class="layui-input" value="${obj.sphere}">
	    </div>
	  </div>
	  <button class="layui-btn" lay-submit lay-filter="submmitFilter" style="display: none;" id="sunmitbtn"></button>
	</form>
	     
	<!-- JS -->
	<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
	<script src="${ctx}/static/layui/layui.js" charset="utf-8"></script>
	<script src="${ctx }/static/lib/jquery-form.js"></script>
	<script>
	var parentIndex;
	layui.use(['form'], function(){
		  var form = layui.form
		  ,layer = layui.layer
		  //表单验证
		  form.verify({
			  year: function(value, item){ //value：表单的值、item：表单的DOM对象
				value = $.trim(value);
			  	if(value.length == 0){
			  		return '年目标不能为空';
			  	}
			  	if(value.length > 20){
			  		return '年目标长度不能大于20个字'
			  	}
			  }
		  });
		  //监听提交
		  form.on('submit(submmitFilter)', function(data){
			  $("#form").ajaxSubmit({
		    		url: "${ctx }/sysuser/set",
		    		type: "post",
		    		success: function(result) {
		    			var code = result.code;
		    			var type = result.type;
		    			if(code == 'success'){
		    				if(type == 'update'){
		    					layer.msg('设置成功', {
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
		    			layer.msg('设置失败', {
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