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
import com.py.shijijingying.entity.OrderProduct;
import com.py.shijijingying.entity.Percentage;
import com.py.shijijingying.entity.SalesmanGroup;
import com.py.shijijingying.entity.YearEnd;
import com.py.shijijingying.service.BoxinUserService;
import com.py.shijijingying.service.OrderProductService;
import com.py.shijijingying.service.PercentageService;
import com.py.shijijingying.service.YearEndService;
import com.py.shijijingying.utils.Msg;
/*
 * 业务员 我的团队
 */
@Controller  
@RequestMapping("/salesmanGroup")
public class SalesmanGroupController extends BaseController{
	@Autowired
	private BoxinUserService userService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private PercentageService  percentageService;
	@Autowired
	private YearEndService  yearEndService;
	@Autowired  
	 private BoxinUserService boxinUserService;
/*
 * 点击我的团队
 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(@RequestParam(value="userId",required=false)Integer userId){
		
		List<SalesmanGroup> groups =new ArrayList<SalesmanGroup>();
		BoxinUser user=userService.getUserById(userId);
		BoxinUser u=new BoxinUser();
		u.setUsergrourid(user.getUsernumber());
		List<BoxinUser>  users=userService.selectByExample(u);//获取该业务员下的所有合伙人
		
		for(int i=0;i<users.size();i++){
			BoxinUser us=userService.getUserById(users.get(i).getUserid());
			BoxinUser use=new BoxinUser();
			use.setUsergrourid(us.getUsernumber());
			List<BoxinUser> pusers=userService.selectByExample(use);//得到该合伙人下的所有普通用户
			SalesmanGroup group=new SalesmanGroup();
			 Double money=0.0;//用户总消费
			 Double cash=0.0;//合伙人预计收益
			 Double fund=0.0;//我的收益
			for(int j=0;j<pusers.size();j++){
				OrderProduct orderProduct=new OrderProduct();
				orderProduct.setUserId(pusers.get(j).getUserid());
				List<OrderProduct> products=orderProductService.selectByExample(orderProduct);
				
				 for(int m=0;m<products.size();m++){
					 Percentage percentage=new Percentage();
					 percentage.setName(products.get(m).getPercentage());
					List<Percentage> per=percentageService.selectByExample(percentage);
					double par=per.get(0).getPartner();//该类型合伙人提成
					double sal=per.get(0).getSalesman();//该类型业务人员提成
					int num=Integer.parseInt(products.get(m).getNum());//该商品购买件数
					double price=products.get(m).getPrice();//该订单金额
					money+=products.get(m).getPrice();
					cash+=par*price;
					fund+=sal*price;
				 }
				
			}
			 group.setUserId(users.get(i).getUserid());
			 group.setName(users.get(i).getUsername());
			 group.setCash(cash);
			 group.setMoney(money);
			 group.setFund(fund);
			 groups.add(group);
			 
				
		}
			 
		return Msg.success(groups);
	}
	
	
	/*
	 * 点击查看详情，参数 当前合伙人userId 访问合伙人团队 partnerGroup/list
	 */
	
	/*
	 * 点击年目标 
	 */
	@ResponseBody
	@RequestMapping(value="/selectY")
	public Msg selectY(@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="year",required=false)String year){
		BoxinUser user=userService.getUserById(userId);
		//合伙人我的团队逻辑
		Msg msg=Msg.success();
		msg.add("year", user.getYear());//年目标
		BoxinUser u=new BoxinUser();
		u.setUsergrourid(user.getUsernumber());
		List<BoxinUser>  users=userService.selectByExample(u);//获取该合伙人下的所有用户
		List<Integer> ids=new ArrayList<Integer>();
		for(int i=0;i<users.size();i++){
			ids.add(users.get(i).getUserid());
		}
		
		 Map<String,Object> map=new HashMap<String,Object>();
		 map.put("ids", ids);
		 map.put("createDate", year);
		 map.put("type", 1);
		 List<OrderProduct> products=orderProductService.selectByList(map);
		double money=0.0;
		 for(int j=0;j<products.size();j++){
			 Percentage percentage=new Percentage();
			 percentage.setName(products.get(j).getPercentage());
			List<Percentage> per=percentageService.selectByExample(percentage);
			double par=per.get(0).getPartner();//该类型合伙人提成
			int num=Integer.parseInt(products.get(j).getNum());//该商品购买件数
			double price=products.get(j).getPrice();//该订单价格
			money+=price;
			Double sum=par*price;
			products.get(j).setPartner(sum);
			orderProductService.updateByPrimaryKeySelective(products.get(j));
		 }
		 msg.add("money", money);//已完成
		 msg.add("products", products);
		return msg;
	}
	
	/*
	 * 点击月目标 
	 */
	@ResponseBody
	@RequestMapping(value="/selectM")
	public Msg selectM(@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="month",required=false)String month){
		BoxinUser user=userService.getUserById(userId);
		String[] str=month.split("-");
		String mon=str[1];
		YearEnd year=new YearEnd();
		year.setMonth(mon);
		List<YearEnd> years=yearEndService.selectByExample(year);
		double endnum=years.get(0).getRatio();//该月份对应的比例
		double endsum=endnum*user.getYear();
		//合伙人我的团队逻辑
		Msg msg=Msg.success();
		msg.add("月目标", endsum);
		BoxinUser u=new BoxinUser();
		u.setUsergrourid(user.getUsernumber());
		List<BoxinUser>  users=userService.selectByExample(u);//获取该合伙人下的所有用户
		List<Integer> ids=new ArrayList<Integer>();
		for(int i=0;i<users.size();i++){
			ids.add(users.get(i).getUserid());
		}
		
		 Map<String,Object> map=new HashMap<String,Object>();
		 map.put("ids", ids);
		 map.put("createDate", month);
		 map.put("type", 1);
		 List<OrderProduct> products=orderProductService.selectByList(map);
		double money=0.0;
		 for(int j=0;j<products.size();j++){
			 Percentage percentage=new Percentage();
			 percentage.setName(products.get(j).getPercentage());
			List<Percentage> per=percentageService.selectByExample(percentage);
			double par=per.get(0).getPartner();//该类型合伙人提成
			int num=Integer.parseInt(products.get(j).getNum());//该商品购买件数
			double price=products.get(j).getPrice();//该商品价格
			money+=price;
			Double sum=par*price;
			products.get(j).setPartner(sum);
			orderProductService.updateByPrimaryKeySelective(products.get(j));
		 }
		 msg.add("已完成", money);
		 msg.add("products", products);
		return msg;
	}
	
	
