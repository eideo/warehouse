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
public class Tax_Lines {

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRate_id() {
		return rate_id;
	}
	public void setRate_id(String rate_id) {
		this.rate_id = rate_id;
	}
	public Date getCode() {
		return code;
	}
	public void setCode(Date code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public boolean isCompound() {
		return compound;
	}
	public void setCompound(boolean compound) {
		this.compound = compound;
	}
	private int id;
    @JsonProperty("rate_id")
    private String rate_id;
    private Date code;
    private String title;
    private String total;
    private boolean compound;
  
}