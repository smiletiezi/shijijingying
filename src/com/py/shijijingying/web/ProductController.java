package com.py.shijijingying.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Maps;
import com.py.shijijingying.annotation.SystemControllerLog;
import com.py.shijijingying.entity.BoxinUser;
import com.py.shijijingying.entity.OrderProduct;
import com.py.shijijingying.entity.Product;
import com.py.shijijingying.entity.ProductDiscount;
import com.py.shijijingying.entity.ProductType;
import com.py.shijijingying.service.BoxinUserService;
import com.py.shijijingying.service.OrderProductService;
import com.py.shijijingying.service.ProductDiscountService;
import com.py.shijijingying.service.ProductService;
import com.py.shijijingying.service.ProductTypeService;
import com.py.shijijingying.utils.CommonUtil;
import com.py.shijijingying.utils.EmptyUtil;
import com.py.shijijingying.utils.Msg;
import com.py.shijijingying.utils.Utils;

@Controller  
@RequestMapping("/product")
public class ProductController extends BaseController{
	@Autowired
	private ProductService productService;
	@Autowired
	private BoxinUserService userService;		
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ProductDiscountService productDiscountService;
	/*
	 * add产品
	 * @param shipPerson
	 * @param shipPhone
	 * @param shipAddress
	 * @param shipType
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public Msg add(@RequestParam(value="productName",required=false)String productName,
            @RequestParam(value="productSalePrice",required=false)Double productSalePrice,
            @RequestParam(value="productPrice",required=false)Double productPrice,
            @RequestParam(value="productSize",required=false)String productSize,
            @RequestParam(value="productSaleType",required=false)String productSaleType,
            @RequestParam(value="productBrand",required=false)String productBrand,
            @RequestParam(value="productSale",required=false)String productSale,
            @RequestParam(value="productRemark",required=false)String productRemark,
            @RequestParam(value="productType",required=false)Integer productType,
            @RequestParam(value="productImg",required=false)List<Object> productImg,
            @RequestParam(value="productBelong",required=false)String productBelong,
            @RequestParam(value="productDiscount",required=false)String productDiscount,
            @RequestParam(value="productVideo",required=false)String productVideo,
            @RequestParam(value="salesVolume",required=false)Integer salesVolume,
            @RequestParam(value="recommend",required=false)String recommend,
            @RequestParam(value="present",required=false)String present,
            @RequestParam(value="integration",required=false)Integer integration,
            @RequestParam(value="draw",required=false)String draw) throws IOException{
		Product product=new Product();
		if(productBelong!=null&&productBelong!=""){
			product.setProductBelong(productBelong);
		}
		if(productBrand!=null&&productBrand!=""){
			product.setProductBrand(productBrand);	
				}
		if(productName!=null&&productName!=""){
			product.setProductName(productName);
		}
		if(productPrice!=null){
			product.setProductPrice(productPrice);
		}
		if(productRemark!=null&&productRemark!=""){
			product.setProductRemark(productRemark);
		}
		if(productSale!=null){
			product.setProductSale(productSale);
		}
		if(productSalePrice!=null){
			product.setProductSalePrice(productSalePrice);
		}
		if(productSaleType!=null){
			product.setProductSaleType(productSaleType);
		}
		if(productType!=null){
			product.setProductType(productType);
		}
		if(productSize!=null){
			product.setProductSize(productSize);
		}
		if(productDiscount!=null){
			product.setProductDiscount(productDiscount);
		}
		if(salesVolume!=null){
			product.setSalesVolume(salesVolume);
		}
		if(recommend!=null){
			product.setRecommend(recommend);
		}
		if(present!=null){
			product.setPresent(present);
				}
		if(integration!=null){
			product.setIntegration(integration);
		}
		if(draw!=null){
			product.setDraw(draw);
		}
		List<Product> products=productService.selectByExample(product);
		if(null == products || products.size() ==0){
			if (productName != null) {
				product.setProductName(productName);
			}
			if (productSalePrice != null) {
				product.setProductSalePrice(productSalePrice);
			}
			if (productPrice != null) {
				product.setProductPrice(productPrice);
			}
			if (productSize != null) {
				product.setProductSize(productSize);
		
			}
			if (productSaleType != null) {
				product.setProductSaleType(productSaleType);;
			
			}
			if (productBrand != null) {
				product.setProductBrand(productBrand);
			}
			if (productSale != null) {
				product.setProductSale(productSale);
			}else{
				product.setProductSale("0");
			}
			if (productRemark != null) {
				product.setProductRemark(productRemark);
		
			}
			if (productType != null) {
				product.setProductType(productType);
			}
			if (productImg.size()>0l) {
				System.out.println(productImg.get(1).toString());
				//String imgs=CommonUtil.imgStr(productImg);
				//StringBuffer buf=new StringBuffer();
			//	for (int i = 0; i < imgs.size(); i++) {
				//	buf.append(imgs.get(i)+",");
				//}
				//product.setProductImg(imgs);
			}
			if (productBelong != null) {
				if(productBelong.equals("总公司")){
					product.setProductBelong(productBelong);
					product.setBelong("1");
				}else{
					product.setProductBelong(productBelong);
					product.setBelong("0");
				}
			}
			if (productDiscount != null) {
				product.setProductDiscount(productDiscount);
			}
			if (StringUtil.isNotEmpty(productVideo)) {
				product.setProductVideo(CommonUtil.videoStr(productVideo));
			}
			if (salesVolume != null) {
				product.setSalesVolume(salesVolume);
			}else{
				product.setSalesVolume(1);
			}
			
			if (recommend != null) {
				product.setRecommend(recommend);
			}else{
				product.setRecommend("0");
			}
			if (present.equals("0")==false) {
				product.setPresent(present);
				product.setIntegration(integration);
			}else{
				product.setPresent("0");
				product.setIntegration(0);
			}
			if(draw!=null){
				product.setDraw(draw);
			}else{
				product.setDraw("0");
			}
			productService.insertSelective(product);
			Msg msg = Msg.success();
	        return msg;
		}else{
			return Msg.fail().add("mag", "该产品已存在");
		}
		
	}
	
	/*
	 * 
	 */
	/*
	 * delete产品
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	public Msg delete(@RequestParam(value="productId",required=false)Integer productId
            ){
		Product product=productService.selectByPrimaryKey(productId);
		OrderProduct ord=new OrderProduct();
		ord.setProductName(product.getProductName());
		ord.setType("1");//已支付过的产品
		List<OrderProduct> ords=orderProductService.selectByExample(ord);
			if(EmptyUtil.isEmpty(product)){
				return Msg.fail().add("msg", "请选择要删除的产品");
			}else{
			try{
				if(ords.size()>0){
					return Msg.fail().add("msg", "该产品存在于支付过的用户订单 暂不能删除");
				}else{
					productService.deleteByPrimaryKey(productId);
				}
				
			}catch(Exception e){
					return Msg.fail().add("msg", "删除失败");
				}
			}
		return Msg.success();
	}
	
	/*
	 * update产品
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public Msg update(@RequestParam(value="productId",required=false)Integer productId,
			@RequestParam(value="productName",required=false)String productName,
			@RequestParam(value="productSalePrice",required=false)Double productSalePrice,
            @RequestParam(value="productPrice",required=false)Double productPrice,
            @RequestParam(value="productSize",required=false)String productSize,
            @RequestParam(value="productSaleType",required=false)String productSaleType,
            @RequestParam(value="productBrand",required=false)String productBrand,
            @RequestParam(value="productSale",required=false)String productSale,
            @RequestParam(value="productRemark",required=false)String productRemark,
            @RequestParam(value="productType",required=false)Integer productType,
            @RequestParam(value="productImg",required=false)MultipartFile[] productImg,
            @RequestParam(value="productBelong",required=false)String productBelong,
            @RequestParam(value="productDiscount",required=false)String productDiscount,
            @RequestParam(value="productVideo",required=false)String productVideo,
            @RequestParam(value="salesVolume",required=false)Integer salesVolume,
            @RequestParam(value="recommend",required=false)String recommend,
            @RequestParam(value="present",required=false)String present,
            @RequestParam(value="integration",required=false)Integer integration,
            @RequestParam(value="draw",required=false)String draw
            ) throws IOException{
		Product product=productService.selectByPrimaryKey(productId);
		
			if(EmptyUtil.isEmpty(product)){
				return Msg.fail().add("msg", "请选择要修改产品");
			}else{
				if (productName != null) {
					product.setProductName(productName);
				}
				if (productSalePrice != null) {
					product.setProductSalePrice(productSalePrice);
				}
				if (productPrice != null) {
					product.setProductPrice(productPrice);
				}
				if (productSize != null) {
					product.setProductSize(productSize);
				}
				if (productSaleType != null) {
					product.setProductSaleType(productSaleType);
				}
				if (productBrand != null) {
					product.setProductBrand(productBrand);
				}
				if (productSale != null) {
					product.setProductSale(productSale);
				}else{
					product.setProductSale("0");
				}
				if (productRemark != null) {
					product.setProductRemark(productRemark);
				}
				if (productType != null) {
					product.setProductType(productType);
				}
				if (productImg.length>0) {
					List<String> imgs=CommonUtil.saveFiles(productImg);
					StringBuffer buf=new StringBuffer();
					for (int i = 0; i < imgs.size(); i++) {
						buf.append(imgs.get(i)+",");
					}
					product.setProductImg(buf.toString());
				}
				if (productBelong != null) {
					if(productBelong.equals("总公司")){
						product.setProductBelong(productBelong);
						product.setBelong("1");
					}else{
						product.setProductBelong(productBelong);
						product.setBelong("0");
					}
				}
				if (productDiscount != null) {
					product.setProductDiscount(productDiscount);
				}
				if (productVideo != null) {
					product.setProductImg(CommonUtil.videoStr(productVideo));
				}
				if (salesVolume != null) {
					product.setSalesVolume(salesVolume);
				}
				if (recommend != null) {
					product.setRecommend(recommend);
				}else{
					product.setRecommend("0");
				}
				if (present != null) {
					product.setPresent(present);
					product.setIntegration(integration);
				}else{
					product.setPresent("0");
					product.setIntegration(0);
				}
				if(draw!=null){
					product.setDraw(draw);
				}else{
					product.setDraw("0");
				}
				try{
					productService.updateByPrimaryKey(product);
			}catch(Exception e){
					return Msg.fail().add("msg", "修改失败");
				}
			}
		return Msg.success();
	}
	
	/*
	 * 产品list
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(@RequestParam(value="productId",required=false)Integer productId,
			@RequestParam(value="productSize",required=false)String productSize,
			@RequestParam(value="productName",required=false)String productName,
			@RequestParam(value="productSaleType",required=false)String productSaleType,
			@RequestParam(value="productSale",required=false)String productSale,
			@RequestParam(value="productBrand",required=false)String productBrand,
			@RequestParam(value="productType",required=false)Integer productType,
			@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="pageSize",required=false)Integer pageSize
			){
		List<Product> products=new ArrayList<Product>();
		
		if(productId!=null){
			Product pro=productService.selectByPrimaryKey(productId);
			return Msg.success(pro);
		} else {
			Product product=new Product();
			if(productName!=null && productName!="" ){
				product.setProductName(productName);
			}
			if(productBrand!=null && productBrand !="" ){
				product.setProductBrand(productBrand);
			}
			if(productSale!=null && productSale!=""){
				product.setProductSale(productSale);
			}
			if(productSaleType!=null && productSaleType!=""){
				product.setProductSaleType(productSaleType);
			}
			if(productSize!=null){
				product.setProductSize(productSize);
			}
			if(productType!=null){
				product.setProductType(productType);
			}
			//try{
				//排序插件
			//	PageHelper.orderBy("product_id asc");
				//分页插件
			//	if(pageNum==null){
			//		pageNum=1;
			//	}
			//	if(pageSize==null){
			//		pageSize=10;
			//	}
				
			//	Page<?> page = PageHelper.startPage(pageNum, pageSize);
				products=productService.selectByExample(product);
				return Msg.success(products);
		}
		
		//}catch(Exception e){
		//	return Msg.fail().add("msg", "处理失败");
		//}
			
	}
	/*
	 * 
	 * 查询可兑换产品列表
	 */
	@ResponseBody
	@RequestMapping(value="/presentList")
	public Msg presentList(){
		Product product=new Product();
		product.setPresent("1");
		List<Product> products =productService.selectByExample(product);
		return Msg.success(products);
	}
	
