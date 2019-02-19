<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:choose>
	<c:when test="${obj == null}">
		<c:set var="url" value="${ctx }/product/insert"></c:set>
		<c:set var="text" value="新增"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${ctx }/product/set"></c:set>
		<c:set var="text" value="修改"></c:set>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>${text }产品</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">                   
	<form class="layui-form" id="form" action="${url }" commandName="obj" lay-filter="formFilter">
	<input type="hidden" id="proImgOne" name="proImgOne" value="${obj.productImgOne }">
	<input type="hidden" id="proImgTwo"  name="proImgTwo"  value="${obj.productImgTwo }">
	 <div class="layui-form-item">
    <label class="layui-form-label">产品名称</label>
    <div class="layui-input-block">
     <input type="text" name="productName" autocomplete="off" placeholder="请输入" class="layui-input"  value="${obj.productName}"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">产品销售价格</label>
    <div class="layui-input-block">
  <input type="text" name="productSalePrice" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.productSalePrice }"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">产品原价</label>
    <div class="layui-input-block">
     <input type="text" name="productPrice" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.productPrice}"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">产品规格</label>
    <div class="layui-input-block">
     <input type="text" name="productSize" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.productSize }"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">营销方式</label>
    <div class="layui-input-block">
 <input type="radio" lay-filter="productSaleType" name="productSaleType" value="1" title="自营" checked/>
   <input type="radio" lay-filter="productSaleType" name="productSaleType" value="0" title="经销" />
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">产品品牌</label>
    <div class="layui-input-block">
     <input type="text" name="productBrand" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.productBrand}"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">是否促销</label>
    <div class="layui-input-block">
     <input type="radio" lay-filter="productSale" name="productSale" value="1" title="是" checked="" />
     <input type="radio" lay-filter="productSale" name="productSale" value="0" title="否" />
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block">
     <input type="text" name="productRemark" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.productRemark}"/>
    </div>
   </div>


  <div class="layui-form-item">
	    <label class="layui-form-label"><span style="color:red;">*</span>产品类型</label>
	    <div class="layui-input-block">
	      <select name="productType" lay-verify="required">
	        <c:choose>
	           <c:when test="${obj == null}">
		            <option value="">请选择</option>
	                    <c:forEach items="${type}" var="type">
	                    <option value="${type.productTypeId}">${type.name}</option>
	                    </c:forEach>
	           </c:when>
	           <c:otherwise>
						<c:forEach items="${type}" var="type">
						<c:if test="${type.productTypeId==obj.productType }">
						<option value="${type.productTypeId}"  selected = "selected">${type.name}</option>
						</c:if>
						<option value="${type.productTypeId}"  >${type.name}</option>
						</c:forEach>
	           </c:otherwise>
            </c:choose>
	      
		       
           </select>
	    </div>
	  </div>


 <div class="layui-form-item">
    <label class="layui-form-label"><span style="color:red;">*</span>首页图片</label>
    <div class="layui-upload">
      <button type="button" class="layui-btn" id="test1">上传图片</button>
    </div>  
  </div>
  
   <div class="layui-form-item">
   <label class="layui-form-label"></label>
      <div class="layui-upload-list">
         <img  width="90px" height="95px" class="layui-upload-img" id="demo1" src="${path }${obj.productImg}">
    <input type="hidden" id="productImg" name="productImg" lay-verify="productImg" value="${obj.productImg}"/>
        <p id="demoText"></p>
      </div>
   </div>
 
 <div class="layui-form-item">
    <label class="layui-form-label"><span style="color:red;">*</span>产品图片</label>
    <div class="layui-upload">
      <button type="button" class="layui-btn" id="test2">上传图片</button>
    </div>  
  </div>
  
   <div class="layui-form-item">
   <label class="layui-form-label"></label>
      <div class="layui-upload-list" id="productImg_one">
       <img  width="90px" height="95px" class="layui-upload-img" id="demo2" src="">
      <input type="hidden" id="productImgOne" name="productImgOne" lay-verify="productImgOne" value=""/>
        <p id="demoText"></p>
      </div>
   </div>

