package com.py.shijijingying.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Maps;
import com.py.shijijingying.annotation.SystemControllerLog;
import com.py.shijijingying.entity.BoxinUser;
import com.py.shijijingying.entity.Order;
import com.py.shijijingying.entity.OrderProduct;
import com.py.shijijingying.entity.Percentage;
import com.py.shijijingying.entity.YearEnd;
import com.py.shijijingying.service.BoxinUserService;
import com.py.shijijingying.service.OrderProductService;
import com.py.shijijingying.service.OrderService;
import com.py.shijijingying.service.PercentageService;
import com.py.shijijingying.service.YearEndService;
import com.py.shijijingying.utils.Msg;



@Controller  
@RequestMapping("/partnerGroup")
public class PartnerGroupController extends BaseController{
	@Autowired
	private BoxinUserService userService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private PercentageService  percentageService;
	@Autowired
	private OrderService orderService;
	@Autowired  
	 private BoxinUserService boxinUserService;
	@Autowired
	private YearEndService  yearEndService;
	/*
	 * 点击我的团队list
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(@RequestParam(value="userId",required=false)Integer userId){
		
		BoxinUser user=userService.getUserById(userId);
		
			//合伙人我的团队逻辑
			BoxinUser u=new BoxinUser();
			u.setUsergrourid(user.getUsernumber());
			List<BoxinUser>  users=userService.selectByExample(u);//获取该合伙人下的所有用户
			List<Integer> ids=new ArrayList<Integer>();
			for(int i=0;i<users.size();i++){
				ids.add(users.get(i).getUserid());
			}
			
			 Map<String,Object> map=new HashMap<String,Object>();
			 map.put("ids", ids);
			 map.put("type", 1);
			 List<OrderProduct> products=orderProductService.selectByList(map); //全部订单产品
			
			 for(int j=0;j<products.size();j++){
				 Percentage percentage=new Percentage();
				 percentage.setName(products.get(j).getPercentage());
				List<Percentage> per=percentageService.selectByExample(percentage);
				double par=0;
				if(per.size()>0){
				 par=per.get(0).getPartner();//该类型合伙人提成
				}else{
					 par=0;
				}
				
				int num=Integer.parseInt(products.get(j).getNum());//该商品购买件数
				double price=products.get(j).getPrice();//该订单价格
				Double sum=par*price;
				products.get(j).setPartner(sum);
				orderProductService.updateByPrimaryKeySelective(products.get(j));
			 }
			 List<OrderProduct> pros=orderProductService.selectByMonth(map);//本月订单产品
			 double money=0;
			 for(int k=0;k<pros.size();k++){
				 money+=pros.get(k).getPartner();
			 }
			 
			 Msg msg=Msg.success();
			 msg.add("products", products);
			 msg.add("money", money);
			return Msg.success(msg);
		}
			
	
	/*
	 * 点击产看详情 传入订单编号 到OrderController
	 */
	
	
	/*
	 * 根据订单号 获取物流公司编码和物流单号
	 */
	@ResponseBody
	@RequestMapping(value="/getLogistics")
	public  Msg getLogistics(@RequestParam(value="orderNumber",required=false)String orderNumber){
		Order order=new Order();
		order.setOrderNumber(orderNumber); 
		List<Order> ords=orderService.selectByExample(order);
		String logisticsNumber="";
		String logisticsName="";
		if(ords.size()>0){
			logisticsNumber=ords.get(0).getLogisticsNumber();
			logisticsName=ords.get(0).getLogisticsName();
		}
		if(StringUtil.isNotEmpty(logisticsNumber)&&StringUtil.isNotEmpty(logisticsName)){
			Msg msg=Msg.success();
			msg.add("logisticsNumber", logisticsNumber);
			msg.add("logisticsName", logisticsName);
			return Msg.success(msg);
		}else{
			return Msg.fail().add("msg", "该订单暂未发货");
		}
		
	}
	
	/************************************************合伙人业绩考核*********************************/
	
	/**
	 * 跳转合伙人列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toList")
	public String toList(HttpServletRequest request,Model model) {
		return "jsp/partnerGroupList";
	}	
	
	/**
	 * 获取合伙人列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询合伙人列表")
	@RequestMapping(value = "toPartnerListData")
	@ResponseBody
	public Map<String,Object> toPartnerListData(HttpServletRequest request) {
		String userbusinessname=request.getParameter("userbusinessname");
		BoxinUser user=new BoxinUser();
		user.setUseraccount("3");
		if(StringUtil.isNotEmpty(userbusinessname)) {
			user.setUserbusinessname(userbusinessname); 
		}
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("userid ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
		
		List<BoxinUser> list = boxinUserService.selectByExample(user);
		//返回layui数据
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}
	
	/**
	 * 根据ID查询合伙人业绩
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@SystemControllerLog(description="查询")
	@RequestMapping("toDetails")
	public String toDetails(HttpServletRequest request, Model model) {
		Integer id=Integer.parseInt(request.getParameter("userid"));
		BoxinUser obj = boxinUserService.getUserById(id);//该合伙人
		BoxinUser user=new BoxinUser();
		user.setUsergrourid(obj.getUsernumber());
		List<BoxinUser>  users=userService.selectByExample(user);//获取该合伙人下的所有用户
		List<Integer> ids=new ArrayList<Integer>();
		for(int i=0;i<users.size();i++){
			ids.add(users.get(i).getUserid());
		}
		
		 Map<String,Object> map=new HashMap<String,Object>();
		 map.put("ids", ids);
		 map.put("type", 1);
		 List<OrderProduct> products=orderProductService.selectByList(map); //全部订单产品
		double finish=0.0;//年已完成量
		double money=0.0;//总收益
		for(int j=0;j<products.size();j++) {
			finish+=products.get(j).getPrice();
			money+=products.get(j).getPartner();
		}
		model.addAttribute("year",obj.getYear());//年目标量
		model.addAttribute("finish",finish);//已完成量
		model.addAttribute("money",money);//已完成量
		model.addAttribute("obj",products);//订单详情
		return "jsp/partnerGroupDetails";
	}
	
	
	/**
	 * 跳转到合伙人月业绩form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toMonth")
	public String toMonth(HttpServletRequest request,Model model) {
			Integer id= Integer.parseInt(request.getParameter("userid"));
			String month=request.getParameter("month");
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        String[] str=format.split("-");
        String mon=str[0]+"-"+month;
        YearEnd year=new YearEnd();
		year.setMonth(month);
		List<YearEnd> years=yearEndService.selectByExample(year);
		double endnum=years.get(0).getRatio();//该月份对应的比例
        BoxinUser user=userService.getUserById(id);//该合伙人
        double endsum=endnum*user.getYear();//该业务员月目标量
        BoxinUser u=new BoxinUser();
		u.setUsergrourid(user.getUsernumber());
		List<BoxinUser>  users=userService.selectByExample(u);//获取该合伙人下的所有普通用户
		List<Integer> ids=new ArrayList<Integer>();
		for(int i=0;i<users.size();i++) {
				ids.add(users.get(i).getUserid());
		}
		
		Map<String,Object> map=new HashMap<String,Object>();
		 map.put("ids", ids);
		 map.put("createDate", mon);
		 map.put("type", 1);
		 List<OrderProduct> products=orderProductService.selectByList(map);
		double money=0.0;
		for(int k=0;k<products.size();k++) {
			money+=products.get(k).getPrice();
		}
        model.addAttribute("endsum",endsum);//该月目标量
        model.addAttribute("money",money);//该月已完成量
		return "jsp/salesmantoMonth";
	}
	
}