/*****************************************************后管考核管理*****************************/
	/**
	 * 跳转业务员列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toList")
	public String toList(HttpServletRequest request,Model model) {
		return "jsp/salesmanList";
	}	
	
	/**
	 * 获取业务员列表数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询业务员列表")
	@RequestMapping(value = "toListData")
	@ResponseBody
	public Map<String,Object> toListData(HttpServletRequest request) {
		String userbusinessname=request.getParameter("userbusinessname");
		BoxinUser user=new BoxinUser();
		user.setUseraccount("2");
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
	 * 跳转到合伙人form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toDown")
	public String toDown(HttpServletRequest request,Model model) {
			Integer id= Integer.parseInt(request.getParameter("userid"));
			List<SalesmanGroup> list =new ArrayList<SalesmanGroup>();
			BoxinUser obj=boxinUserService.getUserById(id);
			BoxinUser u=new BoxinUser();
			u.setUsergrourid(obj.getUsernumber());
			List<BoxinUser>  users=userService.selectByExample(u);//获取该业务员下的所有合伙人
			for(int i=0;i<users.size();i++){
				BoxinUser us=userService.getUserById(users.get(i).getUserid());
				BoxinUser use=new BoxinUser();
				use.setUsergrourid(us.getUsernumber());
				List<BoxinUser> pusers=userService.selectByExample(use);//得到该合伙人下的所有普通用户
				SalesmanGroup group=new SalesmanGroup();
				 Double money=0.0;//用户总消费
				 Double cash=0.0;//合伙人预计收益
				 Double fund=0.0;//我的收益
				for(int j=0;j<pusers.size();j++){
					OrderProduct orderProduct=new OrderProduct();
					orderProduct.setUserId(pusers.get(j).getUserid());
					List<OrderProduct> products=orderProductService.selectByExample(orderProduct);
					
					 for(int m=0;m<products.size();m++){
						 Percentage percentage=new Percentage();
						 percentage.setName(products.get(m).getPercentage());
						List<Percentage> per=percentageService.selectByExample(percentage);
						double par=per.get(0).getPartner();//该类型合伙人提成
						double sal=per.get(0).getSalesman();//该类型业务人员提成
						int num=Integer.parseInt(products.get(m).getNum());//该商品购买件数
						double price=products.get(m).getPrice();//该商品价格
						money+=products.get(m).getPrice();
						cash+=par*price;
						fund+=sal*price;
					 }
					
				}
				 group.setUserId(users.get(i).getUserid());
				 group.setName(users.get(i).getUserbusinessname());
				 group.setCash(cash);
				 group.setMoney(money);
				 group.setFund(fund);
				 list.add(group);
			}
			double finish=0;//已完成
			double money=0.0;//总收益
			for(int k=0;k<list.size();k++) {
				finish+=list.get(k).getMoney();
				money+=list.get(k).getFund();
			}
		    model.addAttribute("obj",obj);
		    model.addAttribute("finish",finish);
		    model.addAttribute("money",money);
		return "jsp/salesmantoDownList";
	}
	
	/**
	 * 获取合伙人业绩数据
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="查询合伙人业绩")
	@RequestMapping(value = "toDownData")
	@ResponseBody
	public Map<String,Object> toDownData(HttpServletRequest request,Model model) {
		Integer id=Integer.parseInt(request.getParameter("userid"));
		String name=request.getParameter("name");
		//返回map
				Map<String,Object> resultMap = Maps.newHashMap();
				//获取分页和排序条件
				LayerPage(request);
				//排序插件
				PageHelper.orderBy("userId ASC");
				//分页插件
				Page<?> page = PageHelper.startPage(pageNum, pageSize);
		//调用service
				List<SalesmanGroup> list =new ArrayList<SalesmanGroup>();
				BoxinUser user=userService.getUserById(id);
				BoxinUser u=new BoxinUser();
				u.setUsergrourid(user.getUsernumber());
				if(name!=null) {
					u.setUserbusinessname(name);
				}
				List<BoxinUser>  users=userService.selectByExample(u);//获取该业务员下的所有合伙人
				for(int i=0;i<users.size();i++){
					BoxinUser us=userService.getUserById(users.get(i).getUserid());
					BoxinUser use=new BoxinUser();
					use.setUsergrourid(us.getUsernumber());
					List<BoxinUser> pusers=userService.selectByExample(use);//得到该合伙人下的所有普通用户
					SalesmanGroup group=new SalesmanGroup();
					 Double money=0.0;//用户总消费
					 Double cash=0.0;//合伙人预计收益
					 Double fund=0.0;//我的收益
					for(int j=0;j<pusers.size();j++){
						OrderProduct orderProduct=new OrderProduct();
						orderProduct.setUserId(pusers.get(j).getUserid());
						List<OrderProduct> products=orderProductService.selectByExample(orderProduct);
						
						 for(int m=0;m<products.size();m++){
							 Percentage percentage=new Percentage();
							 percentage.setName(products.get(m).getPercentage());
							List<Percentage> per=percentageService.selectByExample(percentage);
							double par=per.get(0).getPartner();//该类型合伙人提成
							double sal=per.get(0).getSalesman();//该类型业务人员提成
							int num=Integer.parseInt(products.get(m).getNum());//该商品购买件数
							double price=products.get(m).getPrice();//该商品价格
							money+=products.get(m).getPrice();
							cash+=par*price;
							fund+=sal*price;
						 }
						
					}
					 group.setUserId(users.get(i).getUserid());
					 group.setName(users.get(i).getUserbusinessname());
					 group.setCash(cash);
					 group.setMoney(money);
					 group.setFund(fund);
					 list.add(group);
				}
//				double finish=0;
//				for(int k=0;k<list.size();k++) {
//					finish+=list.get(k).getMoney();
//				}
				
		resultMap.put("code", 0);
		resultMap.put("msg", "查询成功");
		resultMap.put("count", page.getTotal());
		resultMap.put("data", list);
		return resultMap;
	}
	
	/**
	 * 根据ID查询业绩详情
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@SystemControllerLog(description="查询业绩详情")
	@RequestMapping("toDetails")
	public String toDetails(HttpServletRequest request, Model model) {
		Integer id=Integer.parseInt(request.getParameter("userId"));
		BoxinUser user=userService.getUserById(id);
		
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
			double price=products.get(j).getPrice();//该商品价格
			Double sum=par*price;
			products.get(j).setPartner(sum);
			orderProductService.updateByPrimaryKeySelective(products.get(j));
		 }
		 List<OrderProduct> pros=orderProductService.selectByMonth(map);//本月订单产品
		 double money=0;
		 for(int k=0;k<pros.size();k++){
			 money+=pros.get(k).getPartner();
		 }
		 
		model.addAttribute("obj",products);
		model.addAttribute("money", money);
		return "jsp/salesmanDetails";
	}
	
	
	/**
	 * 跳转到合伙人form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toMonth")
	public String toMonth(HttpServletRequest request,Model model) {
			Integer id= Integer.parseInt(request.getParameter("id"));
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
        BoxinUser user=userService.getUserById(id);//该业务员
        double endsum=endnum*user.getYear();//该业务员月目标量
        BoxinUser u=new BoxinUser();
		u.setUsergrourid(user.getUsernumber());
		List<BoxinUser>  users=userService.selectByExample(u);//获取该业务员下的所有合伙人
		List<Integer> ids=new ArrayList<Integer>();
		for(int i=0;i<users.size();i++) {
			BoxinUser b=new BoxinUser();
			b.setUsergrourid(users.get(i).getUsernumber());
			List<BoxinUser>  us=userService.selectByExample(b);//获取该合伙人下的所有普通用户
			for(int j=0;j<us.size();j++) {
				ids.add(us.get(j).getUserid());
			}
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
        model.addAttribute("endsum",endsum);
        model.addAttribute("money",money);
		return "jsp/salesmantoMonth";
	}
	
	
	
	
	
}
