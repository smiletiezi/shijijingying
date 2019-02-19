package com.py.shijijingying.web;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Maps;
import com.py.shijijingying.annotation.SystemControllerLog;
import com.py.shijijingying.entity.ExcleOrder;
import com.py.shijijingying.entity.LogisticsBean;
import com.py.shijijingying.entity.Order;
import com.py.shijijingying.entity.OrderProduct;
import com.py.shijijingying.service.OrderProductService;
import com.py.shijijingying.service.OrderService;
import com.py.shijijingying.utils.ExcelUtil;
import com.py.shijijingying.utils.Msg;
import com.py.shijijingying.utils.kd100Util;

@Controller  
@RequestMapping("/order")
public class OrderController extends BaseController{
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderProductService orderProductService;
	/*
	 * 后台输入物流编号
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public Msg update(@RequestParam(value="orderId",required=false)Integer orderId,
			@RequestParam(value="logisticsNumber",required=false)String logisticsNumber,
			@RequestParam(value="logisticsName",required=false)String logisticsName){
		Order order=orderService.selectByPrimaryKey(orderId);
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 if(logisticsNumber!=null&&logisticsName!=null){
			 order.setLogisticsNumber(logisticsNumber);
			 order.setLogisticsName(logisticsName);
			 order.setUpdateTime(format.format(new Date()));
			order.setDelivery("1");//已发货
		 }else{
			 return Msg.fail().add("msg", "请输入物流编号");
		 }
		
		try{
			orderService.updateByPrimaryKey(order);
			return Msg.success();
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
	}
	
	/*
	 * app点击确认收货
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public Msg add(@RequestParam(value="orderId",required=false)Integer orderId){
		Order order=orderService.selectByPrimaryKey(orderId);
		order.setShipType("1");
		try{
		orderService.updateByPrimaryKey(order);
		return Msg.success();
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
	}
	
	/*
	 * 后台订单列表
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="pageSize",required=false)Integer pageSize){
		Order order=new Order();
		//排序插件
		PageHelper.orderBy("order_id asc");
		//分页插件
		if(pageNum==null){
			pageNum=1;
		}
		if(pageSize==null){
			pageSize=10;
		}
		
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		List<Order> orders=orderService.selectByExample(order);
		return Msg.success(orders);
	}
	/*
	 * 后台待审核订单列表
	 */
	@ResponseBody
	@RequestMapping(value="/deliveryList")
	public Msg deliveryList(@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="pageSize",required=false)Integer pageSize){
		Order order=new Order();
		order.setOrderType("1");//已支付
		order.setDelivery("0");//代发货
		//排序插件
				PageHelper.orderBy("order_id asc");
				//分页插件
				if(pageNum==null){
					pageNum=1;
				}
				if(pageSize==null){
					pageSize=10;
				}
				
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		List<Order> orders=orderService.selectByExample(order);
		if(orders==null&&orders.size()==0){
			return Msg.success("没有代发货订单");
		}else{
			return Msg.success(orders);
		}
		
	}
	
	/*
	 * app访问订单列表
	 */
	@ResponseBody
	@RequestMapping(value="/api/list")
	public Msg apilist(@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="orderType",required=false)String orderType,
			@RequestParam(value="delivery",required=false)String delivery,
			@RequestParam(value="shipType",required=false)String shipType,
			@RequestParam(value="orderNumber",required=false)String orderNumber,
			@RequestParam(value="createDate",required=false)String createDate,
			@RequestParam(value="evaluate",required=false)String evaluate){
		Order order=new Order();
		order.setDeleteType("0");//未删除的
		if(userId!=null){
			order.setUserId(userId);
		}
		if(orderType!=null && orderType!=""){
			order.setOrderType(orderType);
		}
		if(delivery!=null && delivery!=""){
			order.setDelivery(delivery);
		}
		if(shipType!=null && shipType!=""){
			order.setShipType(shipType);
		}
		if(orderNumber!=null && orderNumber!=""){
			order.setOrderNumber(orderNumber);
		}
		if(createDate!=null && createDate!=""){
			order.setCreateDate(createDate);
		}
		if(evaluate!=null && evaluate!=""){
			order.setEvaluate(evaluate);
		}
		try{
		List<Order> orders=orderService.selectByExample(order);
		return Msg.success(orders);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
	}
	
	
	/*
	 * 后台访问订单详情
	 */
	@ResponseBody
	@RequestMapping(value="/list/deta")	
	public Msg deta(@RequestParam(value="orderId",required=false)Integer orderId){
		Order order =new Order();
		order.setOrderId(orderId);
		try {
			Order ord=orderService.selectByPrimaryKey(orderId);
			OrderProduct op=new OrderProduct();
			op.setOrderNumber(ord.getOrderNumber());
			List<OrderProduct> ords=orderProductService.selectByExample(op);
			return Msg.success(ords);
		} catch (Exception e) {
			return Msg.fail().add("msg", "处理失败");
		}
		
	}
	/*
	 * app访问订单详情
	 */
	@ResponseBody
	@RequestMapping(value="/list/detail")	
	public Msg detail(@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="orderNumber",required=false)String orderNumber){
		OrderProduct ord=new OrderProduct();
		if(userId!=null){
			ord.setUserId(userId);
		}
		if(orderNumber!=null){
			ord.setOrderNumber(orderNumber);
		}
		
		
		try{
			List<OrderProduct> products=orderProductService.selectByExample(ord);
			return Msg.success(products);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
		
		
	}
	
	
	
	
	/*
	 * 订单删除api
	 */
	@ResponseBody
	@RequestMapping(value="/api/delete")
	public Msg apidelete(@RequestParam(value="orderId",required=false)Integer orderId){
		Order order=orderService.selectByPrimaryKey(orderId);
		System.out.println(order.getOrderType());
		if(order.getOrderType().equals("0")){
			order.setDeleteType("1");
			OrderProduct ord=new OrderProduct();
			ord.setOrderNumber(order.getOrderNumber());
			orderService.updateByPrimaryKey(order);
			List<OrderProduct> products=orderProductService.selectByExample(ord);
			for(int i=0;i<products.size();i++){
				orderProductService.deleteByPrimaryKey(products.get(i).getOrderProductId());
			}
			return Msg.success();
		}else if(order.getShipType().equals("1")&&order.getOrderType().equals("1")){
			order.setDeleteType("1");
			OrderProduct ord=new OrderProduct();
			ord.setOrderNumber(order.getOrderNumber());
			orderService.updateByPrimaryKey(order);
			List<OrderProduct> products=orderProductService.selectByExample(ord);
			for(int i=0;i<products.size();i++){
				orderProductService.deleteByPrimaryKey(products.get(i).getOrderProductId());
			}
			return Msg.success();
			
		}else{
			return Msg.fail().add("msg", "已付款 未收货，暂不能删除该订单");
		}
	}
	
	/**
	 * 物流查询
	 * @throws Exception 
	 * @throws IOException 
	 */	
	@ResponseBody
	@RequestMapping(value="/logistics")
	public Map<String,Object> logistics(@RequestParam(value="orderId",required=false)Integer orderId) throws Exception {
		Map<String,Object> resultMap = Maps.newHashMap();
		if(orderId==null) {
			resultMap.put("code", 1);
			resultMap.put("msg", "请选择订单");
			return resultMap;
		}
		Order order=orderService.selectByPrimaryKey(orderId);
		String logisticsName=order.getLogisticsName();//物流名称
		String logisticsNumber=order.getLogisticsNumber();//物流单号
		if(StringUtil.isNotEmpty(logisticsName) && StringUtil.isNotEmpty(logisticsNumber)) {
			String msg=kd100Util.query(logisticsNumber, logisticsName);
			LogisticsBean bean=JSON.parseObject(msg, LogisticsBean.class);
			//String tmp = StringEscapeUtils.unescapeEcmaScript(msg);
			resultMap.put("code", 0);
			resultMap.put("msg", "查询成功");
			resultMap.put("log", bean);
			return resultMap;
		}else {
			resultMap.put("code", 1);
			resultMap.put("msg", "订单暂未发货");
			return resultMap;
		}
		
	}
	
	
	
/**
 * 订单导出
 * @throws IOException 
 */
	@ResponseBody
	@RequestMapping(value="/expot")
	public  Map<String,Object> expot(HttpServletResponse resp,HttpServletRequest request) throws IOException{
		//返回map
		Map<String,Object> resultMap = Maps.newHashMap();
//		String orderIds ="";
//		for (int i = 0; i < ids.length; i++){
//			orderIds+=ids[i]+",";
//		}
		//获取前台传来的订单id集合
		String orderIds=request.getParameter("ids");
		//取到所有订单
		List<Order> ords=orderService.selectByList(orderIds);
		//excle表格数据
		List<ExcleOrder> list=new ArrayList<ExcleOrder>();
		for(int i=0;i<ords.size();i++){
			OrderProduct op=new OrderProduct();
			op.setOrderNumber(ords.get(i).getOrderNumber());
			List<OrderProduct> ops=orderProductService.selectByExample(op);
			for(int j=0;j<ops.size();j++){
				ExcleOrder eo=new ExcleOrder();
				eo.setOrderId(ords.get(i).getOrderId());
				eo.setOrderNumber(ords.get(i).getOrderNumber());
				eo.setCreateTime(ords.get(i).getCreateDate());
				eo.setNum(ops.get(j).getNum());
				eo.setProductName(ops.get(j).getProductName());
				eo.setSize(ops.get(j).getSize());
				eo.setUserName(ords.get(i).getUserName());
				eo.setShipAddress(ords.get(i).getShipAddress());
				eo.setShipPerson(ords.get(i).getShipPerson());
				eo.setShipPhone(ords.get(i).getOrderPhone());
				eo.setMoney(ops.get(j).getPrice());
				list.add(eo);
			}
		}
		JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
		Map<String,String> headMap = new LinkedHashMap<String,String>();
		 headMap.put("orderId","订单ID");
		 headMap.put("orderNumber","订单编号");
		 headMap.put("userName","购买单位/购买人");
		 headMap.put("createTime","下单时间");
		 headMap.put("productName","商品名称");
		 headMap.put("size", "商品规格");
		 headMap.put("num","购买数量");
		 headMap.put("shipAddress","收货地址");
		 headMap.put("shipPerson","收货人");
		 headMap.put("shipPhone","收货人电话");
		 headMap.put("money","支付金额");
		  String title="订单数据";
		  OutputStream outXlsx = resp.getOutputStream();
		  ExcelUtil.downloadExcelFile(title,headMap,array,resp,0,outXlsx);//下载
			outXlsx.close();
			//返回layui数据
			resultMap.put("type", "expot");
			resultMap.put("code", "success");
			return resultMap;
	}
	
	
	/************************************************后管修改*********************************************/
	
	/**
	 * 跳转订单列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toList")
	public String toList(HttpServletRequest request,Model model) {
		return "jsp/orderList";
	}	
	
	/**
	 * 获取已支付订单列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询产品列表")
	@RequestMapping(value = "toListData")
	@ResponseBody
	public Map<String,Object> toListData(HttpServletRequest request) {
		String orderNumber=request.getParameter("orderNumber");
		Order order=new Order();
		order.setOrderType("1");//已支付
		//order.setDelivery("0");//代发货
		if(StringUtil.isNotEmpty(orderNumber)) {
			order.setOrderNumber(orderNumber);
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("order_id ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		        //调用service
				List<Order> list=orderService.selectByExample(order);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}
	
	/**
	 * 根据ID查询订单详情
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@SystemControllerLog(description="查询产品详情")
	@RequestMapping("toDetails")
	public String toDetails(HttpServletRequest request, Model model) {
		Integer id=Integer.parseInt(request.getParameter("orderId"));
		Order ord=orderService.selectByPrimaryKey(id);
		OrderProduct op=new OrderProduct();
		op.setOrderNumber(ord.getOrderNumber());
		List<OrderProduct> list=orderProductService.selectByExample(op);
		model.addAttribute("obj",list);
		return "jsp/orderDetails";
	}
	
	/**
	 * 页面跳转 发货form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toForm")
	public String getUserList(HttpServletRequest request,Model model) {
		
		Integer id= Integer.parseInt(request.getParameter("orderId"));
		Order ord=orderService.selectByPrimaryKey(id);
		model.addAttribute("obj", ord);
		return "jsp/orderForm";
	}
	
	/**
	 * 订单发货
	 * @param request
	 * @param commodityClassification
	 * @return
	 */
	@SystemControllerLog(description="修改产品")
	@RequestMapping(value="set")
	@ResponseBody
	public Map<String, Object> set(HttpServletRequest request,@ModelAttribute("obj") Order order) {
		//返回map
		Map<String, Object> resultMap = Maps.newHashMap();
		Integer id=order.getOrderId();
		Order ord=orderService.selectByPrimaryKey(id);
		ord.setLogisticsName(order.getLogisticsName());
		ord.setLogisticsNumber(order.getLogisticsNumber());
		ord.setDelivery("1");
		orderService.updateByPrimaryKey(ord);
		resultMap.put("type", "add");
		resultMap.put("code", "success");
		return resultMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

