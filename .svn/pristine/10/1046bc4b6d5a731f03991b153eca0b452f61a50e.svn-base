package com.py.shijijingying.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.SysTokenMapper;
import com.py.shijijingying.entity.SysToken;
import com.py.shijijingying.utils.AESUtil;
import com.py.shijijingying.utils.Utils;
/**
 * 
 * @author LiuHao
 *
 * 2018年9月22日
 */
@Service
public class SysTokenService {
	@Autowired
	private SysTokenMapper sysTokenMapper;
	
	/**
	 * 生成token 根据user id 查询有的修改  没有的新建
	 * @param userId
	 * @param type
	 * @return
	 */
	public String generateToken(int userId) {
		// 系统当前时间
		Date current = new Date();
		//生成一个八位数的salt
		byte[] salt =Utils.generateSalt(8);
		 // 将salt转化为十六进制字符串
		String hexSalt =Utils.encodeHex(salt);
		// 将uuid和当前系统时间拼接
		String pass = UUID.randomUUID() + "&" + current.getTime();
		String token = AESUtil.encode(pass, hexSalt);
		//修改token长度  32
		if(token.length() > 32) {
			token = token.substring(0, 32);
		}
		SysToken systoken=  sysTokenMapper.selectByIdMobileTerminal(userId);
		SysToken sysToken = new SysToken();
		sysToken.setCreateTime(current);
		sysToken.setToken(token);
		int  code=	0;
		if(systoken!=null) {
			sysToken.setId(systoken.getId());
			code=sysTokenMapper.updateByPrimaryKeySelective(sysToken);
		}else {
			code= sysTokenMapper.insertSelective(sysToken);
		}
		if(code!=1) {
			return "Token生成失败";
		}
		return token;
	}
	
	
}
