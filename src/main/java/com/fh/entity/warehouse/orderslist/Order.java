package com.fh.entity.warehouse.orderslist;
/*
 * order_id	id
date	date_created
sku	sku
item_total	Item.total
anfacode	Billing. company
Anfa_member	Billing .city

 */

import java.util.List;

/*
 * 
 */
public class Order {
private int id;
private int Dept_ID;
public int getDept_ID() {
	return Dept_ID;
}
public void setDept_ID(int dept_ID) {
	Dept_ID = dept_ID;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDate_created() {
	return date_created;
}
public void setDate_created(String date_created) {
	this.date_created = date_created;
}
private String date_created;


private List<Iterm> line_items;

public List<Iterm> getLine_items() {
	return line_items;
}
public void setLine_items(List<Iterm> line_items) {
	this.line_items = line_items;
}
public Billing getBilling() {
	return billing;
}
public void setBilling(Billing billing) {
	this.billing = billing;
}
private Billing billing;

private String remark;

public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
private int Original_ID;
public int getOriginal_ID() {
	return Original_ID;
}
public void setOriginal_ID(int original_ID) {
	Original_ID = original_ID;
}

public String getTotal() {
	return total;
}
public void setTotal(String total) {
	this.total = total;
}
private String total;
private String status;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

private String date_completed;
public String getDate_completed() {
	return date_completed;
}
public void setDate_completed(String date_completed) {
	this.date_completed = date_completed;
}

//"country": "CN",
//"city": "1",
//"phone": "13517756425",
//"address_1": "玉林市玉州区良江街南环里139号",
//"address_2": "",
//"postcode": "",
//"last_name": "",
//"company": "8609240597",
//"state": "CN21",
//"first_name": "何威源",
//"email": "309902259@qq.com"

















}

