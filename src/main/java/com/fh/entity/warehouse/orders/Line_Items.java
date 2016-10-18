/**
  * Copyright 2016 aTool.org 
  */
package com.fh.entity.warehouse.orders;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2016-09-26 15:42:20
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Line_Items {

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getSubtotal_tax() {
		return subtotal_tax;
	}
	public void setSubtotal_tax(String subtotal_tax) {
		this.subtotal_tax = subtotal_tax;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTotal_tax() {
		return total_tax;
	}
	public void setTotal_tax(String total_tax) {
		this.total_tax = total_tax;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getTax_class() {
		return tax_class;
	}
	public void setTax_class(String tax_class) {
		this.tax_class = tax_class;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public List<String> getMeta() {
		return meta;
	}
	public void setMeta(List<String> meta) {
		this.meta = meta;
	}
	private int id;
    private String subtotal;
    @JsonProperty("subtotal_tax")
    private String subtotal_tax;
    private String total;
    @JsonProperty("total_tax")
    private String total_tax;
    private String price;
    private int quantity;
    @JsonProperty("tax_class")
    private String tax_class;
    private String name;
    @JsonProperty("product_id")
    private int product_id;
    private String sku;
    private List<String> meta;
    public int getOrder_ID() {
		return Order_ID;
	}
	public void setOrder_ID(int order_ID) {
		Order_ID = order_ID;
	}
	public int getProduct_ID() {
		return Product_ID;
	}
	public void setProduct_ID(int product_ID) {
		Product_ID = product_ID;
	}
	private int Order_ID;
    private int Product_ID;

}