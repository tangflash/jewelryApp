package com.flash.jewelry.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class MaterialInventory implements Serializable {
	
	private static final long serialVersionUID = -5278591819492068934L;
	
	private long id;
	private long materId;
	private String materNum;	
	private int inAmount;
	private BigDecimal inWeight;
	private int outAmount;
	private BigDecimal outWeight;
	private int sort;
	private String clientName;
	
	
	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getMaterNum() {
		return materNum;
	}
	public void setMaterNum(String materNum) {
		this.materNum = materNum;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMaterId() {
		return materId;
	}
	public void setMaterId(long materId) {
		this.materId = materId;
	}
	public int getInAmount() {
		return inAmount;
	}
	public void setInAmount(int inAmount) {
		this.inAmount = inAmount;
	}
	public BigDecimal getInWeight() {
		return inWeight;
	}
	public void setInWeight(BigDecimal inWeight) {
		this.inWeight = inWeight;
	}
	public int getOutAmount() {
		return outAmount;
	}
	public void setOutAmount(int outAmount) {
		this.outAmount = outAmount;
	}
	public BigDecimal getOutWeight() {
		return outWeight;
	}
	public void setOutWeight(BigDecimal outWeight) {
		this.outWeight = outWeight;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}	
}
