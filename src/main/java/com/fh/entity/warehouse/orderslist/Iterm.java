package com.fh.entity.warehouse.orderslist;

public class Iterm {
private String sku;
private int OrderItems_ID;
public int getOrderItems_ID() {
	return OrderItems_ID;
}
public void setOrderItems_ID(int orderItems_ID) {
	OrderItems_ID = orderItems_ID;
}
public String getSku() {
	return sku;
}
public void setSku(String sku) {
	this.sku = sku;
}
public String getTotal() {
	return total;
}
public void setTotal(String total) {
	this.total = total;
}
private String total;
private int Order_ID;
public int getOrder_ID() {
	return Order_ID;
}
public void setOrder_ID(int order_ID) {
	Order_ID = order_ID;
}

private int Product_ID;
public int getProduct_ID() {
	return Product_ID;
}
public void setProduct_ID(int product_ID) {
	Product_ID = product_ID;
}
}
