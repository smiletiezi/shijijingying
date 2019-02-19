package com.py.shijijingying.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.py.shijijingying.entity.Voucher;
import com.py.shijijingying.service.VoucherService;
import com.py.shijijingying.utils.Msg;

@Controller  
@RequestMapping("/voucher")
public class VoucherController extends BaseController{
	@Autowired
	private VoucherService voucherService;
	
	/*
	 * 新增优惠券
	 */
	
	@ResponseBody
	@RequestMapping(value="/add")
	public Msg add(@RequestParam(value="voucherName",required=false)String voucherName,
            @RequestParam(value="voucherPar",required=false)Integer voucherPar,
            @RequestParam(value="voucherMore",required=false)String voucherMore,
            @RequestParam(value="voucherTime",required=false)String voucherTime,
            @RequestParam(value="voucherType",required=false)String voucherType,
            @RequestParam(value="present",required=false)String present,
            @RequestParam(value="integration",required=false)Integer integration){
		Voucher voucher=new Voucher();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(voucherName!=null){
			voucher.setVoucherName(voucherName);
		}
		if(voucherPar!=null){
			voucher.setVoucherPar(voucherPar);
		}
		if(voucherMore!=null){
			voucher.setVoucherMore(voucherMore);
		}
		if(voucherTime!=null){
			voucher.setVoucherTime(voucherTime);	
		}
		if(voucherType!=null){
			voucher.setVoucherType(voucherType);
		}
		if(present!=null){
			voucher.setPresent(present);
		}
		if(integration!=null){
			voucher.setIntegration(integration);
		}
		
		List<Voucher> vouchers=voucherService.selectByExample(voucher);

		if(null == vouchers || vouchers.size() ==0){
			if(voucherName!=null){
				voucher.setVoucherName(voucherName);
			}
			if(voucherPar!=null){
				voucher.setVoucherPar(voucherPar);
			}else{
				return Msg.fail().add("msg", "请设置优惠券面值");
			}
			if(voucherMore!=null){
				voucher.setVoucherMore(voucherMore);
			}else{
				return Msg.fail().add("msg", "请设置该优惠券满多少使用");
			}
			if(voucherTime!=null){
				voucher.setVoucherTime(voucherTime);
			}else{
				return Msg.fail().add("msg", "请设置该优惠券有效期");
			}
			if(voucherType!=null){
				voucher.setVoucherType(voucherType);
			}else{
				voucher.setVoucherType("0");
			}
			if(present!=null){
				if(present.equals("1")){
					voucher.setPresent(present);
					if(integration!=null){
						voucher.setIntegration(integration);
					}else{
						return Msg.fail().add("msg", "请设置该优惠券需要多少积分兑换");
					}
					
				}else{
					voucher.setPresent(present);
					voucher.setIntegration(0);
				}
			}else{
				return Msg.fail().add("msg", "请设置该优惠券是否可用于抽奖");
			}
			Date d = new Date();
			voucher.setVoucherCreateTime(format.format(d));
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DATE, Integer.parseInt(voucherTime));
			d=ca.getTime();
			voucher.setVoucherExpiry(format.format(d));
			voucher.setVoucherUse("0");
			try{
			voucherService.insertSelective(voucher);
			}catch(Exception e){
				return Msg.fail().add("msg", "处理失败");
			}
			return Msg.success();
		}else{
			return Msg.fail().add("msg", "该优惠券已存在");
		}
	}
	/*
	 * 修改优惠券
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public Msg update(@RequestParam(value="voucherId",required=false)Integer voucherId,
            @RequestParam(value="voucherName",required=false)String voucherName,
            @RequestParam(value="voucherPar",required=false)Integer voucherPar,
            @RequestParam(value="voucherMore",required=false)String voucherMore,
            @RequestParam(value="voucherTime",required=false)String voucherTime,
            @RequestParam(value="voucherType",required=false)String voucherType,
            @RequestParam(value="present",required=false)String present,
            @RequestParam(value="integration",required=false)Integer integration) throws ParseException{
		Voucher voucher=voucherService.selectByPrimaryKey(voucherId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(voucher!=null){
			
			if(voucherName!=null){
				voucher.setVoucherName(voucherName);
			}
			if(voucherPar!=null){
				voucher.setVoucherPar(voucherPar);
			}
			if(voucherMore!=null){
				voucher.setVoucherMore(voucherMore);
			}
			if(voucherTime!=null){
				voucher.setVoucherTime(voucherTime);
				Date createTime=format.parse(voucher.getVoucherCreateTime());
				Calendar ca = Calendar.getInstance();
				ca.add(Calendar.DATE, Integer.parseInt(voucherTime));
				createTime=ca.getTime();
				voucher.setVoucherExpiry(format.format(createTime));
			}
			if(voucherType!=null){
				voucher.setVoucherType(voucherType);
			}else{
				voucher.setVoucherType("0");
			}
			if(present!=null){
				if(present.equals("1")){
					voucher.setPresent(present);
					if(integration!=null){
						voucher.setIntegration(integration);
					}else{
						return Msg.fail().add("msg", "请设置该优惠券需要多少积分兑换");
					}
					
				}else{
					voucher.setPresent(present);
					voucher.setIntegration(0);
				}
			}
			try{
				voucherService.updateByPrimaryKey(voucher);
			}catch(Exception e){
				return Msg.fail().add("msg", "处理失败");
			}
			return Msg.success();
		}else{
			return Msg.fail().add("msg", "请点击优惠券");
		}
	}
	
	
	/*
	 * 点击我的优惠券是先执行该方法
	 * 
	
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg uplode(@RequestParam(value="userId",required=false)Integer userId) throws ParseException{
		Voucher voucher=new Voucher();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		voucher.setUserId(userId);
		List<Voucher> vouchers=voucherService.selectByExample(voucher);
		if(vouchers!=null || vouchers.size()>0){
			for(int i=0;i<vouchers.size();i++){
				Date voucherExpiry=format.parse(vouchers.get(i).getVoucherExpiry());//过期时间
				Date time=new Date();//当前时间
				if(voucherExpiry.before(time)){
					vouchers.get(i).setVoucherOverdue("1");
					voucherService.updateByPrimaryKey(vouchers.get(i));
				}
		}
			return Msg.success(vouchers);
		}else{
			return Msg.fail().add("mag", "没有代金券");
		}
	}
	
	/*
	 * 查看已使用的voucherUse=1，未使用的voucherUse=0，已过期的voucherOverdue=1
	 */
	/*
	@ResponseBody
	@RequestMapping(value="/rlist")
	public Msg rlist(@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="voucherUse",required=false)String voucherUse,
			@RequestParam(value="voucherOverdue",required=false)String voucherOverdue){
		Voucher voucher=new Voucher();
		voucher.setUserId(userId);
		voucher.setVoucherOverdue(voucherOverdue);
		voucher.setVoucherUse(voucherUse);
		List<Voucher> vouchers=voucherService.selectByExample(voucher);
		if(vouchers!=null || vouchers.size()>0){
		return Msg.success(vouchers);
		}else{
			return Msg.fail().add("mag", "没有代金券");
		}
	}
	*/
	/*
	 * 删除代金券
	 */
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public Msg delete(@RequestParam(value="voucherId",required=false)Integer voucherId){
		Voucher voucher=new Voucher();
		voucher.setVoucherId(voucherId);
		Voucher vouchers=voucherService.selectByPrimaryKey(voucherId);
		if(vouchers.getVoucherUse()=="1"){
			return Msg.fail().add("msg", "删除失败，用户使用中");
		}else{
			try{
				voucherService.deleteByPrimaryKey(voucherId);
				return Msg.success();
			}catch(Exception e){
				return Msg.fail().add("msg", "处理失败");
			}
		}
		
	}

	/*
	 * 代金券列表
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="pageSize",required=false)Integer pageSize){
		//排序插件
		PageHelper.orderBy("voucher_id asc");
		//分页插件
		if(pageNum==null){
			pageNum=1;
		}
		if(pageSize==null){
			pageSize=10;
		}
		
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		List<Voucher> vouchers=new ArrayList<Voucher>();
		Voucher voucher=new Voucher();
		try{
			vouchers=voucherService.selectByExample(voucher);
			return Msg.success(vouchers);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
	}
	
	/*
	 *获取可用于积分兑换的代金券列表
	 */
	@ResponseBody
	@RequestMapping(value="/personalList")
	public Msg personalList(){
		List<Voucher> vouchers=new ArrayList<Voucher>();
		Voucher voucher=new Voucher();
		voucher.setPresent("1");
		try{
			vouchers=voucherService.selectByExample(voucher);
			return Msg.success(vouchers);
		}catch(Exception e){
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
		return "jsp/voucherList";
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
		Voucher voucher=new Voucher();
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("voucher_id ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<Voucher> list =voucherService.selectByExample(voucher);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}
	/**
	 * 根据ID查询代金券详情
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@SystemControllerLog(description="查询代金券详情")
	@RequestMapping("toDetails")
	public String toDetails(HttpServletRequest request, Model model) {
		Integer id=Integer.parseInt(request.getParameter("voucherId"));
		Voucher voucher=voucherService.selectByPrimaryKey(id);
		model.addAttribute("obj",voucher);
		return "jsp/voucherDetails";
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
			id= Integer.parseInt(request.getParameter("voucherId"));
		} catch (Exception e) {
			id = 0;
		}
		if(id > 0) {
			Voucher voucher = voucherService.selectByPrimaryKey(id);
			model.addAttribute("obj", voucher);
		}
		return "jsp/voucherForm";
	}
	
	/**
	 * 新增代金券
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="新增代金券")
	@RequestMapping(value="insert")
	@ResponseBody
	public Map<String, Object> insert(HttpServletRequest request,@ModelAttribute("obj") Voucher voucher) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		voucher.setVoucherCreateTime(format.format(d));
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DATE, Integer.parseInt(voucher.getVoucherTime()));
		d=ca.getTime();
		voucher.setVoucherExpiry(format.format(d));
		voucher.setVoucherUse("0");
		voucherService.insertSelective(voucher);
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
	public Map<String, Object> set(HttpServletRequest request,@ModelAttribute("obj") Voucher voucher) throws ParseException {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(StringUtil.isNotEmpty(voucher.getVoucherTime())) {
			Date createTime=format.parse(voucher.getVoucherCreateTime());
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DATE, Integer.parseInt(voucher.getVoucherTime()));
			createTime=ca.getTime();
			voucher.setVoucherExpiry(format.format(createTime));
		}
		voucherService.updateByPrimaryKeySelective(voucher);
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
		voucherService.deleteByPrimaryKey(id);
		resultMap.put("type", "delete");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	
	
}
