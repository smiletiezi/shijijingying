package com.py.shijijingying.web;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.py.shijijingying.entity.BoxinUser;
import com.py.shijijingying.entity.Conversione;
import com.py.shijijingying.entity.IntegralDetail;
import com.py.shijijingying.entity.Order;
import com.py.shijijingying.entity.OrderProduct;
import com.py.shijijingying.service.BoxinUserService;
import com.py.shijijingying.service.ConversioneService;
import com.py.shijijingying.service.IntegralDetailService;
import com.py.shijijingying.service.MessageService;
import com.py.shijijingying.service.OrderProductService;
import com.py.shijijingying.service.OrderService;
import com.py.shijijingying.service.ProductService;
import com.py.shijijingying.service.ProductTypeService;
import com.py.shijijingying.service.TradingRecordService;
import com.py.shijijingying.utils.CommonUtil;
import com.py.shijijingying.utils.ConfigUtil;
import com.py.shijijingying.utils.ConfigUtilOne;
import com.py.shijijingying.utils.PayCommonUtil;
import com.py.shijijingying.utils.XMLUtil;

@Controller
@RequestMapping("/wxpay")
public class PayWxPreController {
	
	@Autowired
	TradingRecordService tradingRecordService;
	@Autowired
	BoxinUserService userService;
	@Autowired
	MessageService messageService;
	@Autowired
	OrderService orderService;
	@Resource  
	 private ConversioneService conversioneService;
	@Resource
	private IntegralDetailService integralDetailService;
	@Resource
	private ProductService productService;
	@Resource
	private OrderProductService orderProductService;
	@Resource
	private ProductTypeService productTypeService;
	/**
     * 微信统一下单接口
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value="/api/wx/createOrder",method={RequestMethod.POST,RequestMethod.GET})
    public Map<String, Object> wxPrePay(HttpServletRequest request,HttpServletResponse response,
    		@RequestParam("tradeMoney")BigDecimal tradeMoney,
    		@RequestParam("orderId")Integer orderId
	        ){
    	
    	
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
        Order order=orderService.selectByPrimaryKey(orderId);
 	   String belong=order.getBelong();
        /****** 1.封装你的交易订单开始 *****/     //自己用此处封装你的订单数据，订单状态可以设置为等待支付
        if(belong.equals("1")){
        	//总公司
        	 DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        	 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        	 BigDecimal total = tradeMoney.multiply(new BigDecimal(100));
             SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
             parameters.put("appid", ConfigUtil.APPID);  											//应用APPID
             parameters.put("mch_id", ConfigUtil.MCH_ID); 											//微信支付分配的商户号
             parameters.put("nonce_str", PayCommonUtil.CreateNoncestr()); 							//随机字符串，不长于32位 
             parameters.put("body", "微信支付"+tradeMoney +"元");  										//商品描述
             parameters.put("out_trade_no", sdf.format(new Date())+String.valueOf(orderId)); 	//订单id
             parameters.put("fee_type", "CNY");  													//默认人民币：CNY
             parameters.put("total_fee", decimalFormat.format(total));  					//订单总金额
             parameters.put("spbill_create_ip",CommonUtil.getIpAddress(request));						//用户端实际ip	
             parameters.put("notify_url", ConfigUtil.notify_url); 									//接收微信支付异步通知回调地址
             parameters.put("trade_type", "APP");  													//支付类型
            //设置签名
             String sign = PayCommonUtil.createSign("UTF-8",parameters);
             parameters.put("sign", sign);
           //封装请求参数结束
             String requestXML = PayCommonUtil.getRequestXml(parameters);  
            //调用统一下单接口
             String result = PayCommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
             System.out.println("\n"+result);
             try {
            	 /**统一下单接口返回正常的prepay_id，再按签名规范重新生成签名后，将数据传输给APP。参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign=WXPay**/
                Map<String, String> map = XMLUtil.doXMLParse(result);
                SortedMap<Object, Object> parameterMap2 = new TreeMap<Object, Object>();  
                parameterMap2.put("appid", ConfigUtil.APPID);
                parameterMap2.put("partnerid", ConfigUtil.MCH_ID);
                parameterMap2.put("prepayid", map.get("prepay_id"));
                parameterMap2.put("package", "Sign=WXPay");  
                parameterMap2.put("noncestr", PayCommonUtil.CreateNoncestr());  
                //本来生成的时间戳是13位，但是ios必须是10位，所以截取了一下
                parameterMap2.put("timestamp", Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0,10)));  
                String sign2 = PayCommonUtil.createSign("UTF-8",parameterMap2);
                parameterMap2.put("sign", sign2);
                
