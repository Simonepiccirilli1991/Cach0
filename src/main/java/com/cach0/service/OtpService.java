package com.cach0.service;

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

	
	
	public BaseCacheResponse insert(OtpRequest request) {

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
			response.setInsert(false);
			response.setMsg("Error on insert");
			return response;
		}

		response.setInsert(true);
		response.setMsg("T'appo!");

		return response;	
	}
	
	
	public OtpResponse get(String trxId) {
		
		OtpResponse response = new OtpResponse();
		
		OtpDto dto = null;
		try {
			
			dto = otp.get(trxId);
			
		}catch(Exception e) {
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
		
		return response;
	}
	
	
}
