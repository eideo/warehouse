package com.fh.entity.warehouse;

public class StockPile {
//	StockPile_ID     INT AUTO_INCREMENT       NOT NULL, /* 库存编号 , 主键 */
//	  Dept_ID          INT                      NOT NULL, /* 商品所属商户, 外键 ( 参照 DEPT 表 ) */  
//	  StoreHouse_ID    INT                      NOT NULL, /* 所在仓库,     外键 ( 参照 SOTREHOUSE 表 ) */   
//	  Product_ID       INT                      NOT NULL, /* 商品编号,     外键 ( 参照 PRODUCT 表 ) */   
//	 
	//FirstEnterDate   datetime(3)              NOT NULL, /* 此种商品第一次入库时间 */
//	  LastLeaveDate    datetime(3)              NULL,     /* 此种商品最后一次出库时间 */
//	  Quantity         int                      NOT NULL, /* 所存数量 */
//	  Price            decimal(15,4)            NOT NULL , /* 加权价 */
	
	
	private int StockPile_ID;
	
	public int getStockPile_ID() {
		return StockPile_ID;
	}

	public void setStockPile_ID(int stockPile_ID) {
		StockPile_ID = stockPile_ID;
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

	public int getProduct_ID() {
		return Product_ID;
	}

	public void setProduct_ID(int product_ID) {
		Product_ID = product_ID;
	}

	public String getFirstEnterDate() {
		return FirstEnterDate;
	}

	public void setFirstEnterDate(String firstEnterDate) {
		FirstEnterDate = firstEnterDate;
	}

	public String getLastLeaveDate() {
		return LastLeaveDate;
	}

	public void setLastLeaveDate(String lastLeaveDate) {
		LastLeaveDate = lastLeaveDate;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	private int Dept_ID;
	private int StoreHouse_ID;
	
	private int Product_ID;
	
	
	
	
	private String FirstEnterDate;
	
	private String LastLeaveDate;
	private int Quantity;
	
	private double Price;
	
	
	
}
