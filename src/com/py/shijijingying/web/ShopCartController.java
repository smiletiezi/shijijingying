package com.py.shijijingying.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.StringUtil;
import com.py.shijijingying.entity.BoxinUser;
import com.py.shijijingying.entity.BxArea;
import com.py.shijijingying.entity.MyVoucher;
import com.py.shijijingying.entity.Order;
import com.py.shijijingying.entity.OrderProduct;
import com.py.shijijingying.entity.Product;
import com.py.shijijingying.entity.ProductDiscount;
import com.py.shijijingying.entity.ProductType;
import com.py.shijijingying.entity.ShopCart;
import com.py.shijijingying.entity.Voucher;
import com.py.shijijingying.service.BoxinUserService;
import com.py.shijijingying.service.BxAreaService;
import com.py.shijijingying.service.ConversioneService;
import com.py.shijijingying.service.IntegralDetailService;
import com.py.shijijingying.service.MyVoucherService;
import com.py.shijijingying.service.OrderProductService;
import com.py.shijijingying.service.OrderService;
import com.py.shijijingying.service.ProductDiscountService;
import com.py.shijijingying.service.ProductService;
import com.py.shijijingying.service.ProductTypeService;
import com.py.shijijingying.service.ShopCartService;
import com.py.shijijingying.service.VoucherService;
import com.py.shijijingying.utils.EmptyUtil;
import com.py.shijijingying.utils.Msg;
import com.py.shijijingying.utils.orderNumberUtil;


