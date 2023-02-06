package com.cach0.service;

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
	// INSERT push
	public BaseCacheResponse insertPush(PushDto request) {

		BaseCacheResponse response = new BaseCacheResponse();
		//TODO quando implementi header ecc, passare chiave come header
		try {
			push.insert(request.getBt(), request);
		}catch(Exception e) {
			response.setInsert(false);
			response.setMsg("error on insert push");
			return response;
		}
		response.setInsert(true);
		response.setMsg("Push insert");
		return response;
	}
	//PUT push
	public BaseCacheResponse acceptPush(PushDto request) {

		BaseCacheResponse response = new BaseCacheResponse();

		try {
			push.put(request.getBt(), request);

		}catch(Exception e) {
			response.setInsert(false);
			response.setMsg("error on updating push");
			return response;
		}

		response.setInsert(true);
		response.setMsg("Push updated");
		return response;
	}
	// GET push
	public PushResponse getPush(String bt) {

		PushResponse response = new PushResponse();

		try {
			PushDto dto = push.get(bt);

			if(ObjectUtils.isEmpty(dto)) {
				response.setNoFound(true);
				response.setMsg("Push no found");
				return response;
			}

			response.setBt(dto.getBt());
			response.setBandaId(dto.getBandaId());
			response.setStatus(dto.getStatus());
			response.setTime(dto.getTime());

		}catch(Exception e) {
			response.setNoFound(true);
			response.setMsg("Push Error on get");
			return response;
		}
		return response;
	}
	//PUT push
	public BaseCacheResponse updateSatusPush(PushDto request) {
		
		BaseCacheResponse response = new BaseCacheResponse();
		
		try {
			push.put(request.getBt(), request);
		}catch(Exception e) {
			response.setInsert(false);
			response.setMsg("Error on updating push status");
			return response;
		}
		
		response.setInsert(true);
		response.setMsg("status push updated");
		return response;
	}
}
