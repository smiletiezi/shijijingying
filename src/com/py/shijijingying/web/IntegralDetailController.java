package com.py.shijijingying.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.py.shijijingying.entity.IntegralDetail;
import com.py.shijijingying.service.IntegralDetailService;
import com.py.shijijingying.utils.EmptyUtil;
import com.py.shijijingying.utils.Msg;


@Controller  
@RequestMapping("/detail")
public class IntegralDetailController {
	@Autowired
	private IntegralDetailService integralDetailService;
	/*
	 * 积分明细列表
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(@RequestParam(value="userId",required=false)Integer userId){
		IntegralDetail detail=new IntegralDetail();
		detail.setUserId(userId);
		List<IntegralDetail> details=new ArrayList<IntegralDetail>();
		try{
			details=integralDetailService.selectByExample(detail);
		
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
		return Msg.success(details);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public Msg delete(@RequestParam(value="detailId",required=false)Integer detailId){
		IntegralDetail detail=integralDetailService.selectByPrimaryKey(detailId);
		if(EmptyUtil.isEmpty(detail)){
			return Msg.fail().add("msg", "请选择你要删除的明细");
		}else{
			integralDetailService.deleteByPrimaryKey(detailId);
			return Msg.success();
		}
	}
}
