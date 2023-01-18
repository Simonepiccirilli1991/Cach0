package com.cach0.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SessionDto implements Serializable {

	private String bt;
	private String username;
	private String scope;
	private LocalDateTime timestampDate;
	
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
	public LocalDateTime getTimestampDate() {
		return timestampDate;
	}
	public void setTimestampDate(LocalDateTime timestampDate) {
		this.timestampDate = timestampDate;
	}
	
	
}
