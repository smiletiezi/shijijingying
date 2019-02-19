package com.py.shijijingying.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.py.shijijingying.entity.BoxinUser;
import com.py.shijijingying.entity.Exchange;
import com.py.shijijingying.entity.IntegralDetail;
import com.py.shijijingying.entity.MyVoucher;
import com.py.shijijingying.entity.Order;
import com.py.shijijingying.entity.OrderProduct;
import com.py.shijijingying.entity.Product;
import com.py.shijijingying.entity.ProductType;
import com.py.shijijingying.entity.Voucher;
import com.py.shijijingying.service.BoxinUserService;
import com.py.shijijingying.service.ExchangeService;
import com.py.shijijingying.service.IntegralDetailService;
import com.py.shijijingying.service.MyVoucherService;
import com.py.shijijingying.service.OrderProductService;
import com.py.shijijingying.service.OrderService;
import com.py.shijijingying.service.ProductService;
import com.py.shijijingying.service.ProductTypeService;
import com.py.shijijingying.service.VoucherService;
import com.py.shijijingying.utils.EmptyUtil;
import com.py.shijijingying.utils.Msg;
import com.py.shijijingying.utils.orderNumberUtil;

@Controller  
@RequestMapping("/exchange")
public class ExchangeController {
	@Autowired
	private ExchangeService exchangeService;
	@Autowired
	private ProductService productService;
	@Autowired
	private BoxinUserService userService;
	@Autowired
	private IntegralDetailService integralDetailService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private VoucherService voucherService;
	@Autowired
	private MyVoucherService myVoucherService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private OrderProductService orderProductService;
	
	/*
	 * 积分兑换
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	private Msg add(@RequestParam(value="productId",required=false)Integer productId,
			@RequestParam(value="voucherId",required=false)Integer voucherId,
			@RequestParam(value="userId",required=false)Integer userId,
	        @RequestParam(value="shipAddress",required=false)String shipAddress,
	        @RequestParam(value="orderPhone",required=false)String orderPhone,
	        @RequestParam(value="shipPerson",required=false)String shipPerson){
		BoxinUser u=new BoxinUser();
		u.setUserid(userId);
		BoxinUser user=userService.selectByPrimary(u);
		int personalu=user.getPersonal();//个人积分
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(productId!=null){
			Product product=productService.selectByPrimaryKey(productId);
			int personalp=product.getIntegration();//兑换该商品所需积分
			if(personalu>=personalp){
				int num=personalu-personalp;
				user.setPersonal(num);
				userService.updateByPrimaryKey(user);
				IntegralDetail detail=new IntegralDetail();//增加积分明细
				detail.setDate(format.format(new Date()));
				detail.setUserId(userId);
				detail.setDetailType("0");
				detail.setPersonal(personalp);
				integralDetailService.insertSelective(detail);
				Exchange change=new Exchange();//增加积分兑换明细
				change.setDate(format.format(new Date()));
				change.setPersonal(personalp);
				change.setUserId(userId);
				change.setProduct(product.getProductName());
				exchangeService.insert(change);
				Order order=new Order();//增加订单
				order.setCreateDate(format.format(new Date()));
				order.setOrderNumber(orderNumberUtil.gens());
				order.setOrderPhone(orderPhone);
				order.setOrderType("1");//已支付
				order.setShipType("0");//未收货
			    order.setDelivery("0");//未发货
			    order.setEvaluate("0");//未评价
				order.setUserId(userId);
				order.setUserName(user.getUsername());
				order.setShipAddress(shipAddress);
				order.setShipPerson(shipPerson);
				order.setNum(1);
				orderService.insertSelective(order);
				//增加商品订单关联
				OrderProduct ord=new OrderProduct();
				ord.setCreateDate(format.format(new Date()));
				ord.setNum("1");
				ord.setProductName(product.getProductName());
				ord.setImg(product.getProductImg());
				ord.setOrderNumber(order.getOrderNumber());
				ProductType type=productTypeService.selectByPrimaryKey(product.getProductType());
				ord.setRemark(type.getName());
				orderProductService.insertSelective(ord);
				return Msg.success();
			}else{
				return Msg.fail().add("msg", "积分不够");
			}
			
		}else if(voucherId!=null){
			Voucher voucher=voucherService.selectByPrimaryKey(voucherId);
			int integration=voucher.getIntegration();//兑换改代金券所需积分
			if(personalu>=integration){
				int num=personalu-integration;
				user.setPersonal(num);
				userService.updateByPrimaryKey(user);
				IntegralDetail detail=new IntegralDetail();//增加积分明细
				detail.setDate(format.format(new Date()));
				detail.setUserId(userId);
				detail.setDetailType("0");
				detail.setPersonal(integration);
				integralDetailService.insertSelective(detail);
				Exchange change=new Exchange();//增加积分兑换明细
				change.setDate(format.format(new Date()));
				change.setPersonal(integration);
				change.setUserId(userId);
				change.setProduct(voucher.getVoucherName());
				exchangeService.insert(change);
				MyVoucher myVoucher=new MyVoucher();
				myVoucher.setUserId(userId);
				myVoucher.setVoucherId(voucherId);
				myVoucher.setVoucherUse("0");
				myVoucher.setVoucherOverdue("0");
				myVoucherService.insert(myVoucher);
				return Msg.success();
			}else{
				return Msg.fail().add("msg", "积分不够");
			}
			
		}else{
			return Msg.fail().add("msg", "处理失败");
		}
	
	
		
		
	}
	/*
	 * 积分兑换明细列表
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(@RequestParam(value="userId",required=false)Integer userId){
		Exchange change=new Exchange();
		change.setUserId(userId);
		try{
		List<Exchange> changes=exchangeService.selectByExample(change);
		return Msg.success(changes);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public Msg delete(@RequestParam(value="exchangeId",required=false)Integer exchangeId){
		Exchange change=exchangeService.selectByPrimaryKey(exchangeId);
		if(EmptyUtil.isEmpty(change)){
			return Msg.fail().add("msg", "请选择你要删除的明细");
		}else{
			exchangeService.deleteByPrimaryKey(exchangeId);
			return Msg.success();
		}
	}
}
