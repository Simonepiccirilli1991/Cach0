package com.cach0.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.cach0.confing.SessionConfiguration;
import com.cach0.model.SessionDto;
import com.cach0.model.request.SessionRequest;
import com.cach0.model.response.SessionResponse;

@Service
public class SessionService {

	@Autowired
	SessionConfiguration sessionSer;
	
	public SessionResponse insert(SessionRequest request) {
		
		SessionResponse response = new SessionResponse();
		SessionDto session = new SessionDto();
		
		session.setBt(request.getBt());
		session.setScope(request.getScope());
		session.setUsername(request.getUsername());
		session.setTimestampDate(LocalDateTime.now());
		
		try {
			sessionSer.insert(request.getBt(), session);
			
		}catch(Exception e) {
			
			response.setInsert(false);
			response.setMsg("Error on insert");
			return response;
		}
		
		response.setInsert(true);
		response.setMsg("T'appo!");
		
		return response;
		
	}
	
	public SessionResponse get(String bt) {

		SessionResponse response = new SessionResponse();

		SessionDto dto = null;
		try {

			dto = sessionSer.get(bt);

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
			response.setScope(dto.getScope());
			response.setValid(true);
			response.setMsg("T'appo!");
		}

		return response;
	}
	
	public SessionResponse updateSession(SessionRequest request) {
		
		SessionResponse response = new SessionResponse();
		
		SessionDto dto = null;
		try {

			dto = sessionSer.get(request.getBt());
			
			dto.setScope(request.getScope());
			sessionSer.put(request.getBt(), dto);
			
		}catch(Exception e) {
			response.setNoFound(true);
			response.setMsg("error on retryving on cache");
			return response;
		}
		
		response.setInsert(true);
		response.setValid(true);
		response.setMsg("T'appo!");
		return response;
	}
}
