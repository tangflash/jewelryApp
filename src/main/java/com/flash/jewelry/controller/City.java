package com.flash.jewelry.controller;

import java.awt.List;
import java.io.Serializable;
import java.util.Date;

public class City implements Serializable {	
	private static final long serialVersionUID = -6677981125518412869L;
	
	private int id;
	private String name;
	private java.util.List<Country> country;
	private Date lastUpDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.util.List<Country> getCountry() {
		return country;
	}
	public void setCountry(java.util.List<Country> country) {
		this.country = country;
	}
	public Date getLastUpDate() {
		return lastUpDate;
	}
	public void setLastUpDate(Date lastUpDate) {
		this.lastUpDate = lastUpDate;
	}
}
