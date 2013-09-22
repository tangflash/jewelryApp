package com.flash.jewelry.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.flash.jewelry.common.StrConstant;

public class MaterialOutDetail {
	private long id;	
	private long billId;	
	private long materId;
	
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	private int amount;
	
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.000")
	private BigDecimal weight;
	
	//@NotEmpty(message=StrConstant.MESSAGE_NOT_EMPTY)
	private String materName;
	
	@NotEmpty(message=StrConstant.MESSAGE_NOT_EMPTY)
	private String styleName;
	
	private long styleId;
	
	private long ProductNameId;
	//@NotEmpty(message=StrConstant.MESSAGE_NOT_EMPTY)
	private String productName;
	
	@Min(value=0,message=StrConstant.MESSAGE_GREATER_ZERO)
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal handSize;
	
	private int sort;
	
	@Min(value=1,message=StrConstant.MESSAGE_GREATER_ZERO)
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	private int productAmount;
	
	@DecimalMin(value="0",message=StrConstant.MESSAGE_GREATER_ZERO)
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal productWeight;
	
	@DecimalMin(value="0",message=StrConstant.MESSAGE_GREATER_ZERO)
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal goldWeight;
	
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal goldMoney;
	
	@DecimalMin(value="0",message=StrConstant.MESSAGE_GREATER_ZERO)
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal consumeWeight;
	
	@DecimalMin(value="0",message=StrConstant.MESSAGE_GREATER_ZERO)
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal processCost; 
	
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal totalProcessCost; 
	
	@DecimalMin(value="0",message=StrConstant.MESSAGE_GREATER_ZERO)
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal addProcessCost;
	
	@DecimalMin(value="0",message=StrConstant.MESSAGE_GREATER_ZERO)
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal superSetCost;
	
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal factoryAddMoney;
	
	private long secMaterId;
	
	private String secMaterName;	
	
	private int secAmount;
	
	@DecimalMin(value="0",message=StrConstant.MESSAGE_GREATER_ZERO)
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.000")
	private BigDecimal secWeight;
	
	@DecimalMin(value="0",message=StrConstant.MESSAGE_GREATER_ZERO)
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal secPrice;
	
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal secMaterMoney;
	
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal totalMoney;	
	
	private MaterialOut materialOut;
	
	private int backAmount;
	
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.000")
	private BigDecimal backWeight;
	
	@DecimalMin(value="0",message=StrConstant.MESSAGE_GREATER_ZERO)
	@DecimalMax(value="100",message=StrConstant.MESSAGE_LESS_THAN_HUNDRED)
	@NotNull(message=StrConstant.MESSAGE_NOT_EMPTY)
	@NumberFormat(style=Style.NUMBER, pattern="#,##0.00")
	private BigDecimal loss;
	
	private String groupName;
	
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public BigDecimal getLoss() {
		return loss;
	}
	public void setLoss(BigDecimal loss) {
		this.loss = loss;
	}
	/**
	 * @return the backAmount
	 */
	public int getBackAmount() {
		return backAmount;
	}
	/**
	 * @param backAmount the backAmount to set
	 */
	public void setBackAmount(int backAmount) {
		this.backAmount = backAmount;
	}
	/**
	 * @return the backWeight
	 */
	public BigDecimal getBackWeight() {
		return backWeight;
	}
	/**
	 * @param backWeight the backWeight to set
	 */
	public void setBackWeight(BigDecimal backWeight) {
		this.backWeight = backWeight;
	}
	
	
	/**
	 * @return the materialOut
	 */
	public MaterialOut getMaterialOut() {
		return materialOut;
	}
	/**
	 * @param materialOut the materialOut to set
	 */
	public void setMaterialOut(MaterialOut materialOut) {
		this.materialOut = materialOut;
	}
	/**
	 * @return the secMaterNum
	 */
	public String getSecMaterName() {
		return secMaterName;
	}
	/**
	 * @param secMaterNum the secMaterNum to set
	 */
	public void setSecMaterName(String secMaterName) {
		this.secMaterName = secMaterName;
	}
	
