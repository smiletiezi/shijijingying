package com.py.shijijingying.web;
/*
 * 提成策略
 */
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
import com.py.shijijingying.entity.Percentage;
import com.py.shijijingying.service.PercentageService;
import com.py.shijijingying.service.ProductTypeService;
import com.py.shijijingying.utils.Msg;

@Controller  
@RequestMapping("/percentage")
public class PercentageController extends BaseController{
	@Autowired
	private PercentageService percentageService;
	@Autowired
	private ProductTypeService productTypeService;
	/*
	 * add
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public Msg add(@RequestParam(value="name",required=false)String name,
			@RequestParam(value="salesman",required=false)Double salesman,
	        @RequestParam("partner")Double partner,
	        @RequestParam("remark")String remark){
		Percentage percentage=new Percentage();
		if(name!=null){
			percentage.setName(name);
		}else{
			return Msg.fail().add("msg", "请选择产品类型");
		}
		if(salesman!=null){
			percentage.setSalesman(salesman);
		}else{
			percentage.setSalesman(0.0);
		}
		if(partner!=null){
			percentage.setPartner(partner);
		}else{
			percentage.setPartner(0.0);
		}
		if(remark!=null){
			percentage.setRemark(remark);
		}
		try{
			percentageService.insertSelective(percentage);
			
		}catch(Exception e){
			Msg.fail().add("msg", "处理失败");
		}
		return Msg.success();
		
	}
	
	/*
	 * update
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public Msg update(@RequestParam(value="percentageId",required=false)Integer percentageId,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="salesman",required=false)Double salesman,
	        @RequestParam(value="partner",required=false)Double partner,
	        @RequestParam(value="remark",required=false)String remark){
		Percentage percentage=percentageService.selectByPrimaryKey(percentageId);
		if(name!=null){
			percentage.setName(name);
		}
		if(salesman!=null){
			percentage.setSalesman(salesman);
		}
		if(partner!=null){
			percentage.setPartner(partner);
		}
		if(remark!=null){
			percentage.setRemark(remark);
		}
		try{
			percentageService.updateByPrimaryKeySelective(percentage);
			
		}catch(Exception e){
			Msg.fail().add("msg", "处理失败");
		}
		return Msg.success();
	}
	
	/*
	 * delete
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	public Msg delete(@RequestParam(value="percentageId",required=false)Integer percentageId){
		try{
			percentageService.deleteByPrimaryKey(percentageId);
			
		}catch(Exception e){
			Msg.fail().add("msg", "处理失败");
		}
		return Msg.success();
	}
	
/*
 * list	
 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="pageSize",required=false)Integer pageSize){
		Percentage percentage=new Percentage();
		//排序插件
		PageHelper.orderBy("percentage_id asc");
		//分页插件
		if(pageNum==null){
			pageNum=1;
		}
		if(pageSize==null){
			pageSize=10;
		}
		
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		List<Percentage> percentages=new ArrayList<Percentage>();
		try{
			 percentages=percentageService.selectByExample(percentage);
		
		}catch(Exception e){
			Msg.fail().add("msg", "处理失败");
		}
		return Msg.success(percentages);
	}
	
/*******************************************************后管修改**************************************/
	
	/**
	 * 跳转提成策略列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toList")
	public String toList(HttpServletRequest request,Model model) {
		return "jsp/percentageList";
	}	
	
	/**
	 * 获取提成策略列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询提成策略列表")
	@RequestMapping(value = "toListData")
	@ResponseBody
	public Map<String,Object> toListData(HttpServletRequest request) {
		String name=request.getParameter("name");
		Percentage percentage=new Percentage();
		if(StringUtil.isNotEmpty(name)) {
			percentage.setName(name);
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("percentage_id ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<Percentage> list =percentageService.selectByExample(percentage);
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
		int id = 0;
		try {
			id= Integer.parseInt(request.getParameter("percentageId"));
		} catch (Exception e) {
			id = 0;
		}
		if(id > 0) {
			Percentage percentage = percentageService.selectByPrimaryKey(id);
			model.addAttribute("obj", percentage);
		}
		return "jsp/percentageForm";
	}
	
	/**
	 * 新增提成策略  
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="新增提成策略")
	@RequestMapping(value="insert")
	@ResponseBody
	public Map<String, Object> insert(HttpServletRequest request,@ModelAttribute("obj") Percentage percentage) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		percentageService.insertSelective(percentage);
		resultMap.put("type", "add");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/**
	 * 修改提策略
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="修改提成策略")
	@RequestMapping(value="set")
	@ResponseBody
	public Map<String, Object> set(HttpServletRequest request,@ModelAttribute("obj") Percentage percentage) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		 percentageService.updateByPrimaryKeySelective(percentage);
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
		percentageService.deleteByPrimaryKey(id);
		resultMap.put("type", "delete");
		resultMap.put("code", "success");
		return resultMap;
	}
	
}
