package com.fh.entity.warehouse;

public class Clients {
//	
//	
//	 Name             varchar(30)              NOT NULL, /* 名称 */
//	  Domain_Name      varchar(30)              NOT NULL, /*域名*/
//	  Consumer_Key     varchar(30)              NULL,
//	  Consumer_Secret  varchar(30)              NULL,
//	  Address          varchar(256)             NULL, /* 地址 */
//	  Phone            varchar(32)              NULL,     /* 电话 */
//	  Remark           varchar(250)             NULL,/* 描述,备注 */ 
//	  CreateDate        datetime(3)             Not NULL default current_timestamp,   
//	  Status           CHAR(1)                  NOT Null,
//	
	private String NAME;
	
	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}


	public String getDomain_Name() {
		return Domain_Name;
	}

	public void setDomain_Name(String domain_Name) {
		Domain_Name = domain_Name;
	}

	public String getConsumer_Key() {
		return Consumer_Key;
	}

	public void setConsumer_Key(String consumer_Key) {
		Consumer_Key = consumer_Key;
	}

	public String getConsumer_Secret() {
		return Consumer_Secret;
	}

	public void setConsumer_Secret(String consumer_Secret) {
		Consumer_Secret = consumer_Secret;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	private String Domain_Name;
	
	private String Consumer_Key;
	
	private String Consumer_Secret;
	
	private String Address;
	
	private String Phone;
	
	private String Remark;
	
	private String CreateDate;
	
	private String Status;
	



	public int getDEPT_ID() {
		return DEPT_ID;
	}

	public void setDEPT_ID(int dEPT_ID) {
		DEPT_ID = dEPT_ID;
	}

	private int DEPT_ID;
	

}
