package com.flash.jewelry.controller;

import java.io.Serializable;
import java.util.Date;

public class Country implements Serializable {

	private int id;
	private String name;
	private Date lastUpdate;
	
	
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
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
