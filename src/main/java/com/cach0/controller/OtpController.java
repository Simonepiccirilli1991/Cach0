package com.cach0.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cach0.model.response.OtpResponse;

@RestController
@RequestMapping("otp")
public class OtpController {

	
	@PostMapping("insert")
	public OtpResponse insert() {
		
		return null;
	}
	
	@PostMapping("get")
	public OtpResponse getOtpDto() {
		
		return null;
	}
}
