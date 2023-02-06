package com.cach0.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cach0.model.PushDto;
import com.cach0.model.request.OtpRequest;
import com.cach0.model.request.SessionRequest;
import com.cach0.model.response.BaseCacheResponse;
import com.cach0.model.response.OtpResponse;
import com.cach0.model.response.PushResponse;
import com.cach0.model.response.SessionResponse;

@SpringBootTest
public class ServiceTest {

	@Autowired
	OtpService otpService;
	@Autowired
	SessionService sessService;
	@Autowired
	PushService pushService;
	
	@Test
	public void insertCacheTestOK() {
		
		OtpRequest request = new OtpRequest();
		request.setBt("bt");
		request.setOtp("1111");
		request.setProfilo("Web");
		request.setTimestamp("timestamp");
		request.setTimestampDate(LocalDateTime.now());
		request.setTransactionId("trxId2");
		
		BaseCacheResponse resp = otpService.insert(request);
		
		assertThat(resp.getInsert()).isEqualTo(true);
	}
	
	
	@Test
	public void insertEGetCacheTestOK() {
		
		OtpRequest request = new OtpRequest();
		request.setBt("bt");
		request.setOtp("1111");
		request.setProfilo("Web");
		request.setTimestamp("timestamp");
		request.setTimestampDate(LocalDateTime.now());
		request.setTransactionId("trxId1");
		
		otpService.insert(request);
		
		OtpResponse response = otpService.get("trxId1");
		
		assertThat(response.getBt()).isEqualTo("bt");
		assertThat(response.getProfilo()).isEqualTo("Web");
	}
	
//--------------Session test ---------------------------------------------//
	
	@Test
	public void inserteGetSession() {
		
		SessionRequest request = new SessionRequest();
		request.setBt("bt12");
		request.setScope("l1");
		request.setUsername("username1");
		
		SessionResponse resp1 = sessService.insert(request);
		
		assertThat(resp1.getInsert()).isEqualTo(true);
		
		SessionResponse resp2= sessService.get("bt12");
		
		assertThat(resp2.getScope()).isEqualTo("l1");
	}
	
//------------------ Push Test -----------------------------------------//
	@Test
	public void insertEGetPushTestOK() {
		
		PushDto request = new PushDto();
		request.setBandaId("bancaId");
		request.setBt("btPush");
		request.setStatus("pending");
		request.setTime(LocalDateTime.now());
		
		BaseCacheResponse iResp = pushService.insertPush(request);
		
		assertThat(iResp.getInsert()).isTrue();
		
		
		PushResponse response = pushService.getPush("btPush");
		
		assertThat(response.getBandaId()).isEqualTo("bancaId");
		assertThat(response.getStatus()).isEqualTo("pending");
		
	}
	
	@Test
	public void acceptPusheGetTestOK() {
		
		PushDto request = new PushDto();
		request.setBandaId("bancaId");
		request.setBt("btPush2");
		request.setStatus("pending");
		request.setTime(LocalDateTime.now());
		
		BaseCacheResponse iResp = pushService.insertPush(request);
		
		assertThat(iResp.getInsert()).isTrue();
		
		request.setStatus("accept");
		
		pushService.updateSatusPush(request);
		
		assertThat(pushService.getPush("btPush2").getStatus()).isEqualTo("accept");
		
	}
}
