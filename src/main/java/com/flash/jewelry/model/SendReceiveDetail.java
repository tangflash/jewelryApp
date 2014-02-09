package com.flash.jewelry.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.exolab.castor.types.DateTime;

public class SendReceiveDetail implements Serializable {
	
	private static final long serialVersionUID = -1545388517959348610L;
	
	private long id;
	private MaterialOut materialOut;
	
	private  BaseData department;
		

	private Date sendDate;
	private int sendAmount;
	private BigDecimal sendWeight;
	private Person sendSignIn;
	
	private int scrapAmount;
	private BigDecimal scrapWeight;
	private Person qc;
	
	private Date receiveDate;
	private int receiveAmount;
	private BigDecimal receiveWeight;
	private Person receiveSign;
	
	private String remark;
	
	
	public BaseData getDepartment() {
		return department;
	}

	public void setDepartment(BaseData department) {
		this.department = department;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MaterialOut getMaterialOut() {
		return materialOut;
	}

	public void setMaterialOut(MaterialOut materialOut) {
		this.materialOut = materialOut;
	}
	

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public int getSendAmount() {
		return sendAmount;
	}

	public void setSendAmount(int sendAmount) {
		this.sendAmount = sendAmount;
	}

	public BigDecimal getSendWeight() {
		return sendWeight;
	}

	public void setSendWeight(BigDecimal sendWeight) {
		this.sendWeight = sendWeight;
	}

	public Person getSendSignIn() {
		return sendSignIn;
	}

	public void setSendSignIn(Person sendSignIn) {
		this.sendSignIn = sendSignIn;
	}

	public int getScrapAmount() {
		return scrapAmount;
	}

	public void setScrapAmount(int scrapAmount) {
		this.scrapAmount = scrapAmount;
	}

	public BigDecimal getScrapWeight() {
		return scrapWeight;
	}

	public void setScrapWeight(BigDecimal scrapWeight) {
		this.scrapWeight = scrapWeight;
	}

	public Person getQc() {
		return qc;
	}

	public void setQc(Person qc) {
		this.qc = qc;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public int getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(int receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	public BigDecimal getReceiveWeight() {
		return receiveWeight;
	}

	public void setReceiveWeight(BigDecimal receiveWeight) {
		this.receiveWeight = receiveWeight;
	}

	public Person getReceiveSign() {
		return receiveSign;
	}

	public void setReceiveSign(Person receiveSign) {
		this.receiveSign = receiveSign;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
