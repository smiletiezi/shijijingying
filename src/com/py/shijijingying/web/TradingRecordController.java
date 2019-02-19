package com.py.shijijingying.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.py.shijijingying.entity.TradingRecord;
import com.py.shijijingying.service.TradingRecordService;
import com.py.shijijingying.utils.EmptyUtil;
import com.py.shijijingying.utils.Msg;


@Controller  
@RequestMapping("/tradingRecord")
public class TradingRecordController {
	@Autowired
	private TradingRecordService tradingRecordService;
	/*
	 * 订单查询
	 */
	@ResponseBody
	@RequestMapping(value="/select")
	public Msg select(@RequestParam(value="tradingRecordId",required=false)Integer tradingRecordId,
            @RequestParam(value="tradingRecordUserId",required=false)Integer tradingRecordUserId,
            @RequestParam(value="tradingRecordType",required=false)Integer tradingRecordType,
            @RequestParam(value="tradingRecordPrice",required=false)Double tradingRecordPrice,
            @RequestParam(value="tradingRecordStatus",required=false)Integer tradingRecordStatus,
            @RequestParam(value="tradingRecordDes",required=false)String tradingRecordDes,
            @RequestParam(value="tradingRecordBill",required=false)String tradingRecordBill,
            @RequestParam(value="transactionId",required=false)String transactionId,
            @RequestParam(value="tradingRecordTime",required=false)String tradingRecordTime
            ){
		TradingRecord record =new TradingRecord();
		if(tradingRecordId!=null){
			record.setTradingRecordId(tradingRecordId);
		}
		if(tradingRecordUserId!=null){
			record.setTradingRecordUserId(tradingRecordUserId);
		}
		if(tradingRecordType!=null){
			record.setTradingRecordType(tradingRecordType);
		}
		if(tradingRecordPrice!=null){
			record.setTradingRecordPrice(tradingRecordPrice);
		}
		if(tradingRecordStatus!=null){
			record.setTradingRecordStatus(tradingRecordStatus);
		}
		if(tradingRecordDes!=null){
			record.setTradingRecordDes(tradingRecordDes);
		}
		if(tradingRecordBill!=null){
			record.setTradingRecordBill(tradingRecordBill);
		}
		if(transactionId!=null){
			record.setTransactionId(transactionId);
		}
		if(tradingRecordTime!=null){
			record.setTradingRecordTime(tradingRecordTime);
		}
		try{
		      List<TradingRecord> records=tradingRecordService.selectByExample(record);
		      if(EmptyUtil.isEmpty(records)){
		    	  return Msg.fail().add("msg", "订单为空");
		      }
		      return Msg.success(records);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
	}
}
