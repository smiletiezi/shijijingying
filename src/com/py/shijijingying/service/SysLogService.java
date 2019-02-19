package com.py.shijijingying.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.SysLogMapper;
import com.py.shijijingying.entity.SysLog;

/**
 * 系统日志
 * @author Mr Liu hao
 *
 * 2018年6月25日
 */
@Service
public class SysLogService {

	@Autowired
	private  SysLogMapper sysLogMapper;
	
	/**
	 * 增加日志
	 * @param record
	 * @return
	 */
	 public  int insertSelective(SysLog record) {
		  return sysLogMapper.insertSelective(record);
	  }
	 
	 /**
	  * 查询日志 (可带条件)
	  * @param map
	  * @return
	  */
	 public List<SysLog> conditionSelect(Map<String , Object> map) {
		 return sysLogMapper.conditionSelect(map);
	   }
}
