<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>操作日志列表</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
<link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">
<!-- 搜索条件 -->
	<form class="layui-form">
		<div class="demoTable">
			条件搜索：
	  		<div class="layui-inline">
	    		<input class="layui-input" name="title" id="title" autocomplete="off" placeholder="请输入操作账号、操作描述">
	  		</div>
  		<button class="layui-btn" type="button" onclick="reload()">搜索</button>
  		<button class="layui-btn" type="button" onclick="resetSearch()">重置</button>
		</div>
	</from>
	<input type="hidden" id="pageNum" name="pageNum" value="${pageNum}" />
	<!-- 列表上面按钮 -->
	<div class="layui-btn-container tbtn">
		<button class="layui-btn" type="button" onclick="reload()">刷新列表</button>
	</div>
	<!-- 列表 -->
	<table class="layui-table" id="logList" lay-filter="logList">
<!-- 		<thead>
			<tr>
				<th lay-data="{type:'checkbox',fixed: 'left'}"></th>
				<th lay-data="{field:ipAddr}">IP</th>
				<th lay-data="{field:operateDesc}">操作人</th>
				<th lay-data="{field:type}">是否正常</th>
				<th lay-data="{field:createTime}">操作方法</th>
				<th lay-data="{field:method}">操作时间</th>
			</tr>
		</thead> -->
	</table>
	<!-- 列表右边按钮 -->
	<script type="text/html" id="barDemo">
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">预览</a>
   		<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
   		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
	<!-- 对是否推荐数据处理 -->
	<script src="${ctx}/static/layui/layui.js" charset="utf-8"></script>
	<script src="${ctx}/static/lib/jquery-1.11.3.min.js" charset="utf-8"></script>
	<!-- 转换type -->
	<script id="typeTemplate" type="text/html">  
		{{typeTransform(d.type)}}
	</script>
	<script>
	
		var table;
		layui.use('table',function() {
			 table = layui.table;
			//执行渲染
			table.render({
			  elem: '#logList' //
			  ,url: '${ctx}/sysLog/sysLogListdata' //数据接口
			 ,page: true
		     ,cols: [[
		    	  {type:'checkbox'}
		    	 ,{field: 'ipAddr', title: 'IP'}
		    	 ,{field: 'operateDesc', title: '操作描述'}
		    	 ,{field: 'type', title: '是否正常',templet:"#typeTemplate"}
		    	 ,{field: 'loginName', title: '操作账号'}
		    	 ,{field: 'method', title: '操作方法'}
		    	 ,{field: 'createTime', title: '操作时间',templet:'<div>{{ Format(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>'}
		     ]] //设置表头
			
			
			});
		});
		//重置
		function resetSearch(){
			var title=$("#title").val('');
		   	table.reload('logList', {
		  	      page: {
		  	        curr: 1 //重新从第 1 页开始
		  	      }
		  	      ,where: {
		  	    	title: ''
		  	      }
		  	});
			
		}
		
		//条件查询
		function reload(){
			var condition =$("#title").val();
		    table.reload('logList', {
		      page: {
		        curr: 1 //重新从第 1 页开始
		      }
		      ,where: {
		    	  condition:condition
		      }
		    });
		}
		
		function typeTransform(type) {
			var text='';
			if(type=='1'){
				text='异常';
			}else{
				text='正常';
			}
			return text;
		}
	
		//时间格式处理
		function Format(datetime,fmt) {
			  if (parseInt(datetime)==datetime) {
			    if (datetime.length==10) {
			      datetime=parseInt(datetime)*1000;
			    } else if(datetime.length==13) {
			      datetime=parseInt(datetime);
			    }
			  }
			  datetime=new Date(datetime);
			  var o = {
			  "M+" : datetime.getMonth()+1,                 //月份   
			  "d+" : datetime.getDate(),                    //日   
			  "h+" : datetime.getHours(),                   //小时   
			  "m+" : datetime.getMinutes(),                 //分   
			  "s+" : datetime.getSeconds(),                 //秒   
			  "q+" : Math.floor((datetime.getMonth()+3)/3), //季度   
			  "S"  : datetime.getMilliseconds()             //毫秒   
			  };   
			  if(/(y+)/.test(fmt))   
			  fmt=fmt.replace(RegExp.$1, (datetime.getFullYear()+"").substr(4 - RegExp.$1.length));   
			  for(var k in o)   
			  if(new RegExp("("+ k +")").test(fmt))   
			  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
			  return fmt;
			}	
		
		
		</script>

</body>
</html>