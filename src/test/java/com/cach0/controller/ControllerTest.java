package com.cach0.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.cach0.confing.OtpConfiguration;
import com.cach0.model.request.OtpRequest;
import com.cach0.model.response.BaseCacheResponse;
import com.cach0.model.response.OtpResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

	@Autowired
	OtpConfiguration otp;
	@Autowired
	MockMvc mvc;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void getCacheTestOK() throws Exception {
		
		OtpRequest request = new OtpRequest();
		request.setBt("bt");
		request.setOtp("1111");
		request.setProfilo("Web");
		request.setTimestamp("timestamp");
		request.setTransactionId("trxId1");
		
		mvc.perform(post("/otp/insert")
				.contentType("application/json")
				.content(mapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		
		
		String resp = mvc.perform(post("/otp/get")
				.contentType("application/json")
				.content("trxId1"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		OtpResponse response = mapper.readValue(resp, OtpResponse.class);
		
		assertThat(response.getBt()).isEqualTo("bt");
		assertThat(response.getProfilo()).isEqualTo("Web");
	}
	
	
	@Test
	public void insertCacheTestOK() throws Exception {
		
		OtpRequest request = new OtpRequest();
		request.setBt("bt");
		request.setOtp("1111");
		request.setProfilo("Web");
		request.setTimestamp("timestamp");
		request.setTransactionId("trxId2");
		
		String resp = mvc.perform(post("/otp/insert")
				.contentType("application/json")
				.content(mapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		BaseCacheResponse response = mapper.readValue(resp, BaseCacheResponse.class);
		
		assertThat(response.getInsert()).isEqualTo(true);
		assertThat(response.getMsg()).isNotNull();
		
	}
	
}
