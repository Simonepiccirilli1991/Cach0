package com.cach0.model.response;

public class BaseCacheResponse {

	private String msg;
	private Boolean insert;
	private Boolean noFound;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean getInsert() {
		return insert;
	}
	public void setInsert(Boolean insert) {
		this.insert = insert;
	}
	public Boolean getNoFound() {
		return noFound;
	}
	public void setNoFound(Boolean noFound) {
		this.noFound = noFound;
	}
	
	@Override
	public String toString() {
		return "BaseCacheResponse [msg=" + msg + ", insert=" + insert + ", noFound=" + noFound + "]";
	}
	
	
}
