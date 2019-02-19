package com.py.shijijingying.entity;

/*
 * 订单记录
 */
public class TradingRecord {
    private Integer tradingRecordId;

    private Integer tradingRecordUserId;

    private Integer tradingRecordType;

    private Double tradingRecordPrice;

    private Integer tradingRecordStatus;

    private String tradingRecordDes;

    private String tradingRecordBill;

    private String transactionId;

    private String tradingRecordTime;

    public Integer getTradingRecordId() {
        return tradingRecordId;
    }

    public void setTradingRecordId(Integer tradingRecordId) {
        this.tradingRecordId = tradingRecordId;
    }

    public Integer getTradingRecordUserId() {
        return tradingRecordUserId;
    }

    public void setTradingRecordUserId(Integer tradingRecordUserId) {
        this.tradingRecordUserId = tradingRecordUserId;
    }

    public Integer getTradingRecordType() {
        return tradingRecordType;
    }

    public void setTradingRecordType(Integer tradingRecordType) {
        this.tradingRecordType = tradingRecordType;
    }

    public Double getTradingRecordPrice() {
        return tradingRecordPrice;
    }

    public void setTradingRecordPrice(Double tradingRecordPrice) {
        this.tradingRecordPrice = tradingRecordPrice;
    }

    public Integer getTradingRecordStatus() {
        return tradingRecordStatus;
    }

    public void setTradingRecordStatus(Integer tradingRecordStatus) {
        this.tradingRecordStatus = tradingRecordStatus;
    }

    public String getTradingRecordDes() {
        return tradingRecordDes;
    }

    public void setTradingRecordDes(String tradingRecordDes) {
        this.tradingRecordDes = tradingRecordDes == null ? null : tradingRecordDes.trim();
    }

    public String getTradingRecordBill() {
        return tradingRecordBill;
    }

    public void setTradingRecordBill(String tradingRecordBill) {
        this.tradingRecordBill = tradingRecordBill == null ? null : tradingRecordBill.trim();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public String getTradingRecordTime() {
        return tradingRecordTime;
    }

    public void setTradingRecordTime(String tradingRecordTime) {
        this.tradingRecordTime = tradingRecordTime;
    }
}