<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test="${obj == null}">
		<c:set var="url" value="${ctx }/productType/insert"></c:set>
		<c:set var="text" value="新增"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${ctx }/productType/set"></c:set>
		<c:set var="text" value="修改"></c:set>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>${text }产品类型</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">                   
	<form class="layui-form" id="form" action="${url }" commandName="obj" lay-filter="formFilter">
	
	 <div class="layui-form-item">
    <label class="layui-form-label">产品类型名称</label>
    <div class="layui-input-block">
     <input type="text" name="name" autocomplete="off" placeholder="请输入" class="layui-input"  value="${obj.name}"/>
    </div>
   </div>
   
   <div class="layui-form-item">
    <label class="layui-form-label">是否作为一级类型</label>
    <div class="layui-input-block">
     <input type="radio" lay-filter="typeCode" name="typeCode" value="0" title="是" />
     <input type="radio" lay-filter="typeCode"  id="typeCode" name="typeCode" value="1" title="否"  />
    </div>
   </div>

  <div class="layui-form-item" id="role">
	    <label class="layui-form-label"><span style="color:red;">*</span>父级类型</label>
	    <div class="layui-input-block">
	      <select name="productType" id="role_type">
		            <option value="">请选择</option>
	                    <c:forEach items="${role}" var="role">
	                    <option value="${role.productTypeId}">${role.name}</option>
	                    </c:forEach>
           </select>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
    <label class="layui-form-label"><span style="color:red;">*</span>产品类型图标</label>
    <div class="layui-upload">
      <button type="button" class="layui-btn" id="test1">上传图片</button>
    </div>  
  </div>
  
   <div class="layui-form-item">
   <label class="layui-form-label"></label>
      <div class="layui-upload-list">
         <img  width="90px" height="95px" class="layui-upload-img" id="demo1" src="${path }${obj.img}">
    <input type="hidden" id="img" name="img" lay-verify="productImg" value="${obj.img}"/>
        <p id="demoText"></p>
      </div>
   </div>
	  
	  <input type="hidden" name="productTypeId" value="${obj.productTypeId }">
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
		  
		  form.on('radio(typeCode)',function(data) {

		      if(data.value==1){
		        $("#role").show();
		      }else{
		    	  $("#role").hide();
		      }
		  })
		  
		  //监听提交
		  form.on('submit(submmitFilter)', function(data){
			  var typeCode=$("#role_type").val();
		        $("#typeCode").val(typeCode);
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
	
	layui.use('upload', function(){
		  var $ = layui.jquery
		  ,upload = layui.upload;
		      var uploadInst = upload.render({
		        elem: '#test1' //绑定元素
		        ,url: '${ctx }/upload/uploadFile?name=imgFile' //上传接口
		        ,method:'post'   //提交方式
		        ,field:'imgFile' //设定文件域的字段名
		        ,accept:'images' //只允许上传图片
		        ,acceptMime:'image/*' //打开文本选择框时，只显示图片类型
		        ,size:10240  //设置文件最大可允许上传的大小，单位 KB。
		        ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
		          layer.load(); //上传loading
		          //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
		          obj.preview(function(index, file, result){
		            $('#demo1').attr('src',result);
		            $('#demo1').attr('alt',file.name);
		          });
		          }
		        ,done: function(res){//上传完毕回调
		          layer.closeAll('loading');//关闭loading
		          //获得文件的链接等，
		            if(res.state==0){//上传成功
		              $('#img').val(res.link);
		            }else if(res.state==-4){
		              layer.msg('请上传10M以内的图片', {
		                icon: 5
		                ,time: 1500
		            });
		            }else if(res.state==-5){
		              layer.msg('请上传图片格式文件', {
		                icon: 5
		                ,time: 1500
		            });
		            }
		        }
		        ,error: function(){
		          //请求异常回调
		          layer.closeAll('loading');//关闭loading
		        }
		      });
		})
	
	</script>

</body>
</html>