package com.cach0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cach0.model.PushDto;
import com.cach0.model.response.BaseCacheResponse;
import com.cach0.model.response.PushResponse;
import com.cach0.service.PushService;

@RestController
@RequestMapping("push")
public class PushController {

	@Autowired
	PushService push;
	
	@PostMapping("insert")
	public BaseCacheResponse insertPush(@RequestBody PushDto request) {
		return push.insertPush(request);
	}
	
	@GetMapping("get/{bt}")
	public PushResponse getPushDto(@PathVariable ("bt") String bt) {
		return push.getPush(bt);
	}
	
	@PutMapping("update")
	public BaseCacheResponse acceptPush(@RequestBody PushDto request) {
		return push.acceptPush(request);
	}
	
}
