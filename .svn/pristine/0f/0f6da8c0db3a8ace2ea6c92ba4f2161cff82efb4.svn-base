<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test="${obj == null}">
		<c:set var="url" value="${ctx }/sysRole/insertRole"></c:set>
		<c:set var="text" value="新增"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${ctx }/sysRole/updateRole"></c:set>
		<c:set var="text" value="修改"></c:set>
	</c:otherwise>
</c:choose>
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
	<form class="layui-form" id="form" action="${url}" commandName="obj" lay-filter="formFilter">

	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>角色名称</label>
	    <div class="layui-input-block">
	      <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入角色名称" class="layui-input" value="${obj.name}">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>角色标识</label>
	    <div class="layui-input-block">
	      <input type="text" name="permission" lay-verify="permission" autocomplete="off" placeholder="请输入角色标识" class="layui-input" value="${obj.permission}">
	    </div>
	  </div>
<%-- <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>选择下级</label>
	    <div class="layui-input-block">
	      <input type="text" name="roleopt" id="roleopt" lay-verify="" autocomplete="off" placeholder="请选择" class="layui-input" value="" readonly>
	      <input type="hidden" name="roleoptid" id="roleoptid"  autocomplete="off" placeholder="请选择" class="layui-input" value="${privilegeId}" readonly >
	      <a class="layui-btn layui-btn-xs" lay-event="edit" onclick="xuanzexiaji();">选择</a>
	    </div>
	  </div> --%>
	  <div class="layui-form-item">
	    <label class="layui-form-label">选择上级</label>
	    <div class="layui-input-block">
	      <c:if test="${allsysrole eq null} ">
	        <select name="parentId" lay-verify="" disabled>
	           <option value="">暂无角色</option>
	        </select>
	      </c:if>
	     <select name="parentId" lay-verify=""  id="parentId">
	     	<option value="">请点击选择</option>
	     	<c:forEach items="${allrole}" var="allrole">
	     		  <option value="${allrole.id}">${allrole.name}</option>
	     	</c:forEach>
	     </select>
	      <span style="color: red;">不选择默认为当前用户的下级</span>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>功能权限</label>
	    <div class="layui-input-block">
	      <input type="text" name="opt" id="opt" lay-verify="opt" autocomplete="off" placeholder="请选择" class="layui-input" value="${privilegeName}" readonly>
	      <input type="hidden" name="optid" id="optid"  autocomplete="off" placeholder="请选择" class="layui-input" value="${privilegeId}" readonly >
	      <a class="layui-btn layui-btn-xs" lay-event="edit" onclick="choice();">选择</a>
	    </div>
	  </div>
	  
	
<%--  <input type="hidden" name="state" id="state" <c:if test="${null == obj || obj.state }">value="0"</c:if>>--%>
	  <input type="hidden" name="id" value="${obj.id}"> 
	  <button class="layui-btn" lay-submit lay-filter="submmitFilter" style="display: none;" id="sunmitbtn"></button>
	</form>
	     
	<!-- JS -->
	<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
	<script src="${ctx}/static/layui/layui.js" charset="utf-8"></script>
	<script src="${ctx}/static/lib/jquery-form.js"></script>
	<script>
	
	$(function () {
		var parentId='${obj.parentId}';
		if(parentId!='null' && parentId !=''){
			$("#parentId").val(parentId);
		}
	})
	var parentIndex;
	layui.use(['form'], function(){
		  var form = layui.form
		  ,layer = layui.layer
		  //监听开关
		  form.on('switch(stateFilter)',function(data){
			  	if(data.elem.checked){
			  		$("#state").val(0);
			  	}else{
			  		$("#state").val(1);
			  	}
		  })
		  //表单验证
		  form.verify({
			  name: function(value, item){ //value：表单的值、item：表单的DOM对象
				value = $.trim(value);
			  	if(value.length == 0){
			  		return '角色名称不能为空';
			  	}
			  	if(value.length > 20){
			  		return '角色名称不能大于20个字'
			  	}
			    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
			      return '角色名称不能有特殊字符';
			    }
			  },
			  permission: function(value, item){ //value：表单的值、item：表单的DOM对象
					value = $.trim(value);
				  	if(value.length == 0){
				  		return '角色标识不能为空';
				  	}
				  	if(value.length > 20){
				  		return '角色标识不能大于20个字'
				  	}
				    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
				      return '角色标识不能有特殊字符';
				    }
				  },
				 opt:function(value, item){ //value：表单的值、item：表单的DOM对象
						value = $.trim(value);
					  	if(value.length == 0){
					  		return '请选择角色权限';
					  	}
					  }
		  });
		  //监听提交
		  form.on('submit(submmitFilter)', function(data){
			  $("#form").ajaxSubmit({
		    		url: "${url}",
		    		type: "post",
		    		success: function(result) {
		    			var code = result.code;
		    			var type = result.type;
		    			if(code == 'success'){
		    				if(type == 'add'){
		    					layer.msg('添加成功', {
		    		    			  icon: 6
		    		    			  ,time: 1500
		    		    		});
		    				}else{
		    					layer.msg('修改成功', {
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
		    			layer.msg('提交失败', {
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
	
	function choice(){
		xuanze('选择功能权限');
	}
	
	function xuanze(title) {
		layer.open({
			  type: 2,
			  title: title,
			  shade: 0.8,
			  maxmin: true,
			  area: ['70%','90%'],
			  content: '${ctx}/sysRole/toSelectRoleForm',
			  btn: ['确认选择'],
			  yes: function(index, layero){ 
				  var node = window["layui-layer-iframe" + index];
				  var optwin=node.optorgIds();
				  $("#opt").val(optwin.orgname);
				  $("#optid").val(optwin.orgIds);
				  node.closeForm(index);
			  }
		 });
	}
	//选择下级角色
	function xuanzexiaji() {
		layer.open({
			  type: 2,
			  title:'选择下级角色',
			  shade: 0.8,
			  maxmin: true,
			  area: ['90%','90%'],
			  content: '${ctx}/sysRole/toSelectAllRoleForm',
			  btn: ['确认选择'],
			  yes: function(index, layero){ 
				  var node = window["layui-layer-iframe" + index];
				  var optwin=node.optorgIds();
				  $("#roleopt").val(optwin.orgname);
				  $("#roleoptid").val(optwin.orgIds);
				  node.closeForm(index);
			  }
		 });
		
	}
	//返回自己页面的optid
	function zz() {
		return  $("#optid").val();
	}
	</script>

</body>
</html>