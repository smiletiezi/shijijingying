package com.py.shijijingying.web;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * 年终比例
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.py.shijijingying.annotation.SystemControllerLog;
import com.py.shijijingying.entity.YearEnd;
import com.py.shijijingying.service.YearEndService;
import com.py.shijijingying.utils.Msg;

@Controller
@RequestMapping("/yearEnd")
public class YearEndController extends BaseController{
	@Autowired
	private YearEndService yearEndService;
	/*
	 * 增加年终比例
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public Msg add(@RequestParam(value="month",required=false)String month,
							  @RequestParam(value="ratio",required=false)Double ratio
							 ){
		YearEnd year=new YearEnd();
		year.setMonth(month);
		year.setRatio(ratio);
		try{
			yearEndService.insertSelective(year);
			return Msg.success();
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
		
	}
	
	/*
	 * 修改update
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public Msg update(@RequestParam(value="yearId",required=false)Integer yearId,
			@RequestParam(value="month",required=false)String month,
			  @RequestParam(value="ratio",required=false)Double ratio
			){
		YearEnd year =yearEndService.selectByPrimaryKey(yearId);
		if(month!=null){
		year.setMonth(month);
		}
		if(ratio!=null){
			year.setRatio(ratio);
		}
		try{
			yearEndService.updateByPrimaryKeySelective(year);
			return Msg.success();
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
	}
	
	/*
	 * list
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(){
		YearEnd year=new YearEnd();
		try{
			List<YearEnd>years=yearEndService.selectByExample(year);
			return Msg.success(years);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
		
	}
	
	
/*******************************************************后管修改**************************************/
	
	/**
	 * 跳转列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toList")
	public String toList(HttpServletRequest request,Model model) {
		return "jsp/yearEndList";
	}	
	
	/**
	 * 获取表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询产品列表")
	@RequestMapping(value = "toListData")
	@ResponseBody
	public Map<String,Object> toListData(HttpServletRequest request) {
		YearEnd yearEnd=new YearEnd();
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("year_id ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<YearEnd> list =yearEndService.selectByExample(yearEnd);
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
	
		int	id= Integer.parseInt(request.getParameter("yearId"));
		YearEnd yearEnd = yearEndService.selectByPrimaryKey(id);
		model.addAttribute("obj", yearEnd);
		return "jsp/yearForm";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="修改")
	@RequestMapping(value="set")
	@ResponseBody
	public Map<String, Object> set(HttpServletRequest request,@ModelAttribute("obj") YearEnd yearEnd) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		yearEndService.updateByPrimaryKeySelective(yearEnd);
		resultMap.put("type", "update");
		resultMap.put("code", "success");
		return resultMap;
	}
}
