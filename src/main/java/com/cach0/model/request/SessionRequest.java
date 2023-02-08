package com.cach0.model.request;

import org.springframework.lang.NonNull;

public class SessionRequest {
	
	@NonNull
	private String bt;
	private String username;
	@NonNull
	private String scope;
	
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	@Override
	public String toString() {
		return "SessionRequest [bt=" + bt + ", username=" + username + ", scope=" + scope + "]";
	}
	
	
}
