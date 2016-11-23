package com.fh.entity;




import org.codehaus.jackson.annotate.JsonProperty;

public class LatiPay {



   private String amount;
   private String code;
   @JsonProperty("payType")
   private String paytype;
   private String md5info;
   @JsonProperty("orderId")
   private String orderid;
   private String currency;
   private String status;
   public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAmount() {
        return amount;
    }

   public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

   public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
    public String getPaytype() {
        return paytype;
    }

   public void setMd5info(String md5info) {
        this.md5info = md5info;
    }
    public String getMd5info() {
        return md5info;
    }

   public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    public String getOrderid() {
        return orderid;
    }

   public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getCurrency() {
        return currency;
    }

   public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

}