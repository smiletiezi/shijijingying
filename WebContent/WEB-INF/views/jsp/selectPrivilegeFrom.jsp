<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test="${role == null}">
		<c:set var="url" value="${ctx}/user/insert"></c:set>
		<c:set var="text" value="新增"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${ctx}/user/update"></c:set>
		<c:set var="text" value="修改"></c:set>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>${text}角色</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/ztree/css/metroStyle/metroStyle.css" >
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">                   
	<form class="layui-form" id="form" action="${url}" commandName="role" lay-filter="formFilter">
		<ul id="orgTree" class="ztree"></ul>
		<button class="layui-btn" type="button" lay-filter="submmitFilter" style="display: none;"></button>
	</form>
	     
	<!-- JS -->
	<script src="${ctx}/static/lib/jquery-1.11.3.min.js"></script>
	<script src="${ctx}/static/layui/layui.js" charset="utf-8"></script>
	<script src="${ctx }/static/lib/jquery-form.js"></script>
	<script src="${ctx}/static/ztree/js/jquery.ztree.all.min.js"></script>
	
	<script>
	var parentIndex;
	layui.use(['form'], function(){
		  var form = layui.form
		  ,layer = layui.layer
		  
	});

	//树形菜单
	var setting = {
	  check: {
	    enable: true,
	    chkboxType :{ "Y" : "ps", "N" : "ps" }
	  },
	  data: {
	    simpleData: {
	      enable: true
	    }
	  }
	};
	
//选中
	
	var orgTree = null ;
	$.post(
	  "${ctx}/sysRole/rolePrivilege",
	  {},
	  function(zNodes){
		var idsArr = parent.zz().split(",");
	    for(var i in zNodes){
	        for(var k in idsArr){
	          if(zNodes[i].id == idsArr[k]){
	            zNodes[i]['checked'] = true;
	          }
	        /*   //将已接收的部门id存起来
	          receiveOrgIds.push($ids[k].innerText); */
	        }
	      }
	    orgTree = $.fn.zTree.init($("#orgTree"), setting, zNodes);
	    orgTree.expandAll(true);
	  }
	);
	var orgIds = "";
	var orgname="";
	//返回选中节点
	function optorgIds() {
		var nodes = orgTree.getCheckedNodes(true),
		 orgIds = "";  
	  	//去除掉已经发送过的节点
	    for(var i=0;i<nodes.length;i++){  
	      var isReceived = false;
	      if(!isReceived){
	        orgname+=nodes[i].name+',';
	        orgIds += nodes[i].id + ",";
	      }
	    }  
		return {orgname:orgname,orgIds:orgIds};
	}
	
	
	function optorgname() {
		return orgname;
	}
	
	function orgIds1() {
		return orgIds;
	}

	
	//模拟按钮提交 
	function closeForm(index){
		parent.layer.close(index);
	
	}
	</script>

</body>
</html>