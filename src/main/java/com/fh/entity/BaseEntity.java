package com.fh.entity;

import java.io.Serializable;

public class BaseEntity implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 	“api_version”: integer,
    “action_version”: integer
    “action”: object
     */
	
	private int api_version;
	public int getApi_version() {
		return api_version;
	}
	public void setApi_version(int api_version) {
		this.api_version = api_version;
	}
	public int getAction_version() {
		return action_version;
	}
	public void setAction_version(int action_version) {
		this.action_version = action_version;
	}
	private int action_version;

}
