package com.fh.entity;

public class AnfiApi {
private String start;


public String getStart() {
	return start;
}
public void setStart(String start) {
	this.start = start;
}

public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
private String to;

public AnfiApi(){
	
}
public AnfiApi(String start,String to){
	this.setStart(start);
	this.setTo(to);
}
}
