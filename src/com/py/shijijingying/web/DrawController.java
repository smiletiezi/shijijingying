package com.py.shijijingying.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.py.shijijingying.entity.BoxinUser;
import com.py.shijijingying.entity.Conversione;
import com.py.shijijingying.entity.MyVoucher;
import com.py.shijijingying.entity.Order;
import com.py.shijijingying.entity.OrderProduct;
import com.py.shijijingying.entity.Product;
import com.py.shijijingying.entity.ProductType;
import com.py.shijijingying.entity.ShipAddress;
import com.py.shijijingying.entity.Voucher;
import com.py.shijijingying.service.BoxinUserService;
import com.py.shijijingying.service.ConversioneService;
import com.py.shijijingying.service.MyVoucherService;
import com.py.shijijingying.service.OrderProductService;
import com.py.shijijingying.service.OrderService;
import com.py.shijijingying.service.ProductService;
import com.py.shijijingying.service.ProductTypeService;
import com.py.shijijingying.service.ShipAddressService;
import com.py.shijijingying.service.VoucherService;
import com.py.shijijingying.utils.Msg;
import com.py.shijijingying.utils.orderNumberUtil;



/*
 * 抽奖
 */
@Controller  
@RequestMapping("/draw")
public class DrawController {
	@Autowired
	private ProductService produceService;
	@Autowired
	private VoucherService voucherService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private BoxinUserService userService;
	@Autowired
	private MyVoucherService myVoucherService;
	@Autowired
	private ShipAddressService shipAddressService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private ConversioneService conversioneService;
	/*
	/*
	 * 点击抽奖
	 */
	@ResponseBody
	@RequestMapping(value="/click")
	public Msg click(@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="shipAddressId",required=false)Integer shipAddressId,
			@RequestParam(value="count",required=false)int count){
		BoxinUser user=userService.getUserById(userId);
		ShipAddress address=shipAddressService.selectByPrimaryKey(shipAddressId);
		if(count>=1){
			Product product =new Product();
			Voucher voucher=new Voucher();
			product.setDraw("1");
			voucher.setVoucherType("1");
			List<Product> products=new ArrayList<Product>();
			products=produceService.selectByExample(product);
			List<Voucher> vouchers=new ArrayList<Voucher>();
			vouchers=voucherService.selectByExample(voucher);
			List<Object> objs=new ArrayList<Object>();
			for(int i=0;i<products.size();i++){
				objs.add(products.get(i));
			}
			for(int j=0;j<vouchers.size();j++){
				objs.add(vouchers.get(j));
			}
			Conversione conversion=new Conversione();
			List<Conversione> convercions=conversioneService.selectByExample(conversion);
			double probability=convercions.get(0).getProbability();	
			int a=objs.size();
			int num=(int) (a/probability);
			for(int k=0;k<num;k++){
				objs.add("下次再来");
			}
			Random random = new Random();
			int n = random.nextInt(objs.size());
			if(objs.get(n) instanceof Voucher){
				Voucher v=(Voucher)objs.get(n);
				MyVoucher my=new MyVoucher();
				my.setUserId(userId);
				my.setVoucherId(v.getVoucherId());
				my.setVoucherOverdue("0");
				my.setVoucherUse("0");
				myVoucherService.insertSelective(my);
			}
			if(objs.get(n) instanceof Product){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Product po=(Product)objs.get(n);
				Order order=new Order();
				order.setUserId(userId);
				order.setOrderNumber(orderNumberUtil.gens());
				order.setCreateDate(format.format(new Date()));
				order.setOrderPhone(address.getShipPhone());
				order.setOrderType("1");//已支付
				order.setShipType("0");//未收货
			    order.setDelivery("0");//未发货
			    order.setEvaluate("0");//未评价
				order.setUserName(user.getUsername());
				order.setShipAddress(address.getShipAddress());
				order.setShipPerson(address.getShipPerson());
				orderService.insertSelective(order);
				OrderProduct ord=new OrderProduct();
				ord.setCreateDate(format.format(new Date()));
				ord.setNum("1");
				ord.setProductName(po.getProductName());
				ord.setImg(po.getProductImg());
				ord.setOrderNumber(order.getOrderNumber());
				ProductType type=productTypeService.selectByPrimaryKey(po.getProductType());
				ord.setRemark(type.getName());
				orderProductService.insertSelective(ord);
				
			}
			user.setDrawnum(count-1);
			userService.updateByPrimaryKey(user);
			return Msg.success(objs.get(n)) ;
		}else{
			return Msg.fail().add("msg", "您不具备抽奖条件");
		}
}
	
	/*
	 * 点击抽奖活动 计算抽奖次数
	 */
	
	@ResponseBody
	@RequestMapping(value="/count")
	public Msg count(@RequestParam(value="userId",required=false)Integer userId){
		BoxinUser user=userService.getUserById(userId);
		if(user.getDrawnum()==null){
			Order order=new Order();
			order.setUserId(userId);
			List<Order> ord=orderService.selectByExample(order);
			Double money=0.0;//该用户累计消费
			for(int i=0;i<ord.size();i++){
				money+=ord.get(i).getOrderPrice();
			}
			int n=sum(money,0);
			return Msg.success(n);
		}else{
			return Msg.success(user.getDrawnum());
		}
		
	}
	
	public int sum(Double money,int num){
		Double sum=new Double("10000.0");
		if(money.compareTo(sum)<0){
			return num;
		}else{
			return sum(money-sum,num+1);
		}
	}
}
