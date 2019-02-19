package com.py.shijijingying.dao;

import java.util.List;

import com.py.shijijingying.entity.Message;
import com.py.shijijingying.entity.UserMessage;

public interface MessageDao {
	public int deleteByPrimaryKey(Integer messageId);

	public int insert(Message record);

	public int insertSelective(Message record);

	public Message selectByPrimaryKey(Integer messageId);

	public int updateByPrimaryKeySelective(Message record);

	public int updateByPrimaryKey(Message record);

	public List<Message> selectByExample(Message message);

	public Message selectByPrimary(Message message);

	public List<UserMessage> selectAllMessageAndUser();

	public int deleteByPrimary(Message message);
}
