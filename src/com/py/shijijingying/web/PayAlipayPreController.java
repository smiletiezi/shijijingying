package com.py.shijijingying.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransOrderQueryModel;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.py.shijijingying.entity.BoxinUser;
import com.py.shijijingying.entity.Conversione;
import com.py.shijijingying.entity.IntegralDetail;
import com.py.shijijingying.entity.Message;
import com.py.shijijingying.entity.Order;
import com.py.shijijingying.entity.OrderProduct;
import com.py.shijijingying.entity.TradingRecord;
import com.py.shijijingying.service.BoxinUserService;
import com.py.shijijingying.service.ConversioneService;
import com.py.shijijingying.service.IntegralDetailService;
import com.py.shijijingying.service.MessageService;
import com.py.shijijingying.service.OrderProductService;
import com.py.shijijingying.service.OrderService;
import com.py.shijijingying.service.ProductService;
import com.py.shijijingying.service.ProductTypeService;
import com.py.shijijingying.service.TradingRecordService;
import com.py.shijijingying.utils.AlipayConfig;
import com.py.shijijingying.utils.AlipayConfigOne;
import com.py.shijijingying.utils.CommonUtil;
import com.py.shijijingying.utils.Jdpush;
import com.py.shijijingying.utils.Msg;
import com.py.shijijingying.utils.SMSBean;

import cn.jpush.api.push.model.audience.Audience;



@Controller
@RequestMapping("/alipay")
public class PayAlipayPreController {
	@Autowired  
	 private TradingRecordService tradingRecordService;
	@Autowired  
	 private BoxinUserService userService;
	@Autowired  
	 private MessageService messageService;
	@Autowired  
	 private OrderService orderService;
	@Autowired  
	 private ConversioneService conversionService;
	@Autowired
	private IntegralDetailService integralDetailService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderProductService orderProductService;
	@Autowired
	private ProductTypeService productTypeService;
	

	
	/**创建订单接口
	*@param phonenum      充值人
	*@param tradeMoney    充值money(RMB)  
	*@throws AlipayApiException  ModelAndView
	 * @throws IOException 
	 */
	 @ResponseBody
	 @RequestMapping(value="/api/alipay/createOrder")
	 public Msg alipay(HttpServletRequest request) throws AlipayApiException, IOException{
		/*double tradeMoney=Double.parseDouble(request.getParameter("tradeMoney"));
		int orderId=Integer.parseInt(request.getParameter("orderId"));*/
		double tradeMoney = 0d;
		try {
			 tradeMoney = Double.parseDouble(request.getParameter("tradeMoney"));
		} catch (Exception e) {
			 tradeMoney = 0d;
		}
		int orderId = 0;
		try {
			orderId = Integer.parseInt(request.getParameter("orderId"));
		} catch (Exception e) {
			orderId = 0;
		}
	    Msg msg = new Msg();
	    String orderStr = "";
	   Order order=orderService.selectByPrimaryKey(orderId);
	   String belong=order.getBelong();
	   try{
	   //判断订单是哪家公司的
	   if(belong.equals("1")){
		   //总公司
		     //实例化客户端  
	        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
	        
	        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay 
	        AlipayTradeAppPayRequest ali_request = new AlipayTradeAppPayRequest();  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。  
	        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();  
	        model.setBody("支付宝支付"+tradeMoney +"元");                        							//商品信息
	        model.setSubject("支付宝支付");                  												//商品名称
	        model.setOutTradeNo(sdf.format(new Date())+String.valueOf(orderId));          			//商户订单号(自动生成)
	        model.setTimeoutExpress("30m");   															//交易超时时间
	        model.setTotalAmount(String.valueOf(tradeMoney));         									//支付金额
	        model.setProductCode("QUICK_MSECURITY_PAY");         										//销售产品码
	        ali_request.setBizModel(model);  
	        ali_request.setNotifyUrl(AlipayConfig.notify_url);          								//回调地址  
	        
	       
	        AlipayTradeAppPayResponse response = client.sdkExecute(ali_request);  
	        orderStr = response.getBody();
	        System.err.println(orderStr);                                								//就是orderString 可以直接给客户端请求，无需再做处理。  
	       
	   }else{
		   //子公司
		   //实例化客户端  
	        AlipayClient client = new DefaultAlipayClient(AlipayConfigOne.URL, AlipayConfigOne.APPID, AlipayConfigOne.RSA_PRIVATE_KEY, AlipayConfigOne.FORMAT, AlipayConfigOne.CHARSET, AlipayConfigOne.ALIPAY_PUBLIC_KEY,AlipayConfigOne.SIGNTYPE);
	        
	        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay 
	        AlipayTradeAppPayRequest ali_request = new AlipayTradeAppPayRequest();  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。  
	        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();  
	        model.setBody("支付宝支付"+tradeMoney +"元");                        							//商品信息
	        model.setSubject("支付宝支付");                  												//商品名称
	        model.setOutTradeNo(sdf.format(new Date())+String.valueOf(orderId));          			//商户订单号(自动生成)
	        model.setTimeoutExpress("30m");   															//交易超时时间
	        model.setTotalAmount(String.valueOf(tradeMoney));         									//支付金额
	        model.setProductCode("QUICK_MSECURITY_PAY");         										//销售产品码
	        ali_request.setBizModel(model);  
	        ali_request.setNotifyUrl(AlipayConfigOne.notify_url);     //回调地址  
	        
	       
	        AlipayTradeAppPayResponse response = client.sdkExecute(ali_request);  
	        orderStr = response.getBody();
	        System.err.println(orderStr);             
	   }
	      
	   
	        msg.setCode(100);
	        msg.setMsg("处理成功");
	        msg.add("result", orderStr);
	        }catch(Exception e){
	       	    msg.setCode(200);
		        msg.setMsg("处理失败");
		        msg.add("result", orderStr);
	        }
	    return msg;
	}
	 