	/**
	 * @return the styleName
	 */
	public String getStyleName() {
		return styleName;
	}
	/**
	 * @param styleName the styleName to set
	 */
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	/**
	 * @return the styleId
	 */
	public long getStyleId() {
		return styleId;
	}
	/**
	 * @param styleId the styleId to set
	 */
	public void setStyleId(long styleId) {
		this.styleId = styleId;
	}
	/**
	 * @return the productNameId
	 */
	public long getProductNameId() {
		return ProductNameId;
	}
	/**
	 * @param productNameId the productNameId to set
	 */
	public void setProductNameId(long productNameId) {
		ProductNameId = productNameId;
	}
	/**
	 * @return the handSize
	 */
	public BigDecimal getHandSize() {
		return handSize;
	}
	/**
	 * @param handSize the handSize to set
	 */
	public void setHandSize(BigDecimal handSize) {
		this.handSize = handSize;
	}
	/**
	 * @return the productAmount
	 */
	public int getProductAmount() {
		return productAmount;
	}
	/**
	 * @param productAmount the productAmount to set
	 */
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}
	/**
	 * @return the productWeight
	 */
	public BigDecimal getProductWeight() {
		return productWeight;
	}
	/**
	 * @param productWeight the productWeight to set
	 */
	public void setProductWeight(BigDecimal productWeight) {
		this.productWeight = productWeight;
	}
	/**
	 * @return the glodWeight
	 */
	public BigDecimal getGoldWeight() {
		return goldWeight;
	}
	/**
	 * @param glodWeight the glodWeight to set
	 */
	public void setGoldWeight(BigDecimal goldWeight) {
		this.goldWeight = goldWeight;
	}
	/**
	 * @return the consumeWeight
	 */
	public BigDecimal getConsumeWeight() {
		return consumeWeight;
	}
	/**
	 * @param consumeWeight the consumeWeight to set
	 */
	public void setConsumeWeight(BigDecimal consumeWeight) {
		this.consumeWeight = consumeWeight;
	}
	/**
	 * @return the processCost
	 */
	public BigDecimal getProcessCost() {
		return processCost;
	}
	/**
	 * @param processCost the processCost to set
	 */
	public void setProcessCost(BigDecimal processCost) {
		this.processCost = processCost;
	}
	/**
	 * @return the addProcessCost
	 */
	public BigDecimal getAddProcessCost() {
		return addProcessCost;
	}
	/**
	 * @param addProcessCost the addProcessCost to set
	 */
	public void setAddProcessCost(BigDecimal addProcessCost) {
		this.addProcessCost = addProcessCost;
	}
	/**
	 * @return the superSetCost
	 */
	public BigDecimal getSuperSetCost() {
		return superSetCost;
	}
	/**
	 * @param superSetCost the superSetCost to set
	 */
	public void setSuperSetCost(BigDecimal superSetCost) {
		this.superSetCost = superSetCost;
	}
	/**
	 * @return the factoryAddMoney
	 */
	public BigDecimal getFactoryAddMoney() {
		return factoryAddMoney;
	}
	/**
	 * @param factoryAddMoney the factoryAddMoney to set
	 */
	public void setFactoryAddMoney(BigDecimal factoryAddMoney) {
		this.factoryAddMoney = factoryAddMoney;
	}
	/**
	 * @return the secMaterId
	 */
	public long getSecMaterId() {
		return secMaterId;
	}
	/**
	 * @param secMaterId the secMaterId to set
	 */
	public void setSecMaterId(long secMaterId) {
		this.secMaterId = secMaterId;
	}
	/**
	 * @return the secAmount
	 */
	public int getSecAmount() {
		return secAmount;
	}
	/**
	 * @param secAmount the secAmount to set
	 */
	public void setSecAmount(int secAmount) {
		this.secAmount = secAmount;
	}
	/**
	 * @return the secWeight
	 */
	public BigDecimal getSecWeight() {
		return secWeight;
	}
	/**
	 * @param secWeight the secWeight to set
	 */
	public void setSecWeight(BigDecimal secWeight) {
		this.secWeight = secWeight;
	}
	/**
	 * @return the secPrice
	 */
	public BigDecimal getSecPrice() {
		return secPrice;
	}
	/**
	 * @param secPrice the secPrice to set
	 */
	public void setSecPrice(BigDecimal secPrice) {
		this.secPrice = secPrice;
	}
		
	
	public String getMaterName() {
		return materName;
	}
	public void setMaterName(String materName) {
		this.materName = materName;
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
	
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * @return the goldMoney
	 */
	public BigDecimal getGoldMoney() {
		return goldMoney;
	}
	/**
	 * @param goldMoney the goldMoney to set
	 */
	public void setGoldMoney(BigDecimal goldMoney) {
		this.goldMoney = goldMoney;
	}
	
	/**
	 * @return the secMaterMoney
	 */
	public BigDecimal getSecMaterMoney() {
		return secMaterMoney;
	}
	/**
	 * @param secMaterMoney the secMaterMoney to set
	 */
	public void setSecMaterMoney(BigDecimal secMaterMoney) {
		this.secMaterMoney = secMaterMoney;
	}
	
	/**
	 * @return the totalMoney
	 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	/**
	 * @param totalMoney the totalMoney to set
	 */
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	/**
	 * @return the totalProcessCost
	 */
	public BigDecimal getTotalProcessCost() {
		return totalProcessCost;
	}
	/**
	 * @param totalProcessCost the totalProcessCost to set
	 */
	public void setTotalProcessCost(BigDecimal totalProcessCost) {
		this.totalProcessCost = totalProcessCost;
	}
}
