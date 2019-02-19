package com.py.shijijingying.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.py.shijijingying.entity.MyVoucher;
import com.py.shijijingying.entity.Voucher;
import com.py.shijijingying.service.MyVoucherService;
import com.py.shijijingying.service.VoucherService;
import com.py.shijijingying.utils.EmptyUtil;
import com.py.shijijingying.utils.Msg;


@Controller  
@RequestMapping("/myVoucher")
public class MyVoucherController {
 @Autowired
 private MyVoucherService myVoucherService;
 @Autowired
 private VoucherService voucherService;

/*
 * 领取优惠券
 */
@ResponseBody
@RequestMapping(value="/insert")
public Msg insert(@RequestParam(value="userId",required=false)Integer userId,
		           @RequestParam(value="voucherId",required=false)Integer voucherId
          ){
	MyVoucher voucher=new MyVoucher();
	voucher.setUserId(userId);
	voucher.setVoucherId(voucherId);
	List<MyVoucher> vouchers=myVoucherService.selectByExample(voucher);
	if(null == vouchers || vouchers.size() ==0){
		if(userId!=null){
			voucher.setUserId(userId);
		}else{
			return Msg.fail().add("msg", "请确认领取人");
		}
		if(voucherId!=null){
			voucher.setVoucherId(voucherId);
		}else{
			return Msg.fail().add("msg", "请点击优惠券");
		}
		voucher.setVoucherUse("0");
		voucher.setVoucherOverdue("0");
		try{
			myVoucherService.insertSelective(voucher);
			Voucher vou=voucherService.selectByPrimaryKey(voucherId);
			vou.setVoucherUse("1");
			voucherService.updateByPrimaryKey(vou);
			}catch(Exception e){
				return Msg.fail().add("msg", "处理失败");
			}
			return Msg.success();
     }else{
	return Msg.fail().add("msg", "已领取过");
     }

}

/*
 * 点击我的优惠券时
 */
@ResponseBody
@RequestMapping(value="/list")
public Msg uplode(@RequestParam(value="userId",required=false)Integer userId) throws ParseException{
	MyVoucher voucher=new MyVoucher();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	voucher.setUserId(userId);
	List<MyVoucher> vouchers=myVoucherService.selectByExample(voucher);//我的代金券
	List<Voucher> voucherN=new ArrayList<Voucher>();
	List<Object> obj=new ArrayList<Object>();
	if(vouchers!=null || vouchers.size()>0){
		for(int i=0;i<vouchers.size();i++){
			voucherN.add(voucherService.selectByPrimaryKey(vouchers.get(i).getVoucherId()));
			obj.add(vouchers.get(i).getMyVoucherId());
			obj.add(voucherService.selectByPrimaryKey(vouchers.get(i).getVoucherId()));
		}
		for(int i=0;i<vouchers.size();i++){
			Date voucherExpiry=format.parse(voucherN.get(i).getVoucherExpiry());//过期时间
			Date time=new Date();//当前时间
			if(voucherExpiry.before(time)){
				vouchers.get(i).setVoucherOverdue("1");
				myVoucherService.updateByPrimaryKey(vouchers.get(i));
			}
	}
		return Msg.success(obj);
	}else{
		return Msg.fail().add("msg", "没有代金券");
	}
}

/*
 * 查看已使用的，未使用的 已过期的
 */
@ResponseBody
@RequestMapping(value="/userlist")
public Msg uselist(@RequestParam(value="userId",required=false)Integer userId,
		@RequestParam(value="voucherUse",required=false)String voucherUse,
		@RequestParam(value="voucherOverdue",required=false)String voucherOverdue){
	MyVoucher voucher=new MyVoucher();
	voucher.setUserId(userId);
	voucher.setVoucherUse(voucherUse);
	voucher.setVoucherOverdue(voucherOverdue);
	List<MyVoucher> myvouchers=myVoucherService.selectByExample(voucher);
	if(null == myvouchers || myvouchers.size() ==0){
		return Msg.fail().add("msg", "未找到代金券");
	}else{
		//List<Voucher> vouchers=new ArrayList<Voucher>();
		List<Object> obj=new ArrayList<Object>();
		for(int i=0;i<myvouchers.size();i++){
			//vouchers.add(voucherService.selectByPrimaryKey(myvouchers.get(i).getVoucherId()));
			obj.add(myvouchers.get(i).getMyVoucherId());
			obj.add(voucherService.selectByPrimaryKey(myvouchers.get(i).getVoucherId()));
		}
		return Msg.success(obj);
	}
}

/*
 * 移除优惠券
 */
@ResponseBody
@RequestMapping(value="/delete")
public Msg delete(@RequestParam(value="myVoucherId",required=false)Integer myVoucherId
       ){
	MyVoucher voucher=myVoucherService.selectByPrimaryKey(myVoucherId);
	if(EmptyUtil.isEmpty(voucher)){
		return Msg.fail().add("msg", "未找到代金券");
	}else{
		myVoucherService.deleteByPrimaryKey(myVoucherId);
		return Msg.success();
	}
}
}
