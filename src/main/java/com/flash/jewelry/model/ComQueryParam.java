package com.flash.jewelry.model;

public class ComQueryParam {
	private String materNum;
	private long clientId;
	private String clientName;
	
	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}


	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getMaterNum() {
		return materNum;
	}

	public void setMaterNum(String materNum) {
		this.materNum = materNum;
	}
}
