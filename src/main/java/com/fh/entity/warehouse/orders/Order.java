/**
  * Copyright 2016 aTool.org 
  */
package com.fh.entity.warehouse.orders;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fh.util.CustomerDateAndTimeDeserialize;
/**
 * Auto-generated: 2016-09-26 15:42:20
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Order {

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrder_number() {
		return order_number;
	}
	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}
	public String getOrder_key() {
		return order_key;
	}
	public void setOrder_key(String order_key) {
		this.order_key = order_key;
	}
	public Date getCreated_at() {
		return created_at;
	}
	@JsonDeserialize(using=CustomerDateAndTimeDeserialize .class)
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	@JsonDeserialize(using=CustomerDateAndTimeDeserialize .class)
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public Date getCompleted_at() {
		return completed_at;
	}
	@JsonDeserialize(using=CustomerDateAndTimeDeserialize .class)
	public void setCompleted_at(Date completed_at) {
		this.completed_at = completed_at;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public int getTotalLineItemsQuantity() {
		return totalLineItemsQuantity;
	}
	public void setTotalLineItemsQuantity(int totalLineItemsQuantity) {
		this.totalLineItemsQuantity = totalLineItemsQuantity;
	}
	public String getTotal_tax() {
		return total_tax;
	}
	public void setTotal_tax(String total_tax) {
		this.total_tax = total_tax;
	}
	public String getTotal_shipping() {
		return total_shipping;
	}
	public void setTotal_shipping(String total_shipping) {
		this.total_shipping = total_shipping;
	}
	public String getCart_tax() {
		return cart_tax;
	}
	public void setCart_tax(String cart_tax) {
		this.cart_tax = cart_tax;
	}
	public String getShipping_tax() {
		return shipping_tax;
	}
	public void setShipping_tax(String shipping_tax) {
		this.shipping_tax = shipping_tax;
	}
	public String getTotal_discount() {
		return total_discount;
	}
	public void setTotal_discount(String total_discount) {
		this.total_discount = total_discount;
	}
	public String getShipping_methods() {
		return shipping_methods;
	}
	public void setShipping_methods(String shipping_methods) {
		this.shipping_methods = shipping_methods;
	}
	public Payment_Details getPayment_details() {
		return payment_details;
	}
	public void setPayment_details(Payment_Details payment_details) {
		this.payment_details = payment_details;
	}
	public Billing_Address getBilling_address() {
		return billing_address;
	}
	public void setBilling_address(Billing_Address billing_address) {
		this.billing_address = billing_address;
	}
	public Shipping_Address getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(Shipping_Address shipping_address) {
		this.shipping_address = shipping_address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCustomer_ip() {
		return customer_ip;
	}
	public void setCustomer_ip(String customer_ip) {
		this.customer_ip = customer_ip;
	}
	public String getCustomer_user_agent() {
		return customer_user_agent;
	}
	public void setCustomer_user_agent(String customer_user_agent) {
		this.customer_user_agent = customer_user_agent;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getView_order_url() {
		return view_order_url;
	}
	public void setView_order_url(String view_order_url) {
		this.view_order_url = view_order_url;
	}
	public List<Line_Items> getLine_items() {
		return line_items;
	}
	public void setLine_items(List<Line_Items> line_items) {
		this.line_items = line_items;
	}
	public List<Shipping_Lines> getShipping_lines() {
		return shipping_lines;
	}
	public void setShipping_lines(List<Shipping_Lines> shipping_lines) {
		this.shipping_lines = shipping_lines;
	}
	public List<Tax_Lines> getTax_lines() {
		return tax_lines;
	}
	public void setTax_lines(List<Tax_Lines> tax_lines) {
		this.tax_lines = tax_lines;
	}
	public List<String> getFee_lines() {
		return fee_lines;
	}
	public void setFee_lines(List<String> fee_lines) {
		this.fee_lines = fee_lines;
	}
	public List<String> getCoupon_lines() {
		return coupon_lines;
	}
	public void setCoupon_lines(List<String> coupon_lines) {
		this.coupon_lines = coupon_lines;
	}
	public boolean isIs_vat_exempt() {
		return is_vat_exempt;
	}
	public void setIs_vat_exempt(boolean is_vat_exempt) {
		this.is_vat_exempt = is_vat_exempt;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	private int id;

    private int order_number;
  
    private String order_key;

    private Date created_at;

    private Date updated_at;
 
    private Date completed_at;
    private String status;
    private String currency;
    private String total;
    private String subtotal;
    @JsonProperty("total_line_items_quantity")
    private int totalLineItemsQuantity;

    private String total_tax;
    @JsonProperty("total_shipping")
    private String total_shipping;
    @JsonProperty("cart_tax")
    private String cart_tax;
    @JsonProperty("shipping_tax")
    private String shipping_tax;
    @JsonProperty("total_discount")
    private String total_discount;
    @JsonProperty("shipping_methods")
    private String shipping_methods;
    @JsonProperty("payment_details")
    private Payment_Details payment_details;
    @JsonProperty("billing_address")
    private Billing_Address billing_address;
    @JsonProperty("shipping_address")
    private Shipping_Address shipping_address;
    private String note;
    @JsonProperty("customer_ip")
    private String customer_ip;
    @JsonProperty("customer_user_agent")
    private String customer_user_agent;
    @JsonProperty("customer_id")
    private int customer_id;
    @JsonProperty("view_order_url")
    private String view_order_url;
    @JsonProperty("line_items")
    private List<Line_Items> line_items;
    @JsonProperty("shipping_lines")
    private List<Shipping_Lines> shipping_lines;
    @JsonProperty("tax_lines")
    private List<Tax_Lines> tax_lines;
    @JsonProperty("fee_lines")
    private List<String> fee_lines;
    @JsonProperty("coupon_lines")
    private List<String> coupon_lines;
    @JsonProperty("is_vat_exempt")
    private boolean is_vat_exempt;
    private Customer customer;
  

}