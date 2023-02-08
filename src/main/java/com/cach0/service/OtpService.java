package com.cach0.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.cach0.confing.OtpConfiguration;
import com.cach0.model.OtpDto;
import com.cach0.model.request.OtpRequest;
import com.cach0.model.response.BaseCacheResponse;
import com.cach0.model.response.OtpResponse;

@Service
public class OtpService {

	@Autowired
	OtpConfiguration otp;
	
	Logger logger = LoggerFactory.getLogger(OtpService.class);
	
	
	public BaseCacheResponse insert(OtpRequest request) {
		
		logger.info("API: OtpService - insert - START with raw request: {}", request);
		BaseCacheResponse response = new BaseCacheResponse();

		String trxId = request.getTransactionId();

		OtpDto dto = new OtpDto();
		dto.setBt(request.getBt());
		dto.setOtp(request.getOtp());
		dto.setProfilo(request.getProfilo());
		dto.setTimestamp(request.getTimestamp());
		dto.setTimestampDate(request.getTimestampDate());

		try {

			otp.insert(trxId, dto);

		}catch(Exception e) {
			logger.error("Error on OtpService - insert", e);
			response.setInsert(false);
			response.setMsg("Error on insert");
			return response;
		}

		response.setInsert(true);
		response.setMsg("T'appo!");
		logger.info("API: OtpService - insert - END with response: {}", response);
		
		return response;	
	}
	
	
	public OtpResponse get(String trxId) {
		
		logger.info("API: OtpService - get - START with raw request: {}", trxId);
		OtpResponse response = new OtpResponse();
		
		OtpDto dto = null;
		try {
			
			dto = otp.get(trxId);
			
		}catch(Exception e) {
			logger.error("Error on OtpService - get", e);
			response.setNoFound(true);
			response.setMsg("error on retryving on cache");
			return response;
		}
		
		if(ObjectUtils.isEmpty(dto)) {
			response.setNoFound(true);
			response.setMsg("No data found on cache with this trxId");
		}
		else {
			response.setBt(dto.getBt());
			response.setInsert(true);
			response.setOtp(dto.getOtp());
			response.setProfilo(dto.getProfilo());
			response.setTimestamp(dto.getTimestamp());
			response.setTimestampDate(dto.getTimestampDate());
			response.setMsg("T'appo!");
		}
		logger.info("API: OtpService - get - END with response: {}", response);
		
		return response;
	}
	
	
}
