package com.cach0.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.cach0.confing.PushConfiguration;
import com.cach0.model.PushDto;
import com.cach0.model.response.BaseCacheResponse;
import com.cach0.model.response.PushResponse;

@Service
public class PushService {

	@Autowired
	PushConfiguration push;
	
	Logger logger = LoggerFactory.getLogger(PushService.class);
	
	
	// INSERT push
	public BaseCacheResponse insertPush(PushDto request) {
		
		logger.info("API: PushService - insertPush - START with raw request: {}", request);
		
		BaseCacheResponse response = new BaseCacheResponse();
		//TODO quando implementi header ecc, passare chiave come header
		try {
			push.insert(request.getBt(), request);
		}catch(Exception e) {
			logger.error("Error on PushService - insert", e);
			response.setInsert(false);
			response.setMsg("error on insert push");
			return response;
		}
		response.setInsert(true);
		response.setMsg("Push insert");
		logger.info("API: PushService - insert - END with response: {}", response);
		
		return response;
	}
	//PUT push
	public BaseCacheResponse acceptPush(PushDto request) {

		logger.info("API: PushService - acceptPush - START with raw request: {}", request);
		
		BaseCacheResponse response = new BaseCacheResponse();

		try {
			push.put(request.getBt(), request);

		}catch(Exception e) {
			logger.error("Error on PushService - accept", e);
			response.setInsert(false);
			response.setMsg("error on updating push");
			return response;
		}

		response.setInsert(true);
		response.setMsg("Push updated");
		logger.info("API: PushService - accept - END with response: {}", response);
		
		return response;
	}
	// GET push
	public PushResponse getPush(String bt) {

		logger.info("API: PushService - getPush - START with raw request: {}", bt);
		
		PushResponse response = new PushResponse();

		try {
			PushDto dto = push.get(bt);

			if(ObjectUtils.isEmpty(dto)) {
				logger.info("API: PushService - getPush - END cause no object found with this trxId: {}", bt);
				response.setNoFound(true);
				response.setMsg("Push no found");
				return response;
			}

			response.setBt(dto.getBt());
			response.setBandaId(dto.getBandaId());
			response.setStatus(dto.getStatus());
			response.setTime(dto.getTime());

		}catch(Exception e) {
			logger.error("Error on PushService - get:", e);
			response.setNoFound(true);
			response.setMsg("Push Error on get");
			return response;
		}
		
		logger.info("API: PushService - get - END with response: {}", response);
		return response;
	}
	//PUT push
	public BaseCacheResponse updateSatusPush(PushDto request) {
		
		logger.info("API: PushService - update - START with raw request: {}", request);
		BaseCacheResponse response = new BaseCacheResponse();
		
		try {
			push.put(request.getBt(), request);
		}catch(Exception e) {
			logger.error("Error on PushService - update:", e);
			response.setInsert(false);
			response.setMsg("Error on updating push status");
			return response;
		}
		
		response.setInsert(true);
		response.setMsg("status push updated");
		logger.info("API: PushService - update - END with response: {}", response);
		
		return response;
	}
}
