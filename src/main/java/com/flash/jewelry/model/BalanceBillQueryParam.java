package com.flash.jewelry.model;

import java.util.Date;

import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class BalanceBillQueryParam {
	private String billNumber;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Past
	private Date bizBeginDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Past
	private Date bizEndDate;
	private String exportFormat;
	
	private int billStatus;
	
	
	
	/**
	 * @return the billStatus
	 */
	public int getBillStatus() {
		return billStatus;
	}
	/**
	 * @param billStatus the billStatus to set
	 */
	public void setBillStatus(int billStatus) {
		this.billStatus = billStatus;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public Date getBizBeginDate() {
		return bizBeginDate;
	}
	public void setBizBeginDate(Date bizBeginDate) {
		this.bizBeginDate = bizBeginDate;
	}
	public Date getBizEndDate() {
		return bizEndDate;
	}
	public void setBizEndDate(Date bizEndDate) {
		this.bizEndDate = bizEndDate;
	}	
	
	/**
	 * @return the exportFormat
	 */
	public String getExportFormat() {
		return exportFormat;
	}
	/**
	 * @param exportFormat the exportFormat to set
	 */
	public void setExportFormat(String exportFormat) {
		this.exportFormat = exportFormat;
	}
}
