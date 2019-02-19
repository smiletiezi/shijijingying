package com.py.shijijingying.entity;

import java.util.List;

public class UserMessage extends Message {
	
	private List<User> userMessage;

	public List<User> getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(List<User> userMessage) {
		this.userMessage = userMessage;
	}

}
