package com.flash.jewelry.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Material implements Serializable {
	
	private static final long serialVersionUID = 3927454206921814825L;
	
	private long id;

	@NotEmpty(message="不能为空")
	private String number;

	@NotEmpty(message="不能为空")
	private String name;

	@NotNull(message="不能为空")
	private int Sort;

	@NotNull(message="不能为空")
	private BigDecimal weight;
	
	public Material(){		
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

	public int getSort() {
		return Sort;
	}

	public void setSort(int sort) {
		Sort = sort;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("materialInfo");
		return sb.toString();
	}
}
