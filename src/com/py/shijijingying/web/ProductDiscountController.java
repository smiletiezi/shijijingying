package com.py.shijijingying.web;

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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Maps;
import com.py.shijijingying.annotation.SystemControllerLog;
import com.py.shijijingying.entity.Product;
import com.py.shijijingying.entity.ProductDiscount;
import com.py.shijijingying.service.ProductDiscountService;
import com.py.shijijingying.service.ProductService;
import com.py.shijijingying.utils.EmptyUtil;
import com.py.shijijingying.utils.Msg;

@Controller  
@RequestMapping("/productDiscount")
public class ProductDiscountController extends BaseController{
	@Autowired
	private ProductDiscountService productDiscountService;
	@Autowired
	private ProductService productService;
	
	/*
	 * add产品折扣
	 * @param discountName
	 * @param discountRemark
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public Msg add(@RequestParam(value="discountName",required=false)String discountName,
			@RequestParam(value="discountSum",required=false)Integer discountSum,
            @RequestParam(value="discountRemark",required=false)Double discountRemark,
            @RequestParam(value="discountType",required=false)String discountType){
		ProductDiscount discount=new ProductDiscount();
		if(discountName!=null){
			discount.setDiscountName(discountName);
		}
		if(discountSum!=null){
			discount.setDiscountSum(discountSum);
		}
		if(discountRemark!=null){
			discount.setDiscountRemark(discountRemark);
		}
		if(discountType!=null){
			discount.setDiscountType(discountType);
		}
		
			List<ProductDiscount> discounts=productDiscountService.selectByExample(discount);
		if(null == discounts || discounts.size() ==0){
			if (discountName != null) {
				discount.setDiscountName(discountName);
			}else{
				return Msg.fail().add("msg", "折扣名称不能为空");
			}
			if(discountSum!=null){
				discount.setDiscountSum(discountSum);
			}else{
				return Msg.fail().add("msg", "折扣件数不能为空");
			}
			if (discountRemark != null) {
				discount.setDiscountRemark(discountRemark);
			}else{
				discount.setDiscountRemark(1.0);
			}
			if(StringUtil.isEmpty(discountType)==false){
				discount.setDiscountType(discountType);
			}else{
				return Msg.fail().add("msg", "产品类型不能为空");
			}
			productDiscountService.insertSelective(discount);
			Msg msg = Msg.success();
	        return msg;
		}else{
			return Msg.fail().add("msg", "该类型折扣已存在");
		}
	}
	
	/*
	 * delete产品折扣
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	
	public Msg delete(@RequestParam(value="discountId",required=false)Integer discountId
            ){
		ProductDiscount discount=productDiscountService.selectByPrimaryKey(discountId);
		Product product =new Product();
		product.setProductDiscount(discount.getDiscountType());
		List<Product> products=productService.selectByExample(product);
		if(products==null&&products.size()==0){
			if(EmptyUtil.isEmpty(discount)){
				return Msg.fail().add("msg", "请选择要删除的折扣类型");
			}else{
			try{
				productDiscountService.deleteByPrimaryKey(discountId);
				return Msg.success();
			}catch(Exception e){
					return Msg.fail().add("msg", "删除失败");
				}
			}
		
	}else{
		return Msg.fail().add("msg", "该折扣使用中，暂不能删除");
	}
	}
	
	/*
	 * update产品折扣
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public Msg update(@RequestParam(value="discountId",required=false)Integer discountId,
			@RequestParam(value="discountName",required=false)String discountName,
			@RequestParam(value="discountSum",required=false)Integer discountSum,
			@RequestParam(value="discountRemark",required=false)Double discountRemark,
			@RequestParam(value="discountType",required=false)String discountType
            ){
		ProductDiscount discount=productDiscountService.selectByPrimaryKey(discountId);
		
			if(EmptyUtil.isEmpty(discount)){
				return Msg.fail().add("msg", "请选择要修改的折扣类型");
			}else{
			
				if(discountName !=null){
					discount.setDiscountName(discountName);
				}
				if(discountSum!=null){
					discount.setDiscountSum(discountSum);
				}
				if (discountRemark != null) {
					discount.setDiscountRemark(discountRemark);
				}
				if(StringUtil.isEmpty(discountType)==false){
					discount.setDiscountType(discountType);
				}
				try{
					productDiscountService.updateByPrimaryKey(discount);
			}catch(Exception e){
					return Msg.fail().add("msg", "修改失败");
				}
			}
		return Msg.success();
	}
	
	/*
	 * 产品折扣list
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="pageSize",required=false)Integer pageSize){
		//排序插件
				PageHelper.orderBy("discount_id asc");
				//分页插件
				if(pageNum==null){
					pageNum=1;
				}
				if(pageSize==null){
					pageSize=10;
				}
				
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		List<ProductDiscount> discounts=new ArrayList<ProductDiscount>();
		ProductDiscount discount=new ProductDiscount();
		try{
			discounts=productDiscountService.selectByExample(discount);
			return Msg.success(discounts);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
			
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
		return "jsp/productDiscountList";
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
		String discountType=request.getParameter("discountType");
		ProductDiscount discount=new ProductDiscount();
		if(StringUtil.isNotEmpty(discountType)) {
			discount.setDiscountType(discountType);
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("discount_id ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<ProductDiscount> list =productDiscountService.selectByExample(discount);
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
	public String getDiscountList(HttpServletRequest request,Model model) {
		int id = 0;
		try {
			id= Integer.parseInt(request.getParameter("discountId"));
		} catch (Exception e) {
			id = 0;
		}
		if(id > 0) {
			ProductDiscount discount = productDiscountService.selectByPrimaryKey(id);
			model.addAttribute("obj", discount);
		}
		return "jsp/productDiscountForm";
	}
	
	/**
	 * 新增折扣  
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="新增折扣")
	@RequestMapping(value="insert")
	@ResponseBody
	public Map<String, Object> insert(HttpServletRequest request,@ModelAttribute("obj") ProductDiscount discount) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		productDiscountService.insertSelective(discount);
		resultMap.put("type", "add");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/**
	 * 修改折扣  
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="修改折扣")
	@RequestMapping(value="set")
	@ResponseBody
	public Map<String, Object> set(HttpServletRequest request,@ModelAttribute("obj") ProductDiscount discount) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		productDiscountService.updateByPrimaryKeySelective(discount);
		resultMap.put("type", "update");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/**
	 * 删除折扣
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="删除折扣")
	@RequestMapping(value="redelete")
	@ResponseBody
	public Map<String, Object> redelete(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		productDiscountService.deleteByPrimaryKey(id);
		resultMap.put("type", "delete");
		resultMap.put("code", "success");
		return resultMap;
	}
	
}
