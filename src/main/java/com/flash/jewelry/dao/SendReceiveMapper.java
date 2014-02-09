package com.flash.jewelry.dao;

import java.util.Collection;

import com.flash.jewelry.model.MaterialInQueryParam;
import com.flash.jewelry.model.SendReceiveDetail;

public interface SendReceiveMapper {
	
	Collection<SendReceiveDetail> findList(MaterialInQueryParam queryParam);

	void deleteDetail(Long detailId);

	SendReceiveDetail selectDetailById(String detailId);
	
}