	 /**
	  * 支付宝支付成功后.回调该接口
	  * @param request
	  * @return
	  * @throws IOException
	  */  
	 @ResponseBody
	 @RequestMapping(value="/alipayNotify")
	 public String notify(HttpServletRequest request,HttpServletResponse response) throws IOException {  
		 System.out.println("进来了");
		//获取支付宝POST过来反馈信息
		 Map<String,String> params = new HashMap<String,String>();
		 Map<String,String[]> requestParams = request.getParameterMap();
		 for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
		     String name = (String) iter.next();
		     String[] values = (String[]) requestParams.get(name);
		     String valueStr = "";
		     for (int i = 0; i < values.length; i++) {
		         valueStr = (i == values.length - 1) ? valueStr + values[i]
		                 : valueStr + values[i] + ",";
		     }
		     //乱码解决，这段代码在出现乱码时使用
		     //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
		     params.put(name, valueStr);
		 }
			String out_trade_no = request.getParameter("out_trade_no");//订单表id
			String tradeStatus = request.getParameter("trade_status");//交易状态
			String transaction_id = request.getParameter("transaction_id");//支付宝交易流水单号
			boolean signVerified = false;
			try {
				signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,AlipayConfig.SIGNTYPE);
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
	     if (signVerified) {    //验签通过   
	         if(tradeStatus.equals("TRADE_SUCCESS")) {    //只处理支付成功的订单: 修改交易表状态,支付成功
	        	 System.out.println("验签通过");
	        	 String orderId=out_trade_no.substring(17);
	        	 Order order=orderService.selectByPrimaryKey(Integer.parseInt(orderId));
	        	 //计算积分
	        	 Conversione conversion =new Conversione();
	        	 List<Conversione> cons=conversionService.selectByExample(conversion);
	        	 Double voucher=cons.get(0).getVoucher();//积分兑换率
	  
	        	 BoxinUser user = userService.getUserById(order.getUserId());
	        	 int personal=user.getPersonal();//我的积分
	        	
	        	 Double num=voucher*order.getOrderPrice();
	        	 int numb=num.intValue();
	        	 user.setPersonal(personal+numb);
	        	 user.setUserbalance(user.getUserbalance() + order.getOrderPrice());
	        	 userService.updateByPrimaryKey(user);
	        	 //增加积分明细
	        	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        	 IntegralDetail detail=new IntegralDetail();
	        			 detail.setDate(format.format(new Date()));
	        			 detail.setDetailType("1");
	        			 detail.setPersonal(numb);
	        			 detail.setUserId(order.getUserId());
	        			 integralDetailService.insertSelective(detail);		 
	        	 order.setOrderType("1");
	        	 order.setUpdateTime(transaction_id);
	        	 OrderProduct ord=new OrderProduct();
	        	 ord.setOrderNumber(order.getOrderNumber());
	        	 List<OrderProduct> products=orderProductService.selectByExample(ord);
	 			for(int i=0;i<products.size();i++){
	 				products.get(i).setType("1");
	 				orderProductService.updateByPrimaryKeySelective(products.get(i));
	 			}
//	        	 if(user != null) {
//	    	             	try {
//	    	             		JpushUtil.pushToAliasMessage("您使用支付宝支付"+order.getOrderPrice()+"元已完成",user.getUserId().toString());
//	    	 				} catch (Exception e) {
//	    	 					
//	    	 				}
//	             }
	        	 
	        	 int returnResult =orderService.updateByPrimaryKey(order);
	        	 if(returnResult > 0){
	                  return "success";
	             }else{
	                  return "fail";
	             }
	         }else{
	             return "fail";
	         }
	     } else {  //验签不通过   
	         System.err.println("验签失败");
	         return "fail";
	     }
	 }
	 
	 /**
	  * 支付宝支付成功后.回调该接口
	  * @param request
	  * @return
	  * @throws IOException
	  */  
	 @ResponseBody
	 @RequestMapping(value="/alipayNotifyOne")
	 public String notifyOne(HttpServletRequest request,HttpServletResponse response) throws IOException {  
		 System.out.println("进来了");
		//获取支付宝POST过来反馈信息
		 Map<String,String> params = new HashMap<String,String>();
		 Map<String,String[]> requestParams = request.getParameterMap();
		 for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
		     String name = (String) iter.next();
		     String[] values = (String[]) requestParams.get(name);
		     String valueStr = "";
		     for (int i = 0; i < values.length; i++) {
		         valueStr = (i == values.length - 1) ? valueStr + values[i]
		                 : valueStr + values[i] + ",";
		     }
		     //乱码解决，这段代码在出现乱码时使用
		     //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
		     params.put(name, valueStr);
		 }
			String out_trade_no = request.getParameter("out_trade_no");//订单表id
			String tradeStatus = request.getParameter("trade_status");//交易状态
			String transaction_id = request.getParameter("transaction_id");//支付宝交易流水单号
			boolean signVerified = false;
			try {
				signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfigOne.ALIPAY_PUBLIC_KEY, AlipayConfigOne.CHARSET,AlipayConfigOne.SIGNTYPE);
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
	     if (signVerified) {    //验签通过   
	         if(tradeStatus.equals("TRADE_SUCCESS")) {    //只处理支付成功的订单: 修改交易表状态,支付成功
	        	 System.out.println("验签通过");
	        	 String orderId=out_trade_no.substring(17);
	        	 Order order=orderService.selectByPrimaryKey(Integer.parseInt(orderId));
	        	 //计算积分
	        	 Conversione conversion =new Conversione();
	        	 List<Conversione> cons=conversionService.selectByExample(conversion);
	        	 Double voucher=cons.get(0).getVoucher();//积分兑换率
	  
	        	 BoxinUser user = userService.getUserById(order.getUserId());
	        	 int personal=user.getPersonal();//我的积分
	        	
	        	 Double num=voucher*order.getOrderPrice();
	        	 int numb=num.intValue();
	        	 user.setPersonal(personal+numb);
	        	 user.setUserbalance(user.getUserbalance() + order.getOrderPrice());
	        	 userService.updateByPrimaryKey(user);
	        	 //增加积分明细
	        	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        	 IntegralDetail detail=new IntegralDetail();
	        			 detail.setDate(format.format(new Date()));
	        			 detail.setDetailType("1");
	        			 detail.setPersonal(numb);
	        			 detail.setUserId(order.getUserId());
	        			 integralDetailService.insertSelective(detail);		 
	        	 order.setOrderType("1");
	        	 order.setUpdateTime(transaction_id);
	        	 OrderProduct ord=new OrderProduct();
	        	 ord.setOrderNumber(order.getOrderNumber());
	        	 List<OrderProduct> products=orderProductService.selectByExample(ord);
	 			for(int i=0;i<products.size();i++){
	 				products.get(i).setType("1");
	 				orderProductService.updateByPrimaryKeySelective(products.get(i));
	 			}
//	        	 if(user != null) {
//	    	             	try {
//	    	             		JpushUtil.pushToAliasMessage("您使用支付宝支付"+order.getOrderPrice()+"元已完成",user.getUserId().toString());
//	    	 				} catch (Exception e) {
//	    	 					
//	    	 				}
//	             }
	        	 
	        	 int returnResult =orderService.updateByPrimaryKey(order);
	        	 if(returnResult > 0){
	                  return "success";
	             }else{
	                  return "fail";
	             }
	         }else{
	             return "fail";
	         }
	     } else {  //验签不通过   
	         System.err.println("验签失败");
	         return "fail";
	     }
	 }
	
	
	 /**
	  * alipay.fund.trans.toaccount.transfer(单笔转账到支付宝账户接口)
	  * 参考文档：https://docs.open.alipay.com/api_28/alipay.fund.trans.toaccount.transfer/
	  * @author wb-wly251833
	  *
	  */
	 @ResponseBody
	 @RequestMapping(value="api/alipayFundTransToaccountTransfer",method={RequestMethod.POST})
	 public Msg alipayFundTransToaccountTransfer( @RequestParam("phonenum")String phonenum,
			 @RequestParam("tradeMoney")Double tradeMoney,
			 @RequestParam("account")String account) throws AlipayApiException{
		 
		 	if(account == null || "".equals(account.trim())) {
		 		return Msg.fail().add("msg", "请填写支付宝账号");
		 	}
		 	if(tradeMoney == null || tradeMoney == 0) {
		 		return Msg.fail().add("msg", "请填写提现金额");
		 	}
		 
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gateway_url, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
			AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
			AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
			
			SMSBean smsBean = (SMSBean) CommonUtil.MSG_MAP.get(phonenum);
			BoxinUser user = userService.getUserById(smsBean.getId());
			
			if(user.getUserbalance() < tradeMoney) {
				return Msg.fail().add("msg", "您的余额只有"+user.getUserbalance()+"元");
			}
			
	        TradingRecord tradingRecord = new TradingRecord();
	        tradingRecord.setTradingRecordUserId(smsBean.getId());
	        tradingRecord.setTradingRecordType(1);
	        tradingRecord.setTradingRecordPrice(tradeMoney);
	        tradingRecord.setTradingRecordDes("支付宝转账"+tradeMoney +"元");
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        tradingRecord.setTradingRecordTime(format.format(new Date()));
	        tradingRecord.setTradingRecordStatus(0);
	        tradingRecordService.insertSelective(tradingRecord);
			
			//商户转账唯一订单号
			model.setOutBizNo(String.valueOf(tradingRecord.getTradingRecordId()));
			//收款方账户类型。 
			//1、ALIPAY_USERID：pid ,以2088开头的16位纯数字组成。 
			//2、ALIPAY_LOGONID：支付宝登录号(邮箱或手机号)
			model.setPayeeType("ALIPAY_LOGONID");
			//收款方账户。与payee_type配合使用。付款方和收款方不能是同一个账户。
			model.setPayeeAccount(account);
			//测试金额必须大于等于0.1，只支持2位小数，小数点前最大支持13位
			if(AlipayConfig.falg) {
				model.setAmount(String.valueOf(tradeMoney));
			}else {
				model.setAmount("0.1");
			}
			//当付款方为企业账户且转账金额达到（大于等于）50000元，remark不能为空。
			model.setRemark("提现");
			request.setBizModel(model);
			
			try {
				AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
				System.out.println(response.getBody());	
				if(!response.isSuccess()){
//					user.setUserBalance(user.getUserBalance() + tradeMoney);
//					userService.updateByPrimaryKeySelective(user);
//					tradingRecord.setTradingRecordStatus(0);
//					tradingRecordService.updateByPrimaryKeySelective(tradingRecord);
					return Msg.fail().add("msg", response.getSubMsg());
				}
			} catch (Exception e) {
				return Msg.fail().add("msg", "提现失败");
			}
			
			TradingRecord selectByPrimaryKey = tradingRecordService.selectByPrimaryKey(tradingRecord.getTradingRecordId());
			selectByPrimaryKey.setTradingRecordStatus(1);
			
			user.setUserbalance(user.getUserbalance() - tradeMoney);
			try {
				userService.updateByPrimaryKey(user);
				tradingRecordService.updateByPrimaryKeySelective(selectByPrimaryKey);
			} catch (Exception e) {
			}
			
            if(user != null) {
            	SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	
            	Message message = new Message();
            	message.setMessageType(2);
            	message.setMessageStaffType(1);
            	message.setMessageContent("您使用支付宝提现的"+tradingRecord.getTradingRecordPrice()+"元已到账");
            	message.setMessageUserId(user.getUserid());
            	message.setMessageTime(format2.format(new Date()));
            	
            	try {
            		int i = messageService.insertSelective(message);
            		if(i != 0){
            			Map<String, String> parm =new HashMap<String, String>();
                    	parm.put("title","缴费信息");
                    	parm.put("body","您使用支付宝提现的"+tradingRecord.getTradingRecordPrice()+"元已到账");
                    	try {
                    		if(user.getUserpushtype() == 0) { //调用ios的
                    			Jdpush.jpushIOS(parm, Audience.registrationId(user.getUserpushregistrationid()));
                    		}else {//然后调用安卓的
                    			Jdpush.jpushAndroid(parm, Audience.registrationId(user.getUserpushregistrationid()));
                    		}
        				} catch (Exception e) {
        				}
            		}
            	} catch (Exception e) {
            	}
            }
			
			return Msg.success();
	 }
	
	 /**
	  * alipay.fund.trans.order.query(查询转账订单接口)
	  * 商户可通过该接口查询转账订单的状态、支付时间等相关信息，主要应用于B2C转账订单查询的场景
	  * 普通单笔转账到支付宝用这个没意义，查不到转账金额。order_fee这个参数 是对银行卡才用到的，如果转到支付宝余额的话 是为0
	  * @author wb-wly251833
	  *
	  */
 	public void alipayFundTransOrderQuery() throws AlipayApiException {
 		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gateway_url, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
 		AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
 		AlipayFundTransOrderQueryModel model = new AlipayFundTransOrderQueryModel();
 		//	商户转账唯一订单号
//	 		model.setOutBizNo("2017081015423214234321");
 		//支付宝转账单据号：和商户转账唯一订单号不能同时为空。二选一传入
 	   	model.setOrderId("20170810110070001500580000006005");
 		request.setBizModel(model);
 		AlipayFundTransOrderQueryResponse response = alipayClient.execute(request);
 		System.out.println(response.getBody());
 		
 		if(response.isSuccess()){
 		System.out.println("调用成功");
 		} else {
 		System.out.println("调用失败");
 		}
 	}
}