                resultMap.put("code","100");
                resultMap.put("msg",parameterMap2);
            } catch (JDOMException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
             
        }else{
        	//子公司
        	 DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        	 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        	 BigDecimal total = tradeMoney.multiply(new BigDecimal(100));
             SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
             parameters.put("appid", ConfigUtilOne.APPID);  											//应用APPID
             parameters.put("mch_id", ConfigUtilOne.MCH_ID); 											//微信支付分配的商户号
             parameters.put("nonce_str", PayCommonUtil.CreateNoncestr()); 							//随机字符串，不长于32位 
             parameters.put("body", "微信支付"+tradeMoney +"元");  										//商品描述
             parameters.put("out_trade_no", sdf.format(new Date())+String.valueOf(orderId)); 	//订单id
             parameters.put("fee_type", "CNY");  													//默认人民币：CNY
             parameters.put("total_fee", decimalFormat.format(total));  					//订单总金额
             parameters.put("spbill_create_ip",CommonUtil.getIpAddress(request));						//用户端实际ip	
             parameters.put("notify_url", ConfigUtilOne.notify_url); 									//接收微信支付异步通知回调地址
             parameters.put("trade_type", "APP");  													//支付类型
            //设置签名
             String sign = PayCommonUtil.createSign("UTF-8",parameters);
             parameters.put("sign", sign);
           //封装请求参数结束
             String requestXML = PayCommonUtil.getRequestXml(parameters);  
            //调用统一下单接口
             String result = PayCommonUtil.httpsRequest(ConfigUtilOne.UNIFIED_ORDER_URL, "POST", requestXML);
             System.out.println("\n"+result);
             try {
            	 /**统一下单接口返回正常的prepay_id，再按签名规范重新生成签名后，将数据传输给APP。参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign=WXPay**/
                Map<String, String> map = XMLUtil.doXMLParse(result);
                SortedMap<Object, Object> parameterMap2 = new TreeMap<Object, Object>();  
                parameterMap2.put("appid", ConfigUtilOne.APPID);
                parameterMap2.put("partnerid", ConfigUtilOne.MCH_ID);
                parameterMap2.put("prepayid", map.get("prepay_id"));
                parameterMap2.put("package", "Sign=WXPay");  
                parameterMap2.put("noncestr", PayCommonUtil.CreateNoncestr());  
                //本来生成的时间戳是13位，但是ios必须是10位，所以截取了一下
                parameterMap2.put("timestamp", Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0,10)));  
                String sign2 = PayCommonUtil.createSign("UTF-8",parameterMap2);
                parameterMap2.put("sign", sign2);
                
                resultMap.put("code","100");
                resultMap.put("msg",parameterMap2);
            } catch (JDOMException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
             
        }
        
         
        } catch (Exception e) {
        	resultMap.put("code","200"); 
            resultMap.put("msg","支付失败");
            return resultMap;
	    }
        return resultMap;
    }
	
    
	
    /**
     * 微信异步通知

     */
    @RequestMapping(value="/wxNotify",method={RequestMethod.POST,RequestMethod.GET})
    public void wxNotify(HttpServletRequest request,HttpServletResponse response) throws IOException, JDOMException{
        //读取参数  
        InputStream inputStream ;  
        StringBuffer sb = new StringBuffer();  
        inputStream = request.getInputStream();  
        String s ;  
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
        while ((s = in.readLine()) != null){  
            sb.append(s);  
        }
        in.close();  
        inputStream.close();  
        //解析xml成map  
        Map<String, String> m = new HashMap<String, String>();  
        m = XMLUtil.doXMLParse(sb.toString());  
        for(Object keyValue : m.keySet()){
            System.out.println(keyValue+"="+m.get(keyValue));
        }
        //过滤空 设置 TreeMap  
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();        
        Iterator it = m.keySet().iterator();  
        while (it.hasNext()) {  
            String parameter = (String) it.next();  
            String parameterValue = m.get(parameter);  
            String v = "";  
            if(null != parameterValue) {  
                v = parameterValue.trim();  
            }  
            packageParams.put(parameter, v);  
        }  

        //判断签名是否正确  
        String resXml = "";  
        if(PayCommonUtil.isTenpaySign("UTF-8", packageParams)) {
        	System.out.println("进来了");
             if("SUCCESS".equals((String)packageParams.get("result_code"))){ 
            	 System.out.println("支付成功");
                 // 这里是支付成功  
                 //////////执行自己的业务逻辑////////////////  
                 String mch_id = (String)packageParams.get("mch_id"); 					//商户号 
                 String openid = (String)packageParams.get("openid");  					//用户标识
                 String out_trade_no = (String)packageParams.get("out_trade_no"); 		//商户订单号
                 String total_fee = (String)packageParams.get("total_fee");  			//
                 String transaction_id = (String)packageParams.get("transaction_id"); 	//微信支付订单号
                 
                 String orderId=out_trade_no.substring(17);
                 Order order=orderService.selectByPrimaryKey(Integer.parseInt(orderId));
                // System.out.println(tradingRecord==null);
                // System.out.println(ConfigUtil.MCH_ID.equals(mch_id));
                // System.out.println(tradingRecord.getTradingRecordPrice() != 0);
                if(!ConfigUtil.MCH_ID.equals(mch_id)||order==null||!order.getOrderType().equals("0") ){
               	 resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
                                 + "<return_msg><![CDATA[参数错误]]></return_msg>" + "</xml> ";  
                }else{
                     if(order.getOrderType().equals("0") ){//支付的状态
                   	  //订单状态的修改。根据实际业务逻辑执行   
                   	 // BoxinUser findUser = userService.getUserById(order.getUserId());
                   	
                   	  //设置用户主键、金额进行修改
                   	// findUser.setUserbalance(findUser.getUserbalance() + order.getOrderPrice());
                   	  //设置交易记录主键和状态进行修改
                   	order.setOrderType("1");
                   	 //计算积分
   	        	 Conversione conversion =new Conversione();
   	        	 List<Conversione> cons=conversioneService.selectByExample(conversion);
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
   	        			OrderProduct ord=new OrderProduct();//修改订单产品状态
   		        	 ord.setOrderNumber(order.getOrderNumber());
   		        	 List<OrderProduct> products=orderProductService.selectByExample(ord);
   		 			for(int i=0;i<products.size();i++){
   		 				products.get(i).setType("1");
   		 				orderProductService.updateByPrimaryKeySelective(products.get(i));
   		 			}
   		 		orderService.updateByPrimaryKey(order);
                   	  resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
                   			  + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
                     }else{
                   	  resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
                             + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
                     }
                 }

    //            BoxinUser user = userService.getUserById(order.getUserId());
//                if(user != null) {
//                	
//                        	try {
//                        		JpushUtil.pushToAliasMessage("您使用微信支付"+order.getOrderPrice()+"元已完成",user.getUserid().toString());
//            				} catch (Exception e) {
//            				}
//                }
                
             }else {  
                 resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
                         + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
             }  


        } else{  
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
                    + "<return_msg><![CDATA[通知签名验证失败]]></return_msg>" + "</xml> "; 
        }   

        //------------------------------  
        //处理业务完毕  
        //------------------------------  
        BufferedOutputStream out = new BufferedOutputStream(  
                response.getOutputStream());  
        out.write(resXml.getBytes());  
        out.flush();  
        out.close();  

    }
    
    
	
	/**
	 * 统一下单接口
	 * @param phonenum					
	 * @param transaction_id			订单id
	 * @param out_trade_no				交易号
	 * @param price						叫金额
	 * @param status					支付状态(0:失败 1:成功)		
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping(value="orderWXGenerated",method=RequestMethod.POST)
	public Msg orderWXGenerated(@RequestParam("phonenum")String phonenum,
			@RequestParam("transaction_id")Integer transaction_id,
			@RequestParam("out_trade_no")String out_trade_no,
			@RequestParam("price")Double price,
			@RequestParam("status")Integer status) {
		
		SMSBean smsBean = (SMSBean) CommonUtil.MSG_MAP.get(phonenum);
		User user = userService.selectByPrimaryKey(smsBean.getId());
		
		TradingRecord tradingRecord = new TradingRecord();
		tradingRecord.setTradingRecordUserId(user.getUserId());
		tradingRecord.setTradingRecordType(0);
		tradingRecord.setTradingRecordPrice(price);
		tradingRecord.setBill(out_trade_no);
		tradingRecord.setTradingRecordTime(new Date());
		tradingRecord.setDes("微信充值");
		if(status == 0) {//失败
			return Msg.fail().add("msg", "充值失败");
		}else {//成功
			user.setUserBalance(user.getUserBalance() + price);//余额
			user.setUserIntegral(user.getUserIntegral() + price.intValue());//积分
			userService.updateByPrimaryKeySelective(user);
			tradingRecordService.insertSelective(tradingRecord);
		}
		return Msg.success();
	}
	
	*//**微信异步回调**//*
	@ResponseBody
	@RequestMapping(value="checkWXPayment",method={RequestMethod.POST,RequestMethod.GET})
	public void checkWXPayment(HttpServletRequest request,HttpServletResponse response){
		Map<String,String >	map =  new HashMap<String,String>();
		try {
			InputStream inStream = request.getInputStream();
			 ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			 byte[] buffer = new byte[1024];
			 int len = 0;
			 while ((len = inStream.read(buffer)) != -1){
				 outSteam.write(buffer, 0, len);
			 }
			 outSteam.close();
			 inStream.close();
			 *//** 获取微信调用notify_url的返回XML信息 *//*
			 String result = new String(outSteam.toByteArray(), "utf-8");
			 SortedMap<String, String> mapResult = dom4jXMLParse(result);
			 String return_code = mapResult.get("return_code");
			 if("SUCCESS".equals(return_code)){
				 String result_code = mapResult.get("result_code");
				 if("SUCCESS".equals(result_code)){
					 boolean wechatSign = isWechatSign(mapResult,WxConst.KEY_VALUE);
					 if(wechatSign){
						 String  out_trade_no 	= mapResult.get("out_trade_no");
						 String  transaction_id = mapResult.get("transaction_id");
						
						 TradingRecord tradingRecord = tradingRecordService.selectByPrimaryKey(Integer.parseInt(transaction_id));
						 tradingRecord.setBill(out_trade_no);
						 if(tradingRecord.getTradingRecordStatus() == 0) {
								tradingRecord.setTradingRecordStatus(1);
								tradingRecordService.updateByPrimaryKeySelective(tradingRecord);
						 }
						
						 OutputStream outputStream = response.getOutputStream();
						 String returnMsg="<xml><return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg></xml>";
						 outputStream.write(returnMsg.getBytes("UTF-8")); 
					 }
				 }
			 }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	*//*******************验证签名合法性************************//*
	public static SortedMap<String, String> dom4jXMLParse(String    strXML) throws DocumentException {
	     SortedMap<String, String> smap = new TreeMap<String, String>();
	     Document doc = DocumentHelper.parseText(strXML);
	     Element root = doc.getRootElement();
	     for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
	         Element e = (Element) iterator.next();
	         smap.put(e.getName(), e.getText());
	     }
	     return smap;
	 }
	
	 public static boolean isWechatSign(SortedMap<String, String> smap,String apiKey) {
	     StringBuffer sb = new StringBuffer();
	     Set es = smap.entrySet();
	     Iterator it = es.iterator();
	     while (it.hasNext()) {
	         Map.Entry entry = (Map.Entry) it.next();
	         String k = (String) entry.getKey();
	         String v = (String) entry.getValue();
	         if (!"sign".equals(k) && null != v && !"".equals(v) && !"key".equals(k)) {
	             sb.append(k + "=" + v + "&");
	         }
	     }
	     sb.append("key=" + apiKey);
	     *//** 验证的签名 *//*
	     String sign = null;
		try {
			sign = UUIDUtils.md5Encode(sb.toString()).toUpperCase();
			if(sign==null){
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	     *//** 微信端返回的合法签名 *//*
	     String validSign = ((String) smap.get("sign")).toUpperCase();
	     return validSign.equals(sign);
	 }*/
}
