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
	<table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>
	<!-- 右边按钮 -->
	<script type="text/html" id="rbtn">
      <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
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
	    ,url:'${ctx}/conversion/toListData'
	    ,cellMinWidth: 80
	    ,page: true
	    ,cols: [[
	      {type:'checkbox'}
	      ,{field:'conversionId', title: 'ID', sort: true}
	      ,{field:'voucher', title: '消费金额与积分兑率'} 
	      ,{field:'draw', title: '消费金额与抽奖券兑率'}
	      ,{field:'probability', title: '中奖概率'}
	      ,{title:'操作',align:'center', toolbar: '#rbtn'}
	    ]]
	  });
	  
	  //监听右边按钮
	  table.on('tool(tableFilter)', function(obj){
	     var data = obj.data //获得当前行数据
	    ,layEvent = obj.event; //获得 lay-event 对应的值
	    if(layEvent === 'edit'){
	      doForm('修改兑率',data.conversionId);
	    }
	  });
	}); 
	
	//重置
	function conditionReset(){
    	//刷新下拉菜单
    	form.render('select','conditionTypeFilter');
    	table.reload('tableId', {
  	      page: {
  	        curr: 1 //重新从第 1 页开始
  	      }
  	      ,where: {
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
			  content: '${ctx}/conversion/toForm?conversionId='+id,
			  btn: ['立即提交'],
			  yes: function(index, layero){ 
				  var nodeName = window["layui-layer-iframe" + index];
				  nodeName.submitForm(index);
			  }
		 });
	}
	
	</script>
</body>
</html>