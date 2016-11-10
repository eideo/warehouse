package com.fh.entity;


public class TestEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8482101173679454854L;
	/**
	 * 
	 */

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String role;
	private String name;
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
	

	private String SKU;
	public String getSKU() {
		return SKU;
	}
	public void setSKU(String sKU) {
		SKU = sKU;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getName_CN() {
		return Name_CN;
	}
	public void setName_CN(String name_CN) {
		Name_CN = name_CN;
	}
	private String NAME;
	private String Name_CN;


}
