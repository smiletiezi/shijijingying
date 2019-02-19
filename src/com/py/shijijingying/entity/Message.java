package com.py.shijijingying.entity;
/*
 * 消息
 */
public class Message {
    private Integer messageId;

    private Integer messageType;

    private Integer messageStaffType;

    private String messageContent;

    private Integer messageUserId;

    private Integer messageRead;

    private String messageTime;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getMessageStaffType() {
        return messageStaffType;
    }

    public void setMessageStaffType(Integer messageStaffType) {
        this.messageStaffType = messageStaffType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public Integer getMessageUserId() {
        return messageUserId;
    }

    public void setMessageUserId(Integer messageUserId) {
        this.messageUserId = messageUserId;
    }

    public Integer getMessageRead() {
        return messageRead;
    }

    public void setMessageRead(Integer messageRead) {
        this.messageRead = messageRead;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime == null ? null : messageTime.trim();
    }
}