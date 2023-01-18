package com.cach0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cach0.model.request.SessionRequest;
import com.cach0.model.response.SessionResponse;
import com.cach0.service.SessionService;

@RestController
@RequestMapping("sess")
public class SessionController {

	@Autowired
	SessionService sessService;
	
	@PostMapping("insert")
	public ResponseEntity<SessionResponse> insert(@RequestBody SessionRequest request){
		return new ResponseEntity<>(sessService.insert(request), HttpStatus.OK);
	}
	@PostMapping("update")
	public ResponseEntity<SessionResponse> updateSession(@RequestBody SessionRequest request){
		return new ResponseEntity<>(sessService.updateSession(request), HttpStatus.OK);
	}
	@PostMapping("get")
	public ResponseEntity<SessionResponse> getSession(@RequestBody String bt){
		return new ResponseEntity<>(sessService.get(bt), HttpStatus.OK);
	}
}
