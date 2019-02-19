package com.py.shijijingying.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.py.shijijingying.dao.MessageDao;
import com.py.shijijingying.entity.Message;
import com.py.shijijingying.entity.UserMessage;

@Service
public class MessageService{
	@Autowired
	private MessageDao messageDao;
	
	public int deleteByPrimaryKey(Integer messageId) {
		return messageDao.deleteByPrimaryKey(messageId);
	}

	public int insert(Message record) {
		return messageDao.insert(record);
	}
	
	public int insertSelective(Message record) {
		return messageDao.insertSelective(record);
	}
	
	public Message selectByPrimaryKey(Integer messageId) {
		return messageDao.selectByPrimaryKey(messageId);
	}
	
	public int updateByPrimaryKeySelective(Message record) {
		return messageDao.updateByPrimaryKeySelective(record);
	}
	
	public int updateByPrimaryKey(Message record) {
		return messageDao.updateByPrimaryKey(record);
	}
	
	public List<Message> selectByExample(Message message) {
		return messageDao.selectByExample(message);
	}
	
	public Message selectByPrimary(Message message) {
		return messageDao.selectByPrimary(message);
	}
	
	public List<UserMessage> selectAllMessageAndUser() {
		return messageDao.selectAllMessageAndUser();
	}
	
	public int deleteByPrimary(Message message) {
		return messageDao.deleteByPrimary(message);
	}
}
