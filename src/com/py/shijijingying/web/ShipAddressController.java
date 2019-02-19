package com.py.shijijingying.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.py.shijijingying.entity.ShipAddress;
import com.py.shijijingying.service.ShipAddressService;
import com.py.shijijingying.utils.EmptyUtil;
import com.py.shijijingying.utils.Msg;

@Controller  
@RequestMapping("/ship")
public class ShipAddressController {
	@Autowired
	private ShipAddressService shipAddressService;
	
	/*
	 * add收货地址
	 * @param shipPerson
	 * @param shipPhone
	 * @param shipAddress
	 * @param shipType
	 */
	@ResponseBody
	@RequestMapping(value="/add")
	public Msg add(@RequestParam(value="shipPerson",required=false)String shipPerson,
			@RequestParam(value="userId",required=false)Integer userId,
            @RequestParam(value="shipPhone",required=false)String shipPhone,
            @RequestParam(value="shipAddress",required=false)String shipAddress,
            @RequestParam(value="shipType",required=false)String shipType){
		ShipAddress ship=new ShipAddress();
		    ship.setUserId(userId);
			ship.setShipPerson(shipPerson);
			ship.setShipPhone(shipPhone);
			ship.setShipAddress(shipAddress);
		List<ShipAddress> ships=shipAddressService.selectByExample(ship);
		if(null == ships || ships.size() ==0){
			if(userId!=null){
				ship.setUserId(userId);
			}else{
				return Msg.fail().add("msg", "当前用户不能为空");
			}
			if (shipPerson != null) {
				ship.setShipPerson(shipPerson);
			}else{
				return Msg.fail().add("msg", "收货人不能为空");
			}
			if (shipPhone != null) {
				ship.setShipPhone(shipPhone);
			}else{
				return Msg.fail().add("msg", "收货人电话不能为空");
			}
			if (shipAddress != null) {
				ship.setShipAddress(shipAddress);
			}else{
				return Msg.fail().add("msg", "收货地址不能为空");
			}
			if (shipType!=null&&shipType.equals("1")){
				
				ShipAddress shi=new ShipAddress();
				shi.setUserId(userId);
				shi.setShipType("1");
				List<ShipAddress> shis=shipAddressService.selectByExample(shi);
				if(null == shis || shis.size() ==0){
					ship.setShipType(shipType);
				}else{
					for(int i=0;i<shis.size();i++){
						shis.get(i).setShipType("0");
						shipAddressService.updateByPrimaryKey(shis.get(i));
					}
					ship.setShipType(shipType);
				}
				
			}else{
				ship.setShipType("0");//不是默认收货地址
			}
			shipAddressService.insertSelective(ship);
			Msg msg = Msg.success();
	        return msg;
		}else{
			return Msg.fail().add("msg", "该地址已存在");
		}
		
	}
	
	/*
	 * delete收货地址
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/delete")
	public Msg delete(@RequestParam(value="shipId",required=false)Integer shipId
            ){
		ShipAddress ship=shipAddressService.selectByPrimaryKey(shipId);
		
			if(EmptyUtil.isEmpty(ship)){
				return Msg.fail().add("msg", "请选择要删除的地址");
			}else{
			try{
					shipAddressService.deleteByPrimaryKey(shipId);
			}catch(Exception e){
					return Msg.fail().add("msg", "删除失败");
				}
			}
		return Msg.success();
	}
	
	/*
	 * update收货地址
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/update")
	public Msg update(@RequestParam(value="shipId",required=false)Integer shipId,
			@RequestParam(value="shipPerson",required=false)String shipPerson,
			@RequestParam(value="shipPhone",required=false)String shipPhone,
			@RequestParam(value="shipAddress",required=false)String shipAddress,
			@RequestParam(value="shipType",required=false)String shipType
            ){
		ShipAddress ship=shipAddressService.selectByPrimaryKey(shipId);
		
			if(EmptyUtil.isEmpty(ship)){
				return Msg.fail().add("msg", "请选择要修改的地址");
			}else{
			
				if(shipPerson !=null){
					ship.setShipPerson(shipPerson);
				}
				if(shipPhone !=null){
					ship.setShipPhone(shipPhone);
				}
				if(shipAddress !=null){
					ship.setShipAddress(shipAddress);
				}
				if (shipType!=null&&shipType.equals("1")){
					
					ShipAddress shi=new ShipAddress();
					shi.setUserId(ship.getUserId());
					shi.setShipType("1");
					List<ShipAddress> shis=shipAddressService.selectByExample(shi);
					if(null == shis || shis.size() ==0){
						ship.setShipType(shipType);
					}else{
						for(int i=0;i<shis.size();i++){
							shis.get(i).setShipType("0");
							shipAddressService.updateByPrimaryKey(shis.get(i));
						}
						ship.setShipType(shipType);
					}
					
				}else{
					ship.setShipType("0");//不是默认收货地址
				}
				try{
					shipAddressService.updateByPrimaryKey(ship);
			}catch(Exception e){
					return Msg.fail().add("msg", "修改失败");
				}
			}
		return Msg.success();
	}
	
	/*
	 * 收货地址list
	 * @param shipId
	 */
	@ResponseBody
	@RequestMapping(value="/list")
	public Msg list(@RequestParam(value="userId",required=false)Integer userId){
		List<ShipAddress> ships=new ArrayList<ShipAddress>();
		ShipAddress ship=new ShipAddress();
		ship.setUserId(userId);
		try{
			ships=shipAddressService.selectByExample(ship);
		}catch(Exception e){
			return Msg.fail().add("msg", "处理失败");
		}
			return Msg.success(ships);
	}
	
	/*
	 * 获取默认收货地址
	 */
	@ResponseBody
	@RequestMapping(value="/getDefault")
	public Msg getDefault(@RequestParam(value="userId",required=false)Integer userId){
		ShipAddress ship=new ShipAddress();
		ship.setUserId(userId);
		ship.setShipType("1");
		List<ShipAddress> ships=shipAddressService.selectByExample(ship);
		if(null == ships || ships.size() ==0){
			return Msg.fail().add("msg", "您还没有默认收货地址，请先设置一个默认收货地址");
		}else{
			return Msg.success(ships.get(0));
		}
	}
}
