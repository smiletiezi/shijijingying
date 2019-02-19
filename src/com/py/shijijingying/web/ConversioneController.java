package com.py.shijijingying.web;
/*
 * 消费金额与积分兑率，与抽奖券兑率
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
import com.google.common.collect.Maps;
import com.py.shijijingying.annotation.SystemControllerLog;
import com.py.shijijingying.entity.Conversione;
import com.py.shijijingying.service.ConversioneService;
import com.py.shijijingying.utils.Msg;



@Controller  
@RequestMapping("/conversion")
public class ConversioneController extends BaseController{
	@Autowired
	private ConversioneService conversionService;
	/*
	 * add
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public Msg add(@RequestParam(value="voucher",required=false)Double voucher,
	        @RequestParam(value="draw",required=false)Double draw,
	        @RequestParam(value="probability",required=false)Double probability,
	        @RequestParam(value="remark",required=false)String remark){
		Conversione conversion=new Conversione();
		if(voucher!=null){
			conversion.setVoucher(voucher);
		}else{
			return Msg.fail().add("msg", "请输入积分兑率");
		}
		if(draw!=null){
			conversion.setDraw(draw);
		}else{
			return Msg.fail().add("msg", "请输入抽奖券兑率");
		}
		if(probability!=null){
			conversion.setProbability(probability);
		}else{
			return Msg.fail().add("msg", "请输入中奖率");
		}
		if(remark!=null){
			conversion.setRemark(remark);
		}
		try{
			conversionService.insertSelective(conversion);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
		return Msg.success();
	}
	
	/*
	 * list
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(){
		Conversione conversion=new Conversione();
		List<Conversione> conversions=new ArrayList<Conversione>();
		try{
			conversions=conversionService.selectByExample(conversion);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
		return Msg.success(conversions);
	}
	
	/*
	 * delete
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	public Msg delete(@RequestParam(value="conversionId",required=false)Integer conversionId){
		Conversione conversion=conversionService.selectByPrimaryKey(conversionId);
		if(conversion==null){
			return Msg.fail().add("msg", "请选择您要删除的数据");
		}else{
			try{
				conversionService.deleteByPrimaryKey(conversionId);
				return Msg.success();
			}catch(Exception e){
				return Msg.fail().add("msg", "处理失败");
			}
			
		}
	}
	/*
	 * update
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public Msg update(@RequestParam(value="conversionId",required=false)Integer conversionId,
			@RequestParam(value="voucher",required=false)Double voucher,
	        @RequestParam(value="draw",required=false)Double draw,
	        @RequestParam(value="probability",required=false)Double probability,
	        @RequestParam(value="remark",required=false)String remark){
		try{
			Conversione conversion=conversionService.selectByPrimaryKey(conversionId);
			if(conversion==null){
				return Msg.fail().add("msg", "请选择您要修改的");
			}else{
				if(voucher!=null){
					conversion.setVoucher(voucher);
				}
				if(draw!=null){
					conversion.setDraw(draw);
				}
				if(probability!=null){
					conversion.setProbability(probability);
				}
				if(remark!=null){
					conversion.setRemark(remark);
				}
				conversionService.updateByPrimaryKeySelective(conversion);
			}
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
		return Msg.success();
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
		return "jsp/conversionList";
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
		Conversione conversion=new Conversione();
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("conversion_id ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<Conversione> list =conversionService.selectByExample(conversion);
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
	
		int	id= Integer.parseInt(request.getParameter("conversionId"));
		Conversione conversion = conversionService.selectByPrimaryKey(id);
		model.addAttribute("obj", conversion);
		return "jsp/conversionForm";
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
	public Map<String, Object> set(HttpServletRequest request,@ModelAttribute("obj") Conversione conversion) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		conversionService.updateByPrimaryKeySelective(conversion);
		resultMap.put("type", "update");
		resultMap.put("code", "success");
		return resultMap;
	}
}
