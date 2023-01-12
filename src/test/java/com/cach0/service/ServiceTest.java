package com.cach0.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cach0.model.request.OtpRequest;
import com.cach0.model.response.BaseCacheResponse;
import com.cach0.model.response.OtpResponse;

@SpringBootTest
public class ServiceTest {

	@Autowired
	OtpService otpService;
	
	
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
}
