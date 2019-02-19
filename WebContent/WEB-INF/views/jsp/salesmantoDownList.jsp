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
<div class="layui-form-item" style="display: none;">
	    <label class="layui-form-label"><span style="color:red;">*</span>ID</label>
	    <div class="layui-input-block">
	      <input type="text" name="userid"  id="userid" lay-verify="userid" autocomplete="off" class="layui-input" value="${obj.userid}">
	    </div>
	  </div>
<!-- 搜索 -->
	<div class="search">
		<form class="layui-form"> 
		        用户名：
			<div class="layui-inline">
				<input class="layui-input" name="name" id="name" autocomplete="off" placeholder="请输入用户名">
			</div>
		  	<button class="layui-btn" type="button" onclick="conditionSearch();">搜索</button>
		  	<button class="layui-btn" type="button" onclick="conditionReset();">重置</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  	年目标：
			<div class="layui-inline">
				<input class="layui-input" name="year" id="year" disabled="disabled"  autocomplete="off"  value="${obj.year}">
			</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			已完成：
			<div class="layui-inline">
				<input class="layui-input" name="finish" id="finish" disabled="disabled"  autocomplete="off" value="${finish }">
			</div></br></br>
			月目标：
			<div class="layui-inline">
				<select id="edit_exam_school">
                   <option value="">请选择</option>
      <option value="01">一月份</option>
      <option value="02">二月份</option>
      <option value="03">三月份</option>
      <option value="04">四月份</option>
      <option value="05">五月份</option>
      <option value="06">六月份</option>
      <option value="07">七月份</option>
      <option value="08">八月份</option>
      <option value="09">九月份</option>
      <option value="10">十月份</option>
      <option value="11">十一月份</option>
      <option value="12">十二月份</option>
    </select>
			</div>
			<button class="layui-btn" type="button" onclick="search();">查看月完成量</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			我的收益：
			<div class="layui-inline">
				<input class="layui-input" name="money" id="money" disabled="disabled"  autocomplete="off"  value="${money}">
			</div>
	  	</form>
	</div>
	<!-- 顶部按钮 
	<div class="layui-btn-container tbtn layui-inline">
	  <button class="layui-btn " onclick="refresh();">刷新列表</button>
	</div>-->
	<table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>
	<!-- 右边按钮 -->
	<script type="text/html" id="rbtn">
      <a class="layui-btn  layui-btn-xs" lay-event="detail">查看详情</a>
	</script>
	
	<!-- JS -->
	<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
	<script src="${ctx}/static/layui/layui.js" charset="utf-8"></script>
	<script>
	//加载table模块
	var id=$("#userid").val();
	var table,form;
	layui.use(['table','form'], function(){
	  table = layui.table;
	  form = layui.form;
	  //加载表格数据
	  table.render({
	    elem: '#tableId'
	    ,url:'${ctx}/salesmanGroup/toDownData?userid='+id
	    ,cellMinWidth: 80
	    ,page: true
	    ,cols: [[
	      {type:'checkbox'}
	      ,{field:'userId', title: '合伙人ID', sort: true}
	      ,{field:'name', title: '用户名'}
	      ,{field:'money', title: '客户总消费'}
	      ,{field:'cash', title: '合伙人收益'}
	      ,{title:'操作',align:'center', toolbar: '#rbtn'}
	    ]]
	  });
	  
	  //监听右边按钮
	  table.on('tool(tableFilter)', function(obj){
	     var data = obj.data //获得当前行数据
	    ,layEvent = obj.event; //获得 lay-event 对应的值
	    if(layEvent === 'detail'){
	      doDetails('业绩详情',data.userId);
	    }
	  });
	  
	  
	});
	//刷新页面
	function refresh(){
		//$(".layui-laypage-btn")[0].click(); 
	   // 执行重载
	    table.reload('tableId', {
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
			  content: '${ctx}/salesmanGroup/toDetails?userId='+id
		 });
	}
	
	
	
	//条件查询
	function conditionSearch(){
		var name = $("#name").val();
	    table.reload('tableId', {
	      page: {
	        curr: 1 //重新从第 1 页开始
	      }
	      ,where: {
	    	  name: name,
	      }
	    });
	}
	
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
	
	
	//查看月完成情况
	function search(){
		var month = $("#edit_exam_school").val();
		var id=$("#userid").val();
		layer.open({
			  type: 2,
			  title: "月业绩",
			  shade: 0.8,
			  maxmin: true,
			  area: ['100%','100%'],
		  content: '${ctx}/salesmanGroup/toMonth?month='+month+'&id='+id
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
	</script>
</body>
</html>