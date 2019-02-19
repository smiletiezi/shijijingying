package com.py.shijijingying.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.py.shijijingying.entity.SysLog;
import com.py.shijijingying.service.SysLogService;
import com.py.shijijingying.utils.Utils;
/**
 * 操作日志
 * @author Mr Liu hao
 *
 * 2018年6月25日
 */
@Controller
@RequestMapping("sysLog")
public class SysLogController  extends BaseController{
	@Autowired
	SysLogService sysLogService;
	/**
	 * 跳转列表 (可带条件查询)
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping("skipSysLogList")
	public  String SkipSysLogList(HttpServletRequest req,Model model) {

		return "jsp/Log";
	}
	/**
	 * 表单
	 * @param req
	 * @param model
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("sysLogListdata")
	public Map<String,Object> sysLogListdata(HttpServletRequest req,Model model) {
		//向页面返回resultMap
		Map<String,Object> resultMap = Maps.newHashMap();
		//向sql传入
		Map<String,Object> searchMap = Maps.newHashMap();
		String condition=req.getParameter("condition");
		//判断是否有条件传入
		if(Utils.isNotNull(condition)) {
			searchMap.put("condition", condition);
		}
		//分页插件
		LayerPage(req);
		Page<SysLog> page = PageHelper.startPage(pageNum, pageSize);
		//查询所有日志
		List<SysLog> logList= sysLogService.conditionSelect(searchMap);
		resultMap.put("data", logList);
		resultMap.put("count", page.getTotal());
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		return resultMap;
	}
	
	
}
