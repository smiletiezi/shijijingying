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
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">
	<!-- 搜索 -->
	<div class="search">
		<form class="layui-form"> 
		        订单编号：
			<div class="layui-inline">
				<input class="layui-input" name="orderNumber" id="orderNumber" autocomplete="off" placeholder="请输入订单编号">
			</div>
		  	<button class="layui-btn" type="button" onclick="conditionSearch();">搜索</button>
		  	<button class="layui-btn" type="button" onclick="conditionReset();">重置</button>
	  	</form>
	</div>
	<!-- 顶部按钮 -->
	<div class="layui-btn-container tbtn">
	  <button class="layui-btn " onclick="refresh();">刷新列表</button>
	  <button class="layui-btn layui-btn-danger" onclick="batch_upload();">导出数据</button>
	</div>
	<table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>
	<!-- 右边按钮 -->
	<script type="text/html" id="rbtn">
      <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
      <a class="layui-btn layui-btn-xs" lay-event="edit">发货</a>
	</script>
    <!-- 订单类型转化模板  -->
	<script id="deliveryConversion" type="text/html">  
    	{{deliveryToChinese(d.delivery)}}   
    </script>
    <!-- 订单类型转化模板  -->
	<script id="shipTypeConversion" type="text/html">  
    	{{shipTypeToChinese(d.shipType)}}   
    </script>
    <!-- 用户类型转化模板  -->
	<script id="belongConversion" type="text/html">  
    	{{belongToChinese(d.belong)}}   
    </script>
   
	<!-- JS -->
	<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
	<script src="${ctx}/static/layui/layui.js" charset="utf-8"></script>
	<script>
	//加载table模块
	var table,form;
	layui.use(['table','form'], function(){
	  table = layui.table;
	  form = layui.form;
	  //加载表格数据
	  table.render({
	    elem: '#tableId'
	    ,url:'${ctx}/order/toListData'
	    ,cellMinWidth: 80
	    ,page: true
	    ,cols: [[
	      {type:'checkbox'}
	      ,{field:'orderId', title: 'ID', sort: true}
	      ,{field:'orderNumber', title: '订单编号'} 
	      ,{field:'belong', title: '订单归属',templet:'#belongConversion'}
	      ,{field:'delivery', title: '发货状态',templet:'#deliveryConversion'}
	      ,{field:'shipType', title: '收货状态',templet:'#shipTypeConversion'}
	      ,{field:'shipPerson', title: '收货人'}
	      ,{field:'shipAddress', title: '收货地址'}
	      ,{field:'orderPhone', title: '收货人电话'}
	      ,{field:'logisticsNumber', title: '物流编号'}
	      ,{field:'logisticsName', title: '物流名称'}
	      ,{field:'orderPrice', title: '订单价格'}
	      ,{title:'操作',align:'center', toolbar: '#rbtn'}
	    ]]
	  });
	  
	  //监听右边按钮
	  table.on('tool(tableFilter)', function(obj){
	     var data = obj.data //获得当前行数据
	    ,layEvent = obj.event; //获得 lay-event 对应的值
	    if(layEvent === 'detail'){
	      doDetails('订单详情',data.orderId)
	    }
	    if(layEvent === 'edit'){
	      doForm('发货',data.orderId);
	    }
	  });
	  });
	
		//刷新页面
		function refresh(){
			 table.reload('tableId', {
			    });
		}
	
	
	//条件查询
	function conditionSearch(){
		var orderNumber = $("#orderNumber").val();
	    table.reload('tableId', {
	      page: {
	        curr: 1 //重新从第 1 页开始
	      }
	      ,where: {
	    	  orderNumber: orderNumber
	      }
	    });
	}
	
	//重置
	function conditionReset(){
		$("#orderNumber").val('');
    	//刷新下拉菜单
    	form.render('select','conditionTypeFilter');
    	table.reload('tableId', {
  	      page: {
  	        curr: 1 //重新从第 1 页开始
  	      }
  	      ,where: {
  	    	orderNumber: ''
  	      }
  	    });
	}
	
	
	
	//form弹出框
	function doForm(title,id){
		layer.open({
			  type: 2,
			  title: title,
			  shade: 0.8,
			  maxmin: true,
			  area: ['100%','100%'],
			  content: '${ctx}/order/toForm?orderId='+id,
			  btn: ['立即提交'],
			  yes: function(index, layero){ 
				  var nodeName = window["layui-layer-iframe" + index];
				  nodeName.submitForm(index);
			  }
		 });
	}
	
	//details弹出框
	function doDetails(title,id){
		layer.open({
			  type: 2,
			  title: title,
			  shade: 0.8,
			  maxmin: true,
			  area: ['100%','100%'],
			  content: '${ctx}/order/toDetails?orderId='+id
		 });
	}
	
	//导出数据
	function batch_upload(){
		  var array = new Array();
		  var checkStatus = table.checkStatus('tableId')
	      ,data = checkStatus.data;
		  if(data.length == 0){
			  layer.msg('请选择');
			  return;
		  }
		  for (var i = 0; i < data.length; i++) {
				var obj = data[i];
				array.push(obj.orderId);
		  }
		  window.open("${ctx}/order/expot?ids="+array);
	}
	
	//导出
	function doBatchUpload(title,array){
		layer.confirm(title, function(index) {
			$.ajax({
			     url: '${ctx}/order/expot',
			     type:"post",
			     //async:false,
			     data:{ids:array},
			     success: function (result) {
			    	 layer.close(index);
			    	 var type = result.type;
					 var code = result.code;
	    			 if(type == 'expot'){
	    				if(code == 'success'){
			    		    layer.msg('导出成功', {
			    			  icon: 6,
			    			  time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
			    			});
		    				setTimeout(function(){
		    					//重置页面
		    					conditionReset();
							}, 1500);
	    				}
	    			 }
			     },error : function(){
			    	layer.close(index);
	    		    layer.msg('导出失败', {
	    			  icon: 6,
	    			  time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
	    			});
			     }
			});
		});
	}
	
	
	
	
	
	/********************************************************** 转换处理方法 **********************************************/
	function timestampToTime(timestamp){
		if(null == timestamp || timestamp.length == 0){
			return '';
		}
		var date = new Date(timestamp);
		var y = date.getFullYear();    
        var m = date.getMonth()+1;    
        m = m<10?'0'+m:m;    
        var d = date.getDate();    
        d = d<10?("0"+d):d;    
        var h = date.getHours();    
        h = h<10?("0"+h):h;    
        var M = date.getMinutes();    
        M = M<10?("0"+M):M;    
        return y+"-"+m+"-"+d+" "+h+":"+M;
	}
	
	function deliveryToChinese(type){
		var text= '';
		switch(type)
		{
		case '0':
			text='代发货';
		  break;
		case '1':
			text='已发货';
		  break;
		}
		return text;
	}
	
	
	function shipTypeToChinese(type){
		var text= '';
		switch(type)
		{
		case '0':
			text='代收货';
		  break;
		case '1':
			text='已收货';
		  break;
		}
		return text;
	}
	
	function belongToChinese(type){
		var text= '';
		switch(type)
		{
		case '0':
			text='子公司';
		  break;
		case '1':
			text='总公司';
		  break;
		}
		return text;
	}
	</script>
</body>
</html>