/**
  * Copyright 2016 aTool.org 
  */
package com.fh.entity.warehouse.orders;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2016-09-26 15:42:20
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Customer {

    private int id;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLast_order_id() {
		return last_order_id;
	}
	public void setLast_order_id(String last_order_id) {
		this.last_order_id = last_order_id;
	}
	public Date getLast_order_date() {
		return last_order_date;
	}
	public void setLast_order_date(Date last_order_date) {
		this.last_order_date = last_order_date;
	}
	public int getOrders_count() {
		return orders_count;
	}
	public void setOrders_count(int orders_count) {
		this.orders_count = orders_count;
	}
	public String getTotal_spent() {
		return total_spent;
	}
	public void setTotal_spent(String total_spent) {
		this.total_spent = total_spent;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
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
	@JsonProperty("created_at")
    private Date created_at;
    @JsonProperty("last_update")
    private Date last_update;
    private String email;
    @JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;
    private String username;
    private String role;
    @JsonProperty("last_order_id")
    private String last_order_id;
    @JsonProperty("last_order_date")
    private Date last_order_date;
    @JsonProperty("orders_count")
    private int orders_count;
    @JsonProperty("total_spent")
    private String total_spent;
    @JsonProperty("avatar_url")
    private String avatar_url;
    @JsonProperty("billing_address")
    private Billing_Address billing_address;
    @JsonProperty("shipping_address")
    private Shipping_Address shipping_address;
}