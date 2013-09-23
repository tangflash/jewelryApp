package com.flash.jewelry.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.flash.jewelry.common.StrConstant;

public class BalanceBill implements Serializable {	
	
	private static final long serialVersionUID = 1508547769065692698L;

	private long id = 0;
	
	@NotEmpty(message="²»ÄÜÎª¿Õ"	)
	private String billNumber;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	@Past	
	private Date bizDate = null;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Past
	private Date createTime = null;	
	
	private BillStatus billStatus;
	
	@NotEmpty(message=StrConstant.MESSAGE_NOT_EMPTY)
	private String clientName;	
	
	private long clientId;
	
	private String feeTotalInfor;
	
	private String exportFormat = "xls";
	
	
	public String getExportFormat() {
		return exportFormat;
	}

	public void setExportFormat(String exportFormat) {
		this.exportFormat = exportFormat;
	}

	public String getFeeTotalInfor() {
		return feeTotalInfor;
	}

	public void setFeeTotalInfor(String feeTotalInfor) {
		this.feeTotalInfor = feeTotalInfor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BillStatus getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(BillStatus billStatus) {
		this.billStatus = billStatus;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	
}
