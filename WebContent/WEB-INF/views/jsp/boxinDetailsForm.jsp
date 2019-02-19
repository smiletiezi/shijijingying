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
	    <label class="layui-form-label">注册时间：</label>
	    <div class="layui-input-block">
	      ${obj.usercreationtime}
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户地址：</label>
	    <div class="layui-input-block">
	      ${obj.useraddress}
	    </div>
	  </div>
	  
	<form class="layui-form" id="form" action="${ctx }/sysuser/examine" commandName="obj" lay-filter="formFilter">
	<div class="layui-form-item" style="display: none;">
	    <label class="layui-form-label"><span style="color:red;">*</span>ID</label>
	    <div class="layui-input-block">
	      <input type="text" name="userid" lay-verify="userid" autocomplete="off" class="layui-input" value="${obj.userid}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>业务员身份验证</label>
	    <div class="layui-input-block">
	      <input type="radio" lay-filter="useraccount" name="useraccount" value="2" title="通过" checked="" />
          <input type="radio" lay-filter="useraccount" name="useraccount" value="1" title="不通过" />
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
		  //监听提交
		  form.on('submit(submmitFilter)', function(data){
			  $("#form").ajaxSubmit({
		    		url: "${ctx }/sysuser/examine",
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