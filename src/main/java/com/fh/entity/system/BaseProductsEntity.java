package com.fh.entity.system;

public class BaseProductsEntity {
//	#{Name},
//	#{NameCN},
//	#{SKU}
private String SKU;
public String getSKU() {
	return SKU;
}
public void setSKU(String sKU) {
	SKU = sKU;
}


private String Name;
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
private String NameCN;
public String getNameCN() {
	return NameCN;
}
public void setNameCN(String nameCN) {
	NameCN = nameCN;
}

}
