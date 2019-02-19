<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test="${obj == null}">
		<c:set var="url" value="${ctx }/bxarea/insert"></c:set>
		<c:set var="text" value="新增"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${ctx }/bxarea/set"></c:set>
		<c:set var="text" value="修改"></c:set>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>${text }邮资</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">                   
	<form class="layui-form" id="form" action="${url }" commandName="obj" lay-filter="formFilter">
	
	 <div class="layui-form-item">
    <label class="layui-form-label">省份/产品类型名称</label>
    <div class="layui-input-block">
     <input type="text" name="name" autocomplete="off" placeholder="请输入" class="layui-input"  value="${obj.name}"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">首件邮费</label>
    <div class="layui-input-block">
  <input type="text" name="priceOne" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.priceOne }"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">加一件邮费</label>
    <div class="layui-input-block">
     <input type="text" name="bxadd" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.bxadd}"/>
    </div>
   </div>

<div class="layui-form-item">
    <label class="layui-form-label">是否包邮</label>
    <div class="layui-input-block">
     <input type="radio" lay-filter="postage" name="postage" value="0" title="包邮" />
     <input type="radio" lay-filter="postage" name="postage" value="1" title="不包邮" />
    </div>
   </div>

   <div class="layui-form-item">
    <label class="layui-form-label">满多少包邮</label>
    <div class="layui-input-block">
     <input type="text" name="more" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.more}"/>
    </div>
   </div>
   
   
	 <input type="hidden" name="id" value="${obj.id }">
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
		  
		  form.on('radio(postage)',function(data) {

		      if(data.value==0){
		        $("input[name='more']").parent().parent().show();
		      }else{
		        $("input[name='more']").parent().parent().hide();
		      }
		      postage=data.value;
		  })
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

	
	</script>

</body>
</html>