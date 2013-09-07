package com.flash.jewelry.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.flash.jewelry.common.StrConstant;

public class MaterialOut implements Serializable {	
	
	private static final long serialVersionUID = 962850473619412927L;

	private long id = 0;	
	
	@NotEmpty(message="不能为空"	)
	private String billNumber;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Past	
	private Date bizDate = null;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	
	private Date createTime = new Date();
	
	@NotEmpty(message=StrConstant.MESSAGE_NOT_EMPTY)
	private String clientName;	
	
	private long clientId;
	
	@NotEmpty(message=StrConstant.MESSAGE_NOT_EMPTY)
	private String goldTypeName;
	
	private long goldTypeId;
	
	@DecimalMin(value="0",message=StrConstant.MESSAGE_NOT_EMPTY)
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal goldPrice;	
	
	
	@Valid
	private MaterialOutDetail materialOutDetail;
	
	private BillStatus billStatus;
	
	
	public MaterialOut(){
		materialOutDetail = new MaterialOutDetail();
		billStatus = new BillStatus();
		billStatus.setNumber("0");
		billStatus.setName("未提交");
	}
	
	public BillStatus getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(BillStatus billStatus) {
		this.billStatus = billStatus;
	}
	
	public MaterialOutDetail getMaterialOutDetail() {
		return materialOutDetail;
	}
	public void setMaterialOutDetail(MaterialOutDetail materialOutDetail) {
		this.materialOutDetail = materialOutDetail;
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
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String client) {
		this.clientName = client;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getGoldTypeName() {
		return goldTypeName;
	}

	public void setGoldTypeName(String goldType) {
		this.goldTypeName = goldType;
	}

	public long getGoldTypeId() {
		return goldTypeId;
	}

	public void setGoldTypeId(long goldTypeId) {
		this.goldTypeId = goldTypeId;
	}

	public BigDecimal getGoldPrice() {
		return goldPrice;
	}

	public void setGoldPrice(BigDecimal goldPrice) {
		this.goldPrice = goldPrice;
	}

}
