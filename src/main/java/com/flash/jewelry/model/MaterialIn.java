package com.flash.jewelry.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.flash.jewelry.common.StrConstant;

public class MaterialIn implements Serializable {	
	
	private static final long serialVersionUID = 962850473619412927L;

	private long id = 0;	
	
	@NotEmpty(message="²»ÄÜÎª¿Õ"	)
	private String billNumber;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	//@DateTimeFormat(iso=ISO.DATE)
	@Past	
	private Date bizDate = null;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Past
	private Date createTime = null;
	
	@Valid
	private MaterialInDetail materialInDetail;
	
	private BillStatus billStatus;
	
	@NotEmpty(message=StrConstant.MESSAGE_NOT_EMPTY)
	private String clientName;	
	
	private long clientId;
	
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
	/**
	 * @return the clientId
	 */
	public long getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	
	
	public BillStatus getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(BillStatus billStatus) {
		this.billStatus = billStatus;
	}
	public MaterialInDetail getMaterialInDetail() {
		return materialInDetail;
	}
	public void setMaterialInDetail(MaterialInDetail materialInDetail) {
		this.materialInDetail = materialInDetail;
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
	

}