	/*
	 * 
	 * 获取推荐产品列表
	 */
	@ResponseBody
	@RequestMapping(value="/recommendList")
	public Msg recommendList(){
		Product product=new Product();
		product.setRecommend("1");
		List<Product> products =productService.selectByExample(product);
		return Msg.success(products);
	}
	
	/*
	 * 获取用户可用积分
	 */
	@ResponseBody
	@RequestMapping(value="/userPersonal")
	public Msg presentList(@RequestParam(value="userId",required=false)Integer userId){
		BoxinUser user=userService.getUserById(userId);
		int personal=user.getPersonal();
		if(user.getPersonal()==null){
			return Msg.success(0);
		}else{
			return Msg.success(personal);
		}
		
	}
	
/*
 * 图片轮播图	
 */
	@ResponseBody
	@RequestMapping(value="/img")	
	public Msg img(){
		List<Product> products =productService.selectByLimt();
		return Msg.success(products);
	}
	
	/*********************************************后管修改**********************************/
	/**
	 * 跳转产品列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toList")
	public String toList(HttpServletRequest request,Model model) {
		return "jsp/productList";
	}	
	
	/**
	 * 获取产品列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询产品列表")
	@RequestMapping(value = "toListData")
	@ResponseBody
	public Map<String,Object> toListData(HttpServletRequest request) {
		String productName=request.getParameter("productName");
		Product product=new Product();
		if(StringUtil.isNotEmpty(productName)) {
			product.setProductName(productName);
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("product_id ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<Product> list =productService.selectByExample(product);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}

	/**
	 * 页面跳转 新增或修改跳转form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toForm")
	public String getUserList(HttpServletRequest request,Model model) {
		String path=Utils.getProperties("attachment_path");
		int id = 0;
		try {
			id= Integer.parseInt(request.getParameter("productId"));
		} catch (Exception e) {
			id = 0;
		}
		List<ProductType> type=productTypeService.selectTwoAll();
		if(id > 0) {
			Product product = productService.selectByPrimaryKey(id);
			model.addAttribute("obj", product);
			model.addAttribute("path", path);
		}
		model.addAttribute("type", type);
		return "jsp/productForm";
	}
	
	/**
	 * 新增产品
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="新增产品")
	@RequestMapping(value="insert")
	@ResponseBody
	public Map<String, Object> insert(HttpServletRequest request,@ModelAttribute("obj") Product product) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		if(StringUtil.isNotEmpty(product.getProductBelong())) {
			product.setBelong(product.getProductBelong());
		}
		if(product.getProductDiscount()!=null&&product.getProductDiscount()!="") {
			ProductDiscount discount=new ProductDiscount();
			discount.setDiscountType(product.getProductDiscount());
			List<ProductDiscount> pd=productDiscountService.selectByExample(discount);
			String str="";
			for(int i=0;i<pd.size();i++) {
				str+=pd.get(i).getDiscountName()+",";
			}
			product.setDiscountName(str);
		}
		productService.insertSelective(product);
		
		resultMap.put("type", "add");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/**
	 * 修改产品  
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="修改产品")
	@RequestMapping(value="set")
	@ResponseBody
	public Map<String, Object> set(HttpServletRequest request,@ModelAttribute("obj") Product product) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		if(product.getProductDiscount()!=null&&product.getProductDiscount()!="") {
			ProductDiscount discount=new ProductDiscount();
			discount.setDiscountType(product.getProductDiscount());
			List<ProductDiscount> pd=productDiscountService.selectByExample(discount);
			String str="";
			for(int i=0;i<pd.size();i++) {
				str+=pd.get(i).getDiscountName()+",";
			}
			product.setDiscountName(str);
		}
		productService.updateByPrimaryKeySelective(product);
		resultMap.put("type", "update");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/**
	 * 删除产品  
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="删除产品")
	@RequestMapping(value="redelete")
	@ResponseBody
	public Map<String, Object> redelete(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		productService.deleteByPrimaryKey(id);
		resultMap.put("type", "delete");
		resultMap.put("code", "success");
		return resultMap;
	}
	/**
	 * 根据ID查询产品详情
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@SystemControllerLog(description="查询产品详情")
	@RequestMapping("toDetails")
	public String toDetails(HttpServletRequest request, Model model) {
		String path=Utils.getProperties("attachment_path");
		Integer id=Integer.parseInt(request.getParameter("productId"));
		Product obj =productService.selectByPrimaryKey(id);
		List<ProductType> type=productTypeService.selectTwoAll();
		model.addAttribute("type", type);
		model.addAttribute("obj",obj);
		model.addAttribute("path",path);
		return "jsp/productDetails";
	}
	
	
}

	


