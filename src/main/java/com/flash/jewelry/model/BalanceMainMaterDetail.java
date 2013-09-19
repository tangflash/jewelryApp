package com.flash.jewelry.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BalanceMainMaterDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5467579163997684929L;
	
	private long id = 0;
	private long billId;
	private long materId;
	private String materName;
	
	private int priorAmount;
	private BigDecimal priorWeight;
	
	private int inAmount;
	private BigDecimal inWeight;
	
	private int curAmount;
	private BigDecimal curWeight;
	
	private String materUsedInfor;
	
	
	public String getMaterName() {
		return materName;
	}
	public void setMaterName(String materName) {
		this.materName = materName;
	}
	
	public String getMaterUsedInfor() {
		return materUsedInfor;
	}
	public void setMaterUsedInfor(String materUsedInfor) {
		this.materUsedInfor = materUsedInfor;
	}
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
	public int getPriorAmount() {
		return priorAmount;
	}
	public void setPriorAmount(int priorAmount) {
		this.priorAmount = priorAmount;
	}
	public BigDecimal getPriorWeight() {
		return priorWeight;
	}
	public void setPriorWeight(BigDecimal priorWeight) {
		this.priorWeight = priorWeight;
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
	public int getCurAmount() {
		return curAmount;
	}
	public void setCurAmount(int curAmount) {
		this.curAmount = curAmount;
	}
	public BigDecimal getCurWeight() {
		return curWeight;
	}
	public void setCurWeight(BigDecimal curWeight) {
		this.curWeight = curWeight;
	}
		

}
