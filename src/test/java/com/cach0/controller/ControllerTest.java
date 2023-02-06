package com.cach0.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.cach0.confing.OtpConfiguration;
import com.cach0.confing.PushConfiguration;
import com.cach0.model.PushDto;
import com.cach0.model.request.OtpRequest;
import com.cach0.model.response.BaseCacheResponse;
import com.cach0.model.response.OtpResponse;
import com.cach0.model.response.PushResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

	@Autowired
	PushConfiguration push;
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
	
	//-------------------- Push controller Test -------------------------------------------------//
	
	@Test
	public void insertEGetPushCacheTestOK() throws Exception {
		
		PushDto request = new PushDto();
		request.setBandaId("bancaId");
		request.setBt("btPush");
		request.setStatus("pending");
		
		String resp = mvc.perform(post("/push/insert")
				.contentType("application/json")
				.content(mapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		BaseCacheResponse iResp = mapper.readValue(resp, BaseCacheResponse.class);
		
		assertThat(iResp.getInsert()).isTrue();
		
		String resp1 = mvc.perform(get("/push/get/btPush")
				.contentType("application/json"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		PushResponse response = mapper.readValue(resp1, PushResponse.class);
		
		assertThat(response.getBandaId()).isEqualTo("bancaId");
		assertThat(response.getStatus()).isEqualTo("pending");
		
		
	}
	
	
	@Test
	public void getEAcceptPushTestOK() throws Exception {
		
		PushDto request = new PushDto();
		request.setBandaId("bancaId");
		request.setBt("btPush2");
		request.setStatus("pending");
		
		String resp = mvc.perform(post("/push/insert")
				.contentType("application/json")
				.content(mapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		BaseCacheResponse iResp = mapper.readValue(resp, BaseCacheResponse.class);
		
		assertThat(iResp.getInsert()).isTrue();
		
		request.setStatus("accept");
		
		String resp1 = mvc.perform(put("/push/update")
				.contentType("application/json")
				.content(mapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String resp2 = mvc.perform(get("/push/get/btPush2")
				.contentType("application/json"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		PushResponse response = mapper.readValue(resp2, PushResponse.class);
		
		assertThat(response.getStatus()).isEqualTo("accept");
	}
	
}
