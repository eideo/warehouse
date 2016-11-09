package com.fh.entity.warehouse;

import java.util.List;

public class EnterStock {
	public List<EnterStockDetail> getEnterStockDetailList() {
		return enterStockDetailList;
	}
	public void setEnterStockDetailList(List<EnterStockDetail> enterStockDetailList) {
		this.enterStockDetailList = enterStockDetailList;
	}
	public int getEnterStock_ID() {
		return EnterStock_ID;
	}
	public void setEnterStock_ID(int enterStock_ID) {
		EnterStock_ID = enterStock_ID;
	}
	public int getDept_ID() {
		return Dept_ID;
	}
	public void setDept_ID(int dept_ID) {
		Dept_ID = dept_ID;
	}
	public int getStoreHouse_ID() {
		return StoreHouse_ID;
	}
	public void setStoreHouse_ID(int storeHouse_ID) {
		StoreHouse_ID = storeHouse_ID;
	}
	public int getEmployee_ID() {
		return Employee_ID;
	}
	public void setEmployee_ID(int employee_ID) {
		Employee_ID = employee_ID;
	}
	public String getEnterDate() {
		return EnterDate;
	}
	public void setEnterDate(String enterDate) {
		EnterDate = enterDate;
	}
	private List<EnterStockDetail> enterStockDetailList;
//	EnterStock_ID    INT AUTO_INCREMENT      NOT NULL, /* 入库单编号 , 主键 */
//	  Dept_ID         int                      NOT NULL,  /* 商户编号,主键 ,  外键( 参照 DEPT 表  )*/
//	  EnterDate        Timestamp               NOT NULL default current_timestamp,   /* 入库时间 */
//	  StoreHouse_ID    INT                     NOT NULL, /* 所入仓库 ,外键 ( 参照 STOREHOUSE 表)*/
//	  Employee_ID      INT                     NULL,  /* 入库人 ,  外键 ( 参照 EMPLOYEE 表)*/
//
	private int EnterStock_ID;
	public int getLeaveStock_ID() {
		return LeaveStock_ID;
	}
	public void setLeaveStock_ID(int leaveStock_ID) {
		LeaveStock_ID = leaveStock_ID;
	}
	public String getLeaveDate() {
		return LeaveDate;
	}
	public void setLeaveDate(String leaveDate) {
		LeaveDate = leaveDate;
	}
	private int LeaveStock_ID;
	private String LeaveDate;
	private int Dept_ID;
	private int StoreHouse_ID;
	private int Employee_ID;
	private String EnterDate;
	public String getOrder_ID() {
		return Order_ID;
	}
	public void setOrder_ID(String order_ID) {
		Order_ID = order_ID;
	}
	private String Order_ID;

	

}
