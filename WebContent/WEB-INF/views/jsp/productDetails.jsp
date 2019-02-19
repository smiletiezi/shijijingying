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
  <title>产品详情</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="${ctx}/static/css/admin-reset.css" media="all">
</head>
<body class="contPadding">                   
	<form class="layui-form" id="form">
	<input type="hidden" id="productImgOne" value="${obj.productImgOne }">
	<input type="hidden" id="productImgTwo" value="${obj.productImgTwo }">
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品名称：</label>
	    <div class="layui-input-block" id="productName">
	      ${obj.productName}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品销售价格：</label>
	    <div class="layui-input-block" id="productSalePrice">
	      ${obj.productSalePrice}
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品原价：</label>
	    <div class="layui-input-block" id="productPrice">
	      ${obj.productPrice}
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品规格：</label>
	    <div class="layui-input-block" id="productSize">
	      ${obj.productSize}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品营销方式：</label>
	    <div class="layui-input-block" id="productSaleType">
	      <c:if test="${obj.productSaleType == '0'}">经销</c:if>	
	      <c:if test="${obj.productSaleType == '1'}">自营</c:if>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品品牌：</label>
	    <div class="layui-input-block" id="productBrand">
	      ${obj.productBrand}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品是否促销：</label>
	    <div class="layui-input-block" id="productSale">
	     <c:if test="${obj.productSale == '0'}">否</c:if>	
	      <c:if test="${obj.productSale == '1'}">是</c:if>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品描述：</label>
	    <div class="layui-input-block" id="productRemark">
	      ${obj.productRemark}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品类型：</label>
	    <div class="layui-input-block" id="productType">
	    <c:forEach items="${type}" var="type">
	    <c:if test="${type.productTypeId==obj.productType }">
	    ${type.name }
	    </c:if>
	    </c:forEach>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">首页图片：</label>
	    <div class="layui-upload-list">
         <img  width="90px" height="95px" class="layui-upload-img" id="demo1" src="${path }${obj.productImg}">
      </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品图片：</label>
	    <div class="layui-upload-list"  id="productImg_one">
      </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">详情图片：</label>
	    <div class="layui-upload-list" id="productImg_two">
       
      </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品视频：</label>
	    <div class="layui-input-block" id="usernumber">
	      <video  width="290px"  controls autoplay height="220px"  id="demo4"  src="${path }${obj.productVideo}"/>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品归属：</label>
	    <div class="layui-input-block" id="productBelong">
	      <c:if test="${obj.productBelong == '0'}">子公司</c:if>	
	      <c:if test="${obj.productBelong == '1'}">总公司</c:if>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品折扣类型：</label>
	    <div class="layui-input-block" id="productDiscount">
	      ${obj.productDiscount}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品提成类型：</label>
	    <div class="layui-input-block" id="percentage">
	      ${obj.percentage}
	    </div>
	  </div>
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品起售数量：</label>
	    <div class="layui-input-block" id="salesVolume">
	      ${obj.salesVolume}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">是否标记为推荐：</label>
	    <div class="layui-input-block" id="recommend">
	      <c:if test="${obj.recommend == '0'}">否</c:if>	
	      <c:if test="${obj.recommend == '1'}">是</c:if>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">是否可积分兑换：</label>
	    <div class="layui-input-block" id="present">
	      <c:if test="${obj.present == '0'}">否</c:if>	
	      <c:if test="${obj.present == '1'}">是</c:if>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">积分兑换点数：</label>
	    <div class="layui-input-block" id="integration">
	      ${obj.integration}
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">产品是否作为抽奖产品：</label>
	    <div class="layui-input-block" id="draw">
	      <c:if test="${obj.draw == '0'}">否</c:if>	
	      <c:if test="${obj.draw == '1'}">是</c:if>
	    </div>
	  </div>
	</form>
	<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
	<script src="${ctx}/static/layui/layui.js" charset="utf-8"></script>
	<script src="${ctx }/static/lib/jquery-form.js"></script>
	<script src="${ctx }/static/zoomify/zoomify.min.js"></script>
	<script src="${ctx }/static/zoomify/zoomify.min.css"></script>
	<script>
	   $(function(){
		 var array=$("#productImgOne").val().split(',');
		 for(var i in array){
			 if(array[i]){
				 var item=$("<img  width='90px' height='95px' class='layui-upload-img' src='${path }"+array[i]+"'/>");
				 $("#productImg_one").append(item); 
			 }
		 }
		 
		 var ary=$("#productImgTwo").val().split(',');
		 for(var i in ary){
			 if(ary[i]){
				 var item=$("<img  width='90px' height='95px' class='layui-upload-img' src='${path }"+ary[i]+"'/>");
				 $("#productImg_two").append(item); 
			 }
		 }
	});
	</script>
	
	
	
</body>
</html>