package com.py.shijijingying.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.py.shijijingying.entity.BoxinUser;
import com.py.shijijingying.entity.Evaluate;
import com.py.shijijingying.entity.Order;
import com.py.shijijingying.entity.OrderProduct;
import com.py.shijijingying.entity.Product;
import com.py.shijijingying.service.BoxinUserService;
import com.py.shijijingying.service.EvaluateService;
import com.py.shijijingying.service.OrderProductService;
import com.py.shijijingying.service.OrderService;
import com.py.shijijingying.service.ProductService;
import com.py.shijijingying.utils.EmptyUtil;
import com.py.shijijingying.utils.Msg;

@Controller  
@RequestMapping("/evaluate")
public class EvaluateController extends BaseController{
	@Autowired
	private EvaluateService evaluateService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private ProductService productService;
	@Autowired
	private BoxinUserService userService;
	
	@RequestMapping("/showTest")
	public String showTest(){
		return "admin/test";
	}
	/*
	 * 发表评价
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public Msg insert(@RequestParam(value="userId",required=false)Integer userId,
            @RequestParam(value="evaluate",required=false)String evaluate,
            @RequestParam(value="img",required=false)String img,
            @RequestParam(value="description",required=false)String description,
            @RequestParam(value="logistics",required=false)String logistics,
            @RequestParam(value="attitude",required=false)String attitude,
            @RequestParam(value="anonymous",required=false)String anonymous,
            @RequestParam(value="orderId",required=false)Integer orderId,
            HttpServletRequest request) throws IllegalStateException, IOException{
		Evaluate eva =new Evaluate();
		BoxinUser user=userService.getUserById(userId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(userId!=null){
			eva.setUserId(userId);
			eva.setUserName(user.getUsername());
			eva.setUserImg(user.getUserimg());
		}
		if(evaluate!=null && evaluate!=""){
			eva.setEvaluate(evaluate);
		}
		if(img!=null && img!=""){
			eva.setImg(img);
		}
		if(description!=null && description!=""){
			eva.setDescription(description);
		}
		if(logistics!=null && logistics!=""){
			eva.setLogistics(logistics);
		}
		if(attitude!=null && attitude!=""){
			eva.setAttitude(attitude);
		}
		if(anonymous!=null && anonymous!=""){
			eva.setAnonymous(anonymous);
		}else{
			eva.setAnonymous("0");
		}
			eva.setAuditing("0");
			eva.setDate(format.format(new Date()));
		try{
			
			Order order=orderService.selectByPrimaryKey(orderId);
			OrderProduct product=new OrderProduct();
			product.setOrderNumber(order.getOrderNumber());
			List<OrderProduct> pros=orderProductService.selectByExample(product);
			//List<String> names=new ArrayList<String>();
			for(int i=0;i<pros.size();i++){
				if(StringUtil.isNotEmpty(pros.get(i).getProductName())){
					Product pro=new Product();
					pro.setProductName(pros.get(i).getProductName());
					List<Product> products=productService.selectByExample(pro);
					if(products.isEmpty()==false){
						eva.setProductId(products.get(0).getProductId());
					}
					evaluateService.insertSelective(eva);
				}
			}
			
			//evaluateService.insertSelective(eva);
			order.setEvaluate("1");
			orderService.updateByPrimaryKey(order);
			return Msg.success();
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
	}
	
	/*
	 * 前台审核
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public Msg update (@RequestParam(value="evaluateId",required=false)Integer evaluateId,
			@RequestParam(value="auditing",required=false)String auditing){
		Evaluate eva=evaluateService.selectByPrimaryKey(evaluateId);
		if(EmptyUtil.isEmpty(eva)){
			return Msg.fail().add("msg", "请选择一条评论");
		}else{
			eva.setAuditing(auditing);
			evaluateService.updateByPrimaryKeySelective(eva);
			return Msg.success();
		}
		
	}
	/*
	 * 后台管理 获取待审核评价
	 */
	@ResponseBody
	@RequestMapping(value="/auditingList")
	public Msg auditingList(@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="pageSize",required=false)Integer pageSize){
		Evaluate eva=new Evaluate();
		eva.setAuditing("0");//待审核
		try{
			//排序插件
			PageHelper.orderBy("evaluateId asc");
			//分页插件
			if(pageNum==null){
				pageNum=1;
			}
			if(pageSize==null){
				pageSize=10;
			}
			
			Page<?> page = PageHelper.startPage(pageNum, pageSize);
			List<Evaluate> evas=evaluateService.selectByExample(eva);
			return Msg.success(evas);
		}catch(Exception e){
			return Msg.fail().add("msg","处理失败");
		}
		
		
	}
	
	/*
	 * 评价列 可以是我的评价，产品评价 
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(@RequestParam(value="userId",required=false)Integer userId,
            @RequestParam(value="productId",required=false)Integer productId){
		Evaluate eva=new Evaluate();
		if(userId==null&&productId==null){
			List<Evaluate> evas=evaluateService.selectByExample(eva);//针对前台显示所有评价
			return Msg.success(evas);
		}else{
			if(userId!=null){
			eva.setUserId(userId);//我的评价（所有）
		}
		if(productId!=null){
			eva.setProductId(productId);//产品评价（所有）
		}
		eva.setAuditing("1");//审核通过
		List<Evaluate> evas=evaluateService.selectByExample(eva);
		return Msg.success(evas);
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
		return "jsp/evaluateList";
	}	
	
	/**
	 * 获取待审核评价列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询评价列表")
	@RequestMapping(value = "toListData")
	@ResponseBody
	public Map<String,Object> toListData(HttpServletRequest request) {
		Evaluate evaluate=new Evaluate();
		evaluate.setAuditing("0");
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("evaluateId ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<Evaluate> list =evaluateService.selectByExample(evaluate);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}
	
	/**
	 * 根据ID查询评价详情
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@SystemControllerLog(description="查询评价详情")
	@RequestMapping("toDetails")
	public String toDetails(HttpServletRequest request, Model model) {
		Integer id=Integer.parseInt(request.getParameter("evaluateId"));
		Evaluate eva=evaluateService.selectByPrimaryKey(id);
		model.addAttribute("obj",eva);
		return "jsp/evaluateDetails";
	}
	
	
	/**
	 * 评价审核
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="评价审核")
	@RequestMapping(value="set")
	@ResponseBody
	public Map<String, Object> set(HttpServletRequest request,@ModelAttribute("obj") Evaluate evaluate) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		Integer id=evaluate.getEvaluateId();
		Evaluate eva=evaluateService.selectByPrimaryKey(id);
		evaluate.setAuditing(evaluate.getAuditing());
		evaluateService.updateByPrimaryKey(evaluate);
		resultMap.put("type", "add");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	/**
	 * 删除评价
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="删除评价")
	@RequestMapping(value="redelete")
	@ResponseBody
	public Map<String, Object> redelete(HttpServletRequest request) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		evaluateService.deleteByPrimaryKey(id);
		resultMap.put("type", "delete");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	
}
