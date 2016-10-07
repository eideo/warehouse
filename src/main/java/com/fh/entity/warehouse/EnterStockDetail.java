package com.fh.entity.warehouse;

public class EnterStockDetail {
	
//	
//	 EnterStock_ID    INT                     NOT NULL, /* 入库单编号 , 主键, 外键 (参照 ENTERSTOCK 表 )*/
//	  Product_ID       INT                     NOT NULL, /* 此种商品编号,主键, 外键 (参照 PRODUCT 表 ) */ 
//	  Quantity         int                     NOT NULL, /* 此种商品数量 */
//	  Price            decimal(15,4)           Not NULL, /* 此种商品参考价格  */
//	  HaveInvoice      tinyint                 NULL,     /* 此种商品有没有开发票 ( 缺省为 0 , 有没有开票 )*/
//	  Durability       datetime(3)             NULL,     /*  保质期*/
//	  Remaining_amount int                     NOT Null, /*剩余数量*/
//	  InvoiceNum       varchar(30)             NULL      /* 发票号 */
	
	private String NAME;
	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	private String SKU;


private int	EnterStock_ID;
public int getEnterStock_ID() {
	return EnterStock_ID;
}

public void setEnterStock_ID(int enterStock_ID) {
	EnterStock_ID = enterStock_ID;
}

public int getProduct_ID() {
	return Product_ID;
}

public void setProduct_ID(int product_ID) {
	Product_ID = product_ID;
}

public String getDurability() {
	return Durability;
}

public void setDurability(String durability) {
	Durability = durability;
}

public int getQuantity() {
	return Quantity;
}

public void setQuantity(int quantity) {
	Quantity = quantity;
}

public int getRemaining_amount() {
	return Remaining_amount;
}

public void setRemaining_amount(int remaining_amount) {
	Remaining_amount = remaining_amount;
}

public double getPrice() {
	return Price;
}

public void setPrice(double price) {
	Price = price;
}

public boolean isHaveInvoice() {
	return HaveInvoice;
}

public void setHaveInvoice(boolean haveInvoice) {
	HaveInvoice = haveInvoice;
}

public String getInvoiceNum() {
	return InvoiceNum;
}

public void setInvoiceNum(String invoiceNum) {
	InvoiceNum = invoiceNum;
}

private int	Product_ID;
private String Durability;


private int	Quantity;
private int	Remaining_amount;
private double Price;

private boolean HaveInvoice;

private String InvoiceNum;
	
	
	

}
