package com.py.shijijingying.web;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.py.shijijingying.annotation.SystemControllerLog;
import com.py.shijijingying.entity.BxArea;
import com.py.shijijingying.service.BxAreaService;

@Controller  
@RequestMapping("/bxarea") 
public class BxAreaController extends BaseController{
	@Autowired
	private BxAreaService bxAreaService;
	
	/**
	 * 跳转邮资管理列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toList")
	public String toList(HttpServletRequest request,Model model) {
		return "jsp/boxinAreaList";
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
		BxArea area=new BxArea();
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("id ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<BxArea> list =bxAreaService.selectByExample(area);
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
			id= Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			id = 0;
		}
		if(id > 0) {
			BxArea area = bxAreaService.selectByPrimaryKey(id);
			model.addAttribute("obj", area);
		}
		return "jsp/boxinAreaForm";
	}
	
	/**
	 * 新增邮资
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="新增邮资")
	@RequestMapping(value="insert")
	@ResponseBody
	public Map<String, Object> insert(HttpServletRequest request,@ModelAttribute("obj") BxArea area) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		bxAreaService.insertSelective(area);
		resultMap.put("type", "add");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/**
	 * 修改代金券  
	 * @param request
	 * @param commodityClassification
	 * @return
	 * @throws ParseException 
	 */
	@SystemControllerLog(description="修改代金券")
	@RequestMapping(value="set")
	@ResponseBody
	public Map<String, Object> set(HttpServletRequest request,@ModelAttribute("obj") BxArea area) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		bxAreaService.updateByPrimaryKeySelective(area);
		resultMap.put("type", "update");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/**
	 * 删除邮资  
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="删除邮资")
	@RequestMapping(value="redelete")
	@ResponseBody
	public Map<String, Object> redelete(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		bxAreaService.deleteByPrimaryKey(id);
		resultMap.put("type", "delete");
		resultMap.put("code", "success");
		return resultMap;
	}

	
}
