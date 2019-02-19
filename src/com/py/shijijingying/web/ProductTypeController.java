package com.py.shijijingying.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.py.shijijingying.entity.ProductType;
import com.py.shijijingying.service.ProductService;
import com.py.shijijingying.service.ProductTypeService;
import com.py.shijijingying.utils.EmptyUtil;
import com.py.shijijingying.utils.Msg;
import com.py.shijijingying.utils.Utils;

@Controller  
@RequestMapping("/productType")
public class ProductTypeController extends BaseController{
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private ProductService productService;
	
	/*
	 * add产品类型
	 * @param name
	 * @param typeCode
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public Msg add(@RequestParam(value="name",required=false)String name,
            @RequestParam(value="typeCode",required=false)String typeCode){
			ProductType type=new ProductType();
			type.setName(name);
			type.setTypeCode(typeCode);
			List<ProductType> types=productTypeService.selectByExample(type);
		if(null == types || types.size() ==0){
			if (name != null) {
				type.setName(name);
			}else{
				return Msg.fail().add("msg", "产品类型名称不能为空");
			}
			if (typeCode != null) {
				type.setTypeCode(typeCode);
			}else{
				type.setTypeCode("0");
			}
			
			productTypeService.insertSelective(type);
			Msg msg = Msg.success();
	        return msg;
		}else{
			return Msg.fail().add("msg", "该产品类型已存在");
		}
	}
	
	/*
	 * delete产品类型
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	public Msg delete(@RequestParam(value="productTypeId",required=false)Integer productTypeId
            ){
		Product product =new Product();
		product.setProductType(productTypeId);
		List<Product> products=productService.selectByExample(product);
		if(products.size()==0){
			 ProductType type=productTypeService.selectByPrimaryKey(productTypeId);
				
				if(EmptyUtil.isEmpty(type)){
					return Msg.fail().add("msg", "请选择要删除的产品类型");
				}else{
				try{
					productTypeService.deleteByPrimaryKey(productTypeId);
					return Msg.success();
				}catch(Exception e){
						return Msg.fail().add("msg", "删除失败");
					}
				}
		}else{
			return Msg.fail().add("msg", "该产品类型下有产品，不能删除");
		}
		
		
	}
	
	/*
	 * update产品类型
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public Msg update(@RequestParam(value="productTypeId",required=false)Integer productTypeId,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="typeCode",required=false)String typeCode
            ){
		ProductType type=productTypeService.selectByPrimaryKey(productTypeId);
		
			if(EmptyUtil.isEmpty(type)){
				return Msg.fail().add("msg", "请选择要修改的产品类型");
			}else{
			
				if(name !=null){
					type.setName(name);
				}
				if (typeCode != null) {
					type.setTypeCode(typeCode);
				}
//				else{
//					type.setTypeCode("0");
//				}
				try{
					productTypeService.updateByPrimaryKey(type);
			}catch(Exception e){
					return Msg.fail().add("msg", "修改失败");
				}
			}
		return Msg.success();
	}
	
	/*
	 * 产品类型一级list
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/listone")
	public Msg listone(){
		List<ProductType> types=new ArrayList<ProductType>();
		ProductType type=new ProductType();
		type.setTypeCode("0");
		try{
			types=productTypeService.selectByExample(type);
			return Msg.success(types);	
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
	}
	
	/*
	 * 获得二级类型
	 */
	@ResponseBody
	@RequestMapping(value="/listtwo")
	public Msg listtwo(@RequestParam(value="productTypeId",required=false)Integer productTypeId){
		List<ProductType> types=new ArrayList<ProductType>();
		ProductType type=new ProductType();
		type.setTypeCode(productTypeId.toString());
		try{
			types=productTypeService.selectByExample(type);
			return Msg.success(types);	
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
	}
	
	/*
	 * 后台获取所有二级类型
	 */
	@ResponseBody
	@RequestMapping(value="/twoListAll")
	public Msg twoListAll(){
		try {
			List<ProductType> types=productTypeService.selectTwoAll();
			return Msg.success(types);
		} catch (Exception e) {
			return Msg.fail().add("msg", "处理失败");
		}
		
	}
	
	
	/*******************************************************后管修改**************************************/
	
	/**
	 * 跳转产品类型列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toList")
	public String toList(HttpServletRequest request,Model model) {
		return "jsp/productTypeList";
	}	
	
	/**
	 * 获取产品一级类型列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询产品列表")
	@RequestMapping(value = "toListData")
	@ResponseBody
	public Map<String,Object> toListData(HttpServletRequest request) {
		String name=request.getParameter("name");
		ProductType type=new ProductType();
		if(StringUtil.isNotEmpty(name)) {
			type.setName(name);
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("product_type_id ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<ProductType> list =productTypeService.selectByExample(type);
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
			id= Integer.parseInt(request.getParameter("productTypeId"));
		} catch (Exception e) {
			id = 0;
		}
		ProductType pro=new ProductType();
		List<ProductType> role=productTypeService.selectByExample(pro);
		if(id > 0) {
			ProductType type = productTypeService.selectByPrimaryKey(id);
			model.addAttribute("obj", type);
			model.addAttribute("path", path);
		}
		model.addAttribute("role", role);
		return "jsp/productTypeForm";
	}
	
	/**
	 * 新增产品类型  
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="新增产品类型")
	@RequestMapping(value="insert")
	@ResponseBody
	public Map<String, Object> insert(HttpServletRequest request,@ModelAttribute("obj") ProductType productType) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		productTypeService.insertSelective(productType);
		resultMap.put("type", "add");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/**
	 * 修改产品类型
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="修改产品")
	@RequestMapping(value="set")
	@ResponseBody
	public Map<String, Object> set(HttpServletRequest request,@ModelAttribute("obj") ProductType productType) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		productTypeService.updateByPrimaryKeySelective(productType);
		resultMap.put("type", "update");
		resultMap.put("code", "success");
		return resultMap;
	}
	/**
	 * 删除产品类型  
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="删除产品类型")
	@RequestMapping(value="redelete")
	@ResponseBody
	public Map<String, Object> redelete(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		productTypeService.deleteByPrimaryKey(id);
		resultMap.put("type", "delete");
		resultMap.put("code", "success");
		return resultMap;
	}
	
}

