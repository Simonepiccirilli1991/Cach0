package com.cach0.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PushDto implements Serializable{

	private String bt;
	private String bandaId;
	private LocalDateTime time;
	private String status;
	
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getBandaId() {
		return bandaId;
	}
	public void setBandaId(String bandaId) {
		this.bandaId = bandaId;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "PushDto [bt=" + bt + ", bandaId=" + bandaId + ", time=" + time + ", status=" + status + "]";
	}
	
	
}
