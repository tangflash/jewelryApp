package com.flash.jewelry.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class BaseData implements Serializable {
	
	private static final long serialVersionUID = -7646083465567486241L;

	private long id;

	@NotEmpty(message="不能为空")
	private String number;

	@NotEmpty(message="不能为空")
	private String name;	
	
	private String remark;

	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BaseData(){		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
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

	public String toString() {
		return this.name;
	}
}
