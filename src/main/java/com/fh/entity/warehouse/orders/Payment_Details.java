/**
  * Copyright 2016 aTool.org 
  */
package com.fh.entity.warehouse.orders;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2016-09-26 15:42:20
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Payment_Details {

    public String getMethod_id() {
		return method_id;
	}
	public void setMethod_id(String method_id) {
		this.method_id = method_id;
	}
	public String getMethod_title() {
		return method_title;
	}
	public void setMethod_title(String method_title) {
		this.method_title = method_title;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	@JsonProperty("method_id")
    private String method_id;
    @JsonProperty("method_title")
    private String method_title;
    private boolean paid;
  

}