@Controller  
@RequestMapping("/shopCart")
public class ShopCartController {
	@Autowired
	private ShopCartService shopCartService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductDiscountService productDiscountService;
	@Autowired  
	 private BoxinUserService userService;
	@Autowired  
	 private OrderService orderService;
	@Autowired  
	 private ConversioneService conversionService;
	@Autowired
	private IntegralDetailService integralDetailService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private MyVoucherService  myVoucherService;
	@Autowired
	private VoucherService  voucherService;
	@Autowired
	private BxAreaService  bxAreaService;
	/*
	 * add加入购物车
	 * @param userId
	 * @param productId
	 * @param shopCode
	 * @param shopRemark
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public Msg add(@RequestParam(value="userId",required=false)Integer userId,
            @RequestParam(value="productId",required=false)Integer productId,
            @RequestParam(value="shopCode",required=false)String shopCode,
            @RequestParam(value="shopRemark",required=false)String shopRemark,
            @RequestParam(value="shopSum",required=false)Integer shopSum){
		ShopCart cart=new ShopCart();
		if(userId!=null){
			cart.setUserId(userId);
		}
		if(productId!=null){
			cart.setProductId(productId);
		}
		cart.setShopCode("0");
		List<ShopCart> carts=shopCartService.selectByExample(cart);
		if(null == carts || carts.size() ==0){
			if (userId != null) {
				cart.setUserId(userId);
			}
			if (productId != null) {
				cart.setProductId(productId);
			}
			if (shopCode != null) {
				cart.setShopCode(shopCode);
			}else{
				cart.setShopCode("0");
			}
			if (shopSum != null) {
				cart.setShopSum(shopSum);
			}else{
				cart.setShopSum(1);
			}
			if (shopRemark != null) {
				cart.setShopRemark(shopRemark);
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			cart.setShopCreateTime(format.format(new Date()));
			shopCartService.insertSelective(cart);
			Msg msg = Msg.success();
	        return msg;
		}else{
			return Msg.fail().add("msg", "该产品已加入购物车");
		}
	}
	
	/*
	 * delete单个或者多个产品
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	public Msg delete(@RequestParam(value="ids",required=false)String ids
            ){
		if(StringUtils.isBlank(ids)){
			return Msg.fail().add("msg", "请选择您要删除的产品");
		}else{
			try{
				shopCartService.deleteByList(ids);
				return Msg.success();
		}catch(Exception e){
				return Msg.fail().add("msg", "删除失败");
			}
		}
		
	}
	
	/*
	 * update购物车里的产品数量
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public Msg update(@RequestParam(value="shopCartId",required=false)Integer shopCartId,
            @RequestParam(value="shopSum",required=false)Integer shopSum
            ){
		ShopCart cart=shopCartService.selectByPrimaryKey(shopCartId);
		
			if(EmptyUtil.isEmpty(cart)){
				return Msg.fail().add("msg", "请选择要更改的产品");
			}else{
			
				if(shopSum !=null){
					cart.setShopSum(shopSum);
				}
				
				try{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					cart.setShopUpdateTime(format.format(new Date()));
					shopCartService.updateByPrimaryKey(cart);
			}catch(Exception e){
					return Msg.fail().add("msg", "修改失败");
				}
			}
		return Msg.success();
	}
	
	/*
	 * 购物车商品list
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(@RequestParam(value="userId",required=false)Integer userId){
		List<ShopCart> carts=new ArrayList<ShopCart>();
		//List<Product> products=new ArrayList<Product>();
		ShopCart cart=new ShopCart();
		cart.setUserId(userId);
		List<Object> obj=new ArrayList<Object>();
		try{
			carts=shopCartService.selectByExample(cart);
			for(int i = 0 ; i < carts.size() ; i++) {
				obj.add("id:"+carts.get(i).getShopCartId());
				obj.add(carts.get(i).getShopSum());
				obj.add(productService.selectByPrimaryKey(carts.get(i).getProductId()));
				}
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
			return Msg.success(obj);
	}
	
	/*
	 * 结算购物车里选择产品支付 改：参数加代金券id
	 */
	@ResponseBody
	@RequestMapping(value="/pay")
	public Msg pay(@RequestParam(value="shopCartIds",required=false)String shopCartIds,
			@RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="nums",required=false)String nums,
			@RequestParam(value="shipAddress",required=false)String shipAddress,
			@RequestParam(value="shipPhone",required=false)String shipPhone,
			@RequestParam(value="shipPerson",required=false)String shipPerson,
			@RequestParam(value="belong",required=false)String belong,
			@RequestParam(value="myVoucherId",required=false)Integer myVoucherId){
		Msg msg=Msg.success();
		if(StringUtils.isBlank(shopCartIds)){
			return Msg.fail().add("msg", "请选择您要支付的产品");
		}else{
			List<ShopCart> carts=new ArrayList<ShopCart>();
			carts=shopCartService.selectByList(shopCartIds);
			double money=0;//订单金额
			List<Double> price=new ArrayList<Double>();//订单产品表中每个产品的结算金额
			double postage=0;//邮费
			int sum=0;
			 List<String> ids=new ArrayList<String>();
		     List<String> ms=Arrays.asList(nums.split(","));
		     List<String> names=new ArrayList<String>();//遍历就结算的产品的产品类型名称
			for(int i = 0 ; i < carts.size() ; i++){
				carts.get(i).setShopSum(Integer.parseInt(ms.get(i)));
				shopCartService.updateByPrimaryKey(carts.get(i));
				sum+=carts.get(i).getShopSum();
				ids.add(carts.get(i).getProductId().toString());
				//ms.add(carts.get(i).getShopSum().toString());
				Product product=productService.selectByPrimaryKey(carts.get(i).getProductId());
				ProductType type=productTypeService.selectByPrimaryKey(product.getProductType());
				names.add(type.getName());
				if(carts.get(i).getShopSum()>=product.getSalesVolume()){//购买数量满足起售数量					
					
//					if(StringUtil.isNotEmpty(product.getProductDiscount().toString())){
//						ProductDiscount discount =productDiscountService.selectByPrimaryKey(product.getProductDiscount());
//						if(carts.get(i).getShopSum()>=discount.getDiscountSum()){
//							double num=discount.getDiscountRemark();
//							money+=num*carts.get(i).getShopSum()*product.getProductSalePrice();
//						}else{
//							money+=carts.get(i).getShopSum()*product.getProductSalePrice();
//						}
//						
//					}else{
//						money+=carts.get(i).getShopSum()*product.getProductSalePrice();
//					}
					if(StringUtil.isNotEmpty(product.getProductDiscount())){
						ProductDiscount pd=new ProductDiscount();
						pd.setDiscountType(product.getProductDiscount());
						pd.setDiscountSum(carts.get(i).getShopSum());
						List<ProductDiscount> pds=productDiscountService.selectByNum(pd);
						if(pds.size()>0){//根据产品折扣类型，产品获取的产品类型优惠不为空
							
							money+=pds.get(0).getDiscountRemark()*carts.get(i).getShopSum()*product.getProductSalePrice();
							price.add(pds.get(0).getDiscountRemark()*carts.get(i).getShopSum()*product.getProductSalePrice());
						}else{
							money+=carts.get(i).getShopSum()*product.getProductSalePrice();
							price.add(carts.get(i).getShopSum()*product.getProductSalePrice());
						}
					}else {
						money+=carts.get(i).getShopSum()*product.getProductSalePrice();
						price.add(carts.get(i).getShopSum()*product.getProductSalePrice());
					}
			
				}else{
					return Msg.fail().add("msg", "商品"+product.getProductName()+"不满足起购数量"); 
				}
				}
			/******************折扣计算后，结算出金额，判断是否点击使用优惠券*******************/
			if(myVoucherId !=null){
				MyVoucher mv=myVoucherService.selectByPrimaryKey(myVoucherId);
				if(mv!=null){
					if(mv.getVoucherOverdue().equals("0")){
						Voucher v=voucherService.selectByPrimaryKey(mv.getVoucherId());
						double voucher=v.getVoucherPar();
						if(money>=Double.parseDouble(v.getVoucherMore())){
							money-=voucher;
						}else{
							return Msg.success("消费金额不足 ，不能使用该代金券");
						}
					}else{
						return Msg.success("代金券已过期");
					}
				}else{
					return Msg.success("代金券不存在");
				}
			}
			
/**********************************************结算出金额 计算邮费****************************/
			BxArea ba=new BxArea();
			ba.setPriceOne(0.0);
			List<BxArea> areas=bxAreaService.selectByExample(ba);
			List<String> as=new ArrayList<String>();
			for(int m=0;m<areas.size();m++) {
				as.add(areas.get(m).getName());
		}
			names.retainAll(as);
			if(names.size()>0) {
				String province=shipAddress.substring(0,2);//收货地址截取省份
				//购买的产品中有消毒剂
				BxArea barea=bxAreaService.selectByName(province);//省份查询
				if(barea==null) {
					//没有找到省份对应的城市 那么选择其他
					BxArea bxarea=bxAreaService.selectByName("其他");
					postage+=bxarea.getPriceOne();
					postage+=bxarea.getBxadd()*sum;
					
				}else {
					//有对应的省份
					postage+=barea.getPriceOne();
					postage+=barea.getBxadd()*sum;
				}
			}else {
				//购买的产品没有消毒剂
				String province=shipAddress.substring(0,2);//收货地址截取省份
				BxArea barea=bxAreaService.selectByName(province);//省份查询
				if(barea==null) {
					//没有找到省份对应的城市 那么选择其他
					BxArea bxarea=bxAreaService.selectByName("其他");
					postage+=bxarea.getPriceOne();
					postage+=bxarea.getBxadd()*sum;
				}else {
					String post=barea.getPostage();//取到对应省份是否包邮 0包邮，1不包邮
					if(post.equals("0")) {
						//包邮
						if(money>=barea.getMore()) {
							postage+=0;
						}else {
							postage+=barea.getPriceOne();
							postage+=barea.getBxadd()*sum;
						}
					}else {
						postage+=barea.getPriceOne();
						postage+=barea.getBxadd()*sum;
					}
				}
			}
			
			/******************结算完成 产生订单********************/
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Order order=new Order();
	        BoxinUser user=userService.getUserById(userId);
	        order.setOrderNumber(orderNumberUtil.gens());
	        order.setOrderPrice(money);
	        order.setOrderType("0");//未支付
	        order.setShipType("0");//未收货
	        order.setDelivery("0");//未发货
	        order.setEvaluate("0");//未评价
	        order.setUserName(user.getUserbusinessname());
	        order.setUserId(userId);
	        order.setShipAddress(shipAddress);
	        order.setOrderPhone(shipPhone);
	        order.setShipPerson(shipPerson);
	        order.setCreateDate(format.format(new Date()));
	        order.setDeleteType("0");
	        order.setNum(sum);
	        order.setBelong(belong);
	        order.setPostage(postage);
	        orderService.insertSelective(order);
	        //订单商品详情 
	        OrderProduct ord=new OrderProduct();
	        Product pro=new Product();
	        ord.setOrderNumber(order.getOrderNumber());
	        ord.setUserId(userId);
	        ord.setUserName(user.getUserbusinessname());
	        ord.setCreateDate(format.format(new Date()));
	       
	        for(int j=0;j<ids.size();j++){
	        	pro=productService.selectByPrimaryKey(Integer.parseInt(ids.get(j)));
	        	ord.setProductName(pro.getProductName());
	        	ord.setImg(pro.getProductImg());
	        	ord.setPrice(price.get(j));
	        	ord.setSalePrice(pro.getProductSalePrice());
	        	ord.setSize(pro.getProductSize());
	        	ord.setPercentage(pro.getPercentage());
	        	ProductType type=productTypeService.selectByPrimaryKey(pro.getProductType());
	        	ord.setNum(ms.get(j));
	        	ord.setRemark(type.getName());
	        	ord.setImg(pro.getProductImg());
	        	orderProductService.insertSelective(ord);
	        }
	        
	        /******************订单创建完成***************************/
	        msg.add("orderId",order.getOrderId());
			msg.add("orderNumber",order.getOrderNumber());
			msg.add("money", money);//金额
			msg.add("postage", postage);//邮费
			return msg;
		}
		
	}
	
	/*
	 * 单独购买时结算
	 */
	@ResponseBody
	@RequestMapping(value="/payOne")
	public Msg payOne(@RequestParam(value="productId",required=false)Integer productId,
	        @RequestParam(value="num",required=false)Integer num,
	        @RequestParam(value="userId",required=false)Integer userId,
			@RequestParam(value="shipAddress",required=false)String shipAddress,
			@RequestParam(value="shipPhone",required=false)String shipPhone,
			@RequestParam(value="shipPerson",required=false)String shipPerson,
			@RequestParam(value="belong",required=false)String belong,
			@RequestParam(value="myVoucherId",required=false)Integer myVoucherId){
		Msg msg=Msg.success();
		if(productId==null){
			 return Msg.fail().add("msg", "请选择您要结算的产品");
		}
		if(num==null){
			return Msg.fail().add("msg", "请选择您要结算的产品数量");
		}
		Product product=productService.selectByPrimaryKey(productId);
		double money=0;//结算金额
		double postage=0;//邮费
		if(num>=product.getSalesVolume()){
//			if(product.getProductDiscount()!=null){
//				ProductDiscount discount =productDiscountService.selectByPrimaryKey(product.getProductDiscount());
//				double sum=discount.getDiscountRemark();
//				money+=sum*num*product.getProductSalePrice();
//			}else{
//				money+=num*product.getProductSalePrice();
//			}
			if(StringUtil.isNotEmpty(product.getProductDiscount())){
				ProductDiscount pd=new ProductDiscount();
				pd.setDiscountType(product.getProductDiscount());
				pd.setDiscountSum(num);
				List<ProductDiscount> pds=productDiscountService.selectByNum(pd);
				if(pds.size()>0){//根据产品类型，产品获取的产品类型优惠不为空
					money+=pds.get(0).getDiscountRemark()*num*product.getProductSalePrice();
				}else{
					money+=num*product.getProductSalePrice();
				}
			}else {
				money+=num*product.getProductSalePrice();
			}
			/******************折扣计算后，结算出金额，判断是否点击使用优惠券*******************/
			if(myVoucherId!=null){
				MyVoucher mv=myVoucherService.selectByPrimaryKey(myVoucherId);
				if(mv!=null){
					if(mv.getVoucherOverdue().equals("0")){
						Voucher v=voucherService.selectByPrimaryKey(mv.getVoucherId());
						double voucher=v.getVoucherPar();
						if(money>=Double.parseDouble(v.getVoucherMore())){
							money-=voucher;
							mv.setVoucherUse("1");
							myVoucherService.updateByPrimaryKeySelective(mv);
						}else{
							return Msg.success("消费金额不足 ，不能使用该代金券");
						}
					}else{
						return Msg.success("代金券已过期");
					}
				}else{
					return Msg.success("代金券不存在");
				}
			}
			
	/**************************************************计算邮费**********************************/		
			
			ProductType types=productTypeService.selectByPrimaryKey(product.getProductType());
			String typename=types.getName();
			BxArea ba=new BxArea();
			ba.setPriceOne(0.0);
			List<BxArea> areas=bxAreaService.selectByExample(ba);
			List<String> as=new ArrayList<String>();
			for(int m=0;m<areas.size();m++) {
				as.add(areas.get(m).getName());
		}
			if(as.contains(typename)) {
				//购买的产品 有不包邮类型
				String province=shipAddress.substring(0,2);//收货地址截取省份
				//购买的产品中有消毒剂
				BxArea barea=bxAreaService.selectByName(province);//省份查询
				if(barea==null) {
					//没有找到省份对应的城市 那么选择其他
					BxArea bxarea=bxAreaService.selectByName("其他");
					postage+=bxarea.getPriceOne();
					postage+=bxarea.getBxadd()*num;
					
				}else {
					//有对应的省份
					postage+=barea.getPriceOne();
					postage+=barea.getBxadd()*num;
				}
			}else {
				//购买的产品 没有有不包邮类型
				String province=shipAddress.substring(0,2);//收货地址截取省份
				BxArea barea=bxAreaService.selectByName(province);//省份查询
				if(barea==null) {
					//没有找到省份对应的城市 那么选择其他
					BxArea bxarea=bxAreaService.selectByName("其他");
					postage+=bxarea.getPriceOne();
					postage+=bxarea.getBxadd()*num;
				}else {
					String post=barea.getPostage();//取到对应省份是否包邮 0包邮，1不包邮
					if(post.equals("0")) {
						//包邮
						if(money>=barea.getMore()) {
							postage+=0;
						}else {
							postage+=barea.getPriceOne();
							postage+=barea.getBxadd()*num;
						}
					}else {
						postage+=barea.getPriceOne();
						postage+=barea.getBxadd()*num;
					}
				}
			}
			
			
			
			
			
			
			
			
			/******************结算完成 产生订单********************/
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Order order=new Order();
	        BoxinUser user=userService.getUserById(userId);
	        order.setOrderNumber(orderNumberUtil.gens());
	        order.setOrderPrice(money);
	        order.setOrderType("0");//未支付
	        order.setShipType("0");//未收货
	        order.setDelivery("0");//未发货
	        order.setEvaluate("0");//未评价
	        order.setUserName(user.getUserbusinessname());
	        order.setUserId(userId);
	        order.setShipAddress(shipAddress);
	        order.setOrderPhone(shipPhone);
	        order.setShipPerson(shipPerson);
	        order.setCreateDate(format.format(new Date()));
	        order.setDeleteType("0");
	        order.setNum(num);
	        order.setBelong(belong);
	        order.setPostage(postage);
	        orderService.insertSelective(order);
	        //订单商品详情 
	        OrderProduct ord=new OrderProduct();
	        ord.setOrderNumber(order.getOrderNumber());
	        ord.setUserId(userId);
	        ord.setUserName(user.getUserbusinessname());
	        ord.setCreateDate(format.format(new Date()));
	        ord.setProductName(product.getProductName());
	        ord.setPrice(money);
	        ord.setSalePrice(product.getProductSalePrice());
	        ord.setSize(product.getProductSize());
	        ord.setPercentage(product.getPercentage());
	        ProductType type=productTypeService.selectByPrimaryKey(product.getProductType());
	        ord.setNum(num.toString());
	        ord.setRemark(type.getName());
	        ord.setImg(product.getProductImg());
	        orderProductService.insertSelective(ord);
	        
	        
	        /******************订单创建完成***************************/
	        msg.add("orderId",order.getOrderId());
	        msg.add("orderNumber",order.getOrderNumber());
			msg.add("money", money);
			msg.add("postage", postage);//邮费
			return msg;
		}else{
			return Msg.fail().add("msg", "购买数量不符合该产品的起售数量");
		}
		
		
		
	}
	/*
	 * 临时接口 确认付款
	 */
	@ResponseBody
	@RequestMapping(value="/payL")
	public Msg payL(@RequestParam("orderId")Integer orderId){
		Order order=orderService.selectByPrimaryKey(orderId);
		try{
			order.setOrderType("1");
			orderService.updateByPrimaryKey(order);
			OrderProduct ord=new OrderProduct();
       	 ord.setOrderNumber(order.getOrderNumber());
       	 List<OrderProduct> products=orderProductService.selectByExample(ord);
			for(int i=0;i<products.size();i++){
				products.get(i).setType("1");
				orderProductService.updateByPrimaryKeySelective(products.get(i));
			}
			return Msg.success();
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
	}
	
	
	
	/*
	 * 判断所要支付的产品中是否有不满足起购数量的
	 */
	
	public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
	    if(a.size() != b.size())
	        return false;
	    for(int i=0;i<a.size();i++){
	        if( Integer.parseInt(a.get(i).toString())<Integer.parseInt(b.get(i).toString())){
	            return false;
	        }
	    }
	    return true;
	
}
	
}
