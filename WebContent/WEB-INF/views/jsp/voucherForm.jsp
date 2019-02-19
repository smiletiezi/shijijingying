<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test="${obj == null}">
		<c:set var="url" value="${ctx }/voucher/insert"></c:set>
		<c:set var="text" value="新增"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${ctx }/voucher/set"></c:set>
		<c:set var="text" value="修改"></c:set>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>${text }代金券</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">                   
	<form class="layui-form" id="form" action="${url }" commandName="obj" lay-filter="formFilter">
	
	 <div class="layui-form-item">
    <label class="layui-form-label">代金券名称</label>
    <div class="layui-input-block">
     <input type="text" name="voucherName" autocomplete="off" placeholder="请输入" class="layui-input"  value="${obj.voucherName}"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">代金券金额</label>
    <div class="layui-input-block">
  <input type="text" name="voucherPar" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.voucherPar }"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">满多少使用</label>
    <div class="layui-input-block">
     <input type="text" name="voucherMore" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.voucherMore}"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">有效期</label>
    <div class="layui-input-block">
     <input type="text" name="voucherTime" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.voucherTime }"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">是否可用于抽奖</label>
    <div class="layui-input-block">
     <input type="radio" lay-filter="voucherType" name="voucherType" value="1" title="可以" checked="" />
     <input type="radio" lay-filter="voucherType" name="voucherType" value="0" title="不可以" />
    </div>
   </div>

<div class="layui-form-item">
    <label class="layui-form-label">是否可积分兑换</label>
    <div class="layui-input-block">
     <input type="radio" lay-filter="present" name="present" value="1" title="可以" checked="" />
     <input type="radio" lay-filter="present" name="present" value="0" title="不可以" />
    </div>
   </div>

   <div class="layui-form-item">
    <label class="layui-form-label">兑奖积分点数</label>
    <div class="layui-input-block">
     <input type="text" name="integration" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.integration}"/>
    </div>
   </div>
   
   
    <input type="hidden" name="voucherCreateTime" value="${obj.voucherCreateTime }">
    <input type="hidden" name="voucherExpiry" value="${obj.voucherExpiry }">
    <input type="hidden" name="voucherUse" value="${obj.voucherUse }">
	 <input type="hidden" name="voucherId" value="${obj.voucherId }">
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
		  
		  form.on('radio(present)',function(data) {

		      if(data.value==1){
		        $("input[name='integration']").parent().parent().show();
		      }else{
		        $("input[name='integration']").parent().parent().hide();
		      }
		      present=data.value;
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