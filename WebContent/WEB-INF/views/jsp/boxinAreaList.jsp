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
	<!-- 顶部按钮 -->
	<div class="layui-btn-container tbtn">
	  <button class="layui-btn " onclick="refresh();">刷新列表</button>
	  <button class="layui-btn" onclick="add();">新增</button>
	</div>
	<table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>
	<!-- 右边按钮 -->
	<script type="text/html" id="rbtn">
      <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
      <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
	<!-- 代金券状态转化模板  -->
	<script id="postageConversion" type="text/html">  
    	{{postageToChinese(d.postage)}}   
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
	    ,url:'${ctx}/bxarea/toListData'
	    ,cellMinWidth: 80
	    ,page: true
	    ,cols: [[
	      {type:'checkbox'}
	      ,{field:'id', title: 'ID', sort: true}
	      ,{field:'name', title: '省份/产品类型'}
	      ,{field:'priceOne', title: '起送价格'} 
	      ,{field:'bxadd', title: '满一件加多少'}
	      ,{field:'postage', title: '是否包邮',templet:'#postageConversion'}
	      ,{field:'more', title: '满多少包邮'}
	      ,{title:'操作',align:'center', toolbar: '#rbtn'}
	    ]]
	  });
	  
	  //监听右边按钮
	  table.on('tool(tableFilter)', function(obj){
	     var data = obj.data //获得当前行数据
	    ,layEvent = obj.event; //获得 lay-event 对应的值
	    if(layEvent === 'del'){
	      doDelete('您确定要删除数据吗？删除后将无法恢复。',data.id);	
	    }
	    if(layEvent === 'edit'){
	      doForm('代金券编辑',data.id);
	    }
	  });
	}); 
	
	//刷新页面
	function refresh(){
		 table.reload('tableId', {
		    });
	}
	
	//新增
	function add(){
		doForm('新增产品',0);
	}
	
	//条件查询
	function conditionSearch(){
	    table.reload('tableId', {
	      page: {
	        curr: 1 //重新从第 1 页开始
	      }
	      ,where: {
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
	
	//删除
	function doDelete(title,id){
		layer.confirm(title, function(index) {
			$.ajax({
			     url: '${ctx}/bxarea/redelete',
			     type:"post",
			     //async:false,
			     dataType:"json",
			     data:{id:id},
			     success: function (result) {
			    	 layer.close(index);
			    	 var type = result.type;
					 var code = result.code;
	    			 if(type == 'delete'){
	    				if(code == 'success'){
			    		    layer.msg('删除成功', {
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
	    		    layer.msg('删除失败', {
	    			  icon: 6,
	    			  time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
	    			});
			     }
			});
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
			  content: '${ctx}/bxarea/toForm?id='+id,
			  btn: ['立即提交'],
			  yes: function(index, layero){ 
				  var nodeName = window["layui-layer-iframe" + index];
				  nodeName.submitForm(index);
			  }
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
	
	function postageToChinese(type){
		var text= '';
		switch(type)
		{
		case '0':
			text='包邮';
		  break;
		case '1':
			text='不包邮';
		  break;
		}
		return text;
	}
	
	</script>
</body>
</html>