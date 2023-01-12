package com.cach0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cach0.model.request.OtpRequest;
import com.cach0.model.response.BaseCacheResponse;
import com.cach0.model.response.OtpResponse;
import com.cach0.service.OtpService;

@RestController
@RequestMapping("otp")
public class OtpController {

	@Autowired
	OtpService otpService;
	
	@PostMapping("insert")
	public BaseCacheResponse insert(@RequestBody OtpRequest request) {
		
		return otpService.insert(request);
	}
	
	@PostMapping("get")
	public OtpResponse getOtpDto(@RequestBody String trxId) {
		
		return otpService.get(trxId);
	}
	
	//TODO tirare su dockerfile.
	//TODO unire il tutto e fare dockercompose
}
