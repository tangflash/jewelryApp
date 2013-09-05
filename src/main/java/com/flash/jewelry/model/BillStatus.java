package com.flash.jewelry.model;

import java.io.Serializable;

public class BillStatus implements Serializable {
	
	private static final long serialVersionUID = -9086448452235496101L;
	private String number;
	private String name;
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