<div class="layui-form-item">
    <label class="layui-form-label"><span style="color:red;">*</span>详情图片</label>
    <div class="layui-upload">
      <button type="button" class="layui-btn" id="test3">上传图片</button>
    </div>  
  </div>
  
   <div class="layui-form-item">
   <label class="layui-form-label"></label>
      <div class="layui-upload-list" id="productImg_two">
       <img  width="90px" height="95px" class="layui-upload-img" id="demo3" src="">
       <input type="hidden" id="productImgTwo" name="productImgTwo" lay-verify="productImgTwo" value=""/>
        <p id="demoText"></p>
      </div>
   </div>

   <div class="layui-form-item">
    <label class="layui-form-label">产品归属</label>
    <div class="layui-input-block">
    <input type="radio" lay-filter="productBelong" name="productBelong" value="1" title="总公司" checked/>
    <input type="radio" lay-filter="productBelong" name="productBelong" value="0" title="子公司" />
    </div>
   </div>

   <div class="layui-form-item">
    <label class="layui-form-label">产品折扣类型</label>
    <div class="layui-input-block">
   <input type="text" name="productDiscount" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.productDiscount}"/>
    </div>
   </div>
   
   <div class="layui-form-item">
    <label class="layui-form-label">产品提成类型</label>
    <div class="layui-input-block">
   <input type="text" name="percentage" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.percentage}"/>
    </div>
   </div>
   

   <div class="layui-form-item">
    <label class="layui-form-label"><span style="color:red;">*</span>产品视频</label>
    <div class="layui-upload">
      <button type="button" class="layui-btn" id="test4">上传视频</button>
    </div>  
  </div>
  
   <div class="layui-form-item">
   <label class="layui-form-label"></label>
      <div class="layui-upload-list" id="productImg_three">
       <video  width="290px"  controls autoplay height="220px"  id="demo4"  src="${path }${obj.productVideo}"/>
       <input type="hidden" id="productVideo" name="productVideo" lay-verify="productVideo" value="${obj.productVideo}"/>
        <p id="demoText"></p>
      </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">起售数量</label>
    <div class="layui-input-block">
     <input type="text" name="salesVolume" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.salesVolume}"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">是否标记为推荐</label>
    <div class="layui-input-block">
     <input type="radio" lay-filter="recommend" name="recommend" value="1" title="是" />
     <input type="radio" lay-filter="recommend" name="recommend" value="0" title="否" checked="" />
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">是否可作为兑奖礼品</label>
    <div class="layui-input-block">
     <input type="radio" lay-filter="present" name="present" value="1" title="是" />
     <input type="radio" lay-filter="present" name="present" value="0" title="否" checked="" />
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">兑奖积分点数</label>
    <div class="layui-input-block">
     <input type="text" name="integration" autocomplete="off" placeholder="请输入" class="layui-input" value="${obj.integration}"/>
    </div>
   </div>


   <div class="layui-form-item">
    <label class="layui-form-label">是否可作为抽奖产品</label>
    <div class="layui-input-block">
     <input type="radio" lay-filter="draw" name="draw" value="1" title="是" />
     <input type="radio" lay-filter="draw" name="draw" value="0" title="否" checked="" />
    </div>
   </div>

	  <input type="hidden" name="productId" value="${obj.productId }">
	  <button class="layui-btn" lay-submit lay-filter="submmitFilter" style="display: none;" id="sunmitbtn"></button>
	</form>   
	<!-- JS -->
	<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
	<script src="${ctx}/static/layui/layui.js" charset="utf-8"></script>
	<script src="${ctx }/static/lib/jquery-form.js"></script>
	<script src="${ctx }/static/zoomify/zoomify.min.js"></script>
	<script src="${ctx }/static/zoomify/zoomify.min.css"></script>
	<script>
	
	  $(function(){
		  if($("#proImgOne").val()!=''){
			  $("#demo2").hide();
			  var array=$("#proImgOne").val().split(',');
				 for(var i in array){
					 if(array[i]){
						 var item=$("<img  width='90px' height='95px' class='layui-upload-img one' src='${path }"+array[i]+"'/>");
						 $("#productImg_one").append(item); 
					 }
				 }
		  }
			if($("#proImgTwo").val()!=''){
				 $("#demo3").hide();
				 var ary=$("#proImgTwo").val().split(',');
				 for(var i in ary){
					 if(ary[i]){
						 var item=$("<img  width='90px' height='95px' class='layui-upload-img two' src='${path }"+ary[i]+"'/>");
						 $("#productImg_two").append(item); 
					 }
				 }
			}
			 $("input[name='integration']").parent().parent().hide();
		});
	
	
	
	
	
	
	
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
		      $("#present").val(data.value);
		  })
		  //监听提交
		  form.on('submit(submmitFilter)', function(data){
			  
			  if(imgOne!=''){
				  $('#productImgOne').val(imgOne);
			  }else{
				  var a=$("#proImgOne").val();
				  $('#productImgOne').val(a);
			  }
			  
			  if(imgTwo!=''){
				  $('#productImgTwo').val(imgTwo);
			  }else{
				  var b=$("#proImgTwo").val();
				  $('#productImgTwo').val(b);
			  }
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
	var imgOne='';
	var imgTwo='';
	layui.use('upload', function(){
		  var $ = layui.jquery
		  ,upload = layui.upload;

		//首页实例
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
		              $('#productImg').val(res.link);
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
		
		//产品图片
		      var uploadInst = upload.render({
			        elem: '#test2' //绑定元素
			        ,url: '${ctx }/upload/uploadFile?name=imgFile' //上传接口
			        ,method:'post'   //提交方式
			        ,field:'imgFile' //设定文件域的字段名
			        ,accept:'images' //只允许上传图片
			        ,acceptMime:'image/*' //打开文本选择框时，只显示图片类型
			        ,size:10240  //设置文件最大可允许上传的大小，单位 KB。
			        ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
			          layer.load(); //上传loading
			          //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
			         $('#demo2').show();
			          $(".one").hide();
			          obj.preview(function(index, file, result){
			            $('#demo2').attr('src',result);
			            $('#demo2').attr('alt',file.name);
			          });
			          }
			        ,done: function(res){//上传完毕回调
			          layer.closeAll('loading');//关闭loading
			          //获得文件的链接等，
			            if(res.state==0){//上传成功
			            	//$('#productImgOne').val($('#productImgOne').val()+","+res.link);
			            imgOne+=","+res.link;
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
		
		
		    //详情图片
		      var uploadInst = upload.render({
			        elem: '#test3' //绑定元素
			        ,url: '${ctx }/upload/uploadFile?name=imgFile' //上传接口
			        ,method:'post'   //提交方式
			        ,field:'imgFile' //设定文件域的字段名
			        ,accept:'images' //只允许上传图片
			        ,acceptMime:'image/*' //打开文本选择框时，只显示图片类型
			        ,size:10240  //设置文件最大可允许上传的大小，单位 KB。
			        ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
			          layer.load(); //上传loading
			          //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
			          $('#demo3').show();
			          $(".two").hide();
			          obj.preview(function(index, file, result){
			            $('#demo3').attr('src',result);
			            $('#demo3').attr('alt',file.name);
			          });
			          }
			        ,done: function(res){//上传完毕回调
			          layer.closeAll('loading');//关闭loading
			          //获得文件的链接等，
			            if(res.state==0){//上传成功
			            	//$('#productImgTwo').val($('#productImgTwo').val()+","+res.link);
			            	 imgTwo+=","+res.link;
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
		    
		    //上传视频
		      var uploadInst = upload.render({
		        elem: '#test4' //绑定元素
		        ,url: '${ctx }/upload/uploadFile?name=videoFile' //上传接口
		        ,method:'post'   //提交方式
		        ,field:'videoFile' //设定文件域的字段名
		        ,accept: 'video' //视频
		        ,size:102400  //设置文件最大可允许上传的大小，单位 KB。
		        ,done: function(res){//上传完毕回调
		          layer.closeAll('loading');//关闭loading
		          //获得文件的链接等，
		            if(res.state==0){//上传成功
		              $('#productVideo').val(res.link);
		            }else if(res.state==-4){
		              layer.msg('请上传100M以内的视频', {
		                icon: 5
		                ,time: 1500
		            });
		            }else if(res.state==-5){
		              layer.msg('请上传视频格式文件', {
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