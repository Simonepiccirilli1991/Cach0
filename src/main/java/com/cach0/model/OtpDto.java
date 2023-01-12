package com.cach0.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OtpDto  implements Serializable {

	private String bt;
	private String timestamp;
	private String profilo;
	private LocalDateTime timestampDate;
	private String otp;
	
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getProfilo() {
		return profilo;
	}
	public void setProfilo(String profilo) {
		this.profilo = profilo;
	}
	public LocalDateTime getTimestampDate() {
		return timestampDate;
	}
	public void setTimestampDate(LocalDateTime timestampDate) {
		this.timestampDate = timestampDate;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
}
