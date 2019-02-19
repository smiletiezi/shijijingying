<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test="${obj == null}">
		<c:set var="url" value="${ctx }/sysCoupon/insertSysCoupon"></c:set>
		<c:set var="text" value="新增"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${ctx }/sysCoupon/updateSysCoupon"></c:set>
		<c:set var="text" value="修改"></c:set>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>${text }优惠券信息</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">                   
	<form class="layui-form" id="form" action="${url}" commandName="obj" lay-filter="formFilter">

	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>优惠券名称</label>
	    <div class="layui-input-block">
	      <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入优惠券名称" class="layui-input" value="${obj.name}">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>发放类型</label>
	    <div class="layui-input-block">
	      <input type="text" name="couponSendType" lay-verify="coupon_send_type" autocomplete="off" placeholder="优惠券发放类型" class="layui-input" value="${obj.couponSendType}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>结算类型</label>
	    <div class="layui-input-block">
	      <input type="text" name="couponSettingType" lay-verify="coupon_setting_type" autocomplete="off" placeholder="优惠券结算类型,1,折抵价，2,满减券，3,折扣券" class="layui-input" value="${obj.couponSettingType}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>优惠金额</label>
	    <div class="layui-input-block">
	      <input type="text" name="couponMoney" lay-verify="coupon_money" autocomplete="off" placeholder="优惠金额" class="layui-input" value="${obj.couponMoney}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>优惠券发放数量</label>
	    <div class="layui-input-block">
	      <input type="text" name="couponNumber" lay-verify="coupon_number" autocomplete="off" placeholder="优惠券发放数量" class="layui-input" value="${obj.couponNumber}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>领取数量 0 至 max（减）</label>
	    <div class="layui-input-block">
	      <input type="text" name="couponAmount" lay-verify="coupon_amount" autocomplete="off" placeholder="领取数量 0 至 max（减）" class="layui-input" value="${obj.couponAmount}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>优惠券发放时间</label>
	    <div class="layui-input-block">
	      <input type="text" name="couponStartTime" lay-verify="coupon_start_time" autocomplete="off" placeholder="优惠券发放时间" class="layui-input" value="${obj.couponStartTime}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>发放结束时间</label>
	    <div class="layui-input-block">
	      <input type="text" name="couponEndTime" lay-verify="coupon_end_time" autocomplete="off" placeholder="发放结束时间" class="layui-input" value="${obj.couponEndTime}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>优惠券有效开始时间。</label>
	    <div class="layui-input-block">
	      <input type="text" name="couponStartPeriod" lay-verify="coupon_start_period" autocomplete="off" placeholder="优惠券有效开始时间。" class="layui-input" value="${obj.couponStartPeriod}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>优惠券有效结束时间。</label>
	    <div class="layui-input-block">
	      <input type="text" name="couponEndPeriod" lay-verify="coupon_end_period" autocomplete="off" placeholder="优惠券有效结束时间。" class="layui-input" value="${obj.couponEndPeriod}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>冻结</label>
	    <div class="layui-input-block">
	      <input type="text" name="couponStatus" lay-verify="coupon_status" autocomplete="off" placeholder="冻结，0正常使用，1已冻结，不可使用" class="layui-input" value="${obj.couponStatus}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>优惠券的使用说明</label>
	    <div class="layui-input-block">
	      <input type="text" name="couponRemarks" lay-verify="coupon_remarks" autocomplete="off" placeholder="优惠券的使用说明" class="layui-input" value="${obj.couponRemarks}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>是否删除</label>
	    <div class="layui-input-block">
	      <input type="text" name="isDelete" lay-verify="is_delete" autocomplete="off" placeholder="0未删除 1删除" class="layui-input" value="${obj.isDelete}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>优惠券创建人</label>
	    <div class="layui-input-block">
	      <input type="text" name="createCouponUserId" lay-verify="create_coupon_user_id" autocomplete="off" placeholder="优惠券创建人" class="layui-input" value="${obj.createCouponUserId}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>优惠券创建时间</label>
	    <div class="layui-input-block">
	      <input type="text" name="createCouponTime" lay-verify="create_coupon_time" autocomplete="off" placeholder="优惠券创建时间" class="layui-input" value="${obj.createCouponTime}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>优惠券修改人id</label>
	    <div class="layui-input-block">
	      <input type="text" name="updateCouponUserId" lay-verify="update_coupon_user_id" autocomplete="off" placeholder="优惠券修改人id" class="layui-input" value="${obj.updateCouponUserId}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>优惠券修改时间</label>
	    <div class="layui-input-block">
	      <input type="text" name="updateCouponTime" lay-verify="update_coupon_time" autocomplete="off" placeholder="优惠券修改时间" class="layui-input" value="${obj.updateCouponTime}">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>商品id</label>
	    <div class="layui-input-block">
	      <input type="text" name="shopClassId" lay-verify="shop_class_id" autocomplete="off" placeholder="商品id" class="layui-input" value="${obj.shopClassId}">
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
	  <!--   未修改 -->
	      <c:if test="${allsysrole eq null} ">
	        <select name="city" lay-verify="" disabled>
	           <option value="0">暂无角色</option>
	        </select>
	      </c:if>
	     <select name="parentId" lay-verify=""  id="parentId">
	     	<option value="0">请点击选择</option>
	     	<!--   未修改 -->
	     	<c:forEach items="${allrole}" var="allrole">
	     		  <option value="${allrole.id}">${allrole.name}</option>
	     	</c:forEach>
	     </select>
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
			  		return '优惠券名称不能为空';
			  	}
			  	if(value.length > 20){
			  		return '优惠券称不能大于20个字'
			  	}
			    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
			      return '优惠券名称不能有特殊字符';
			    }
			  },
			 /*  注了 */
			  /* permission: function(value, item){ //value：表单的值、item：表单的DOM对象
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
				  }, */
				 opt:function(value, item){ //value：表单的值、item：表单的DOM对象
						value = $.trim(value);
					  	if(value.length == 0){
					  		return '请选择优惠券权限';
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
			  content: '${ctx}/sysCoupon/toSelectRoleForm',
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
			  content: '${ctx}/sysCoupon/toSelectAllRoleForm',
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