package com.flash.jewelry.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class MaterialInDetail implements Serializable {
	
	private static final long serialVersionUID = -4984975432200297269L;
	
	private long id;	
	private long billId;	
	private long materId;
	
	@Min(value=1,message="必须大于0!")
	private int amount;
	@NotNull(message="不能为空!")
	private BigDecimal weight;
	@NotEmpty(message="不能为空!")
	private String materNum;
	
	public String getMaterNum() {
		return materNum;
	}
	public void setMaterNum(String materNum) {
		this.materNum = materNum;
	}
	
	
	private int sort;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getBillId() {
		return billId;
	}
	public void setBillId(long billId) {
		this.billId = billId;
	}
	public long getMaterId() {
		return materId;
	}
	public void setMaterId(long materId) {
		this.materId = materId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}	
}
