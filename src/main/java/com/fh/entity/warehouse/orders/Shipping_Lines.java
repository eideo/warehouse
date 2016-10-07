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
public class Shipping_Lines {

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getMethod_id() {
		return method_id;
	}
	public void setMethod_id(Date method_id) {
		this.method_id = method_id;
	}
	public String getMethod_title() {
		return method_title;
	}
	public void setMethod_title(String method_title) {
		this.method_title = method_title;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	private int id;
    @JsonProperty("method_id")
    private Date method_id;
    @JsonProperty("method_title")
    private String method_title;
    private String total;
   

    

}