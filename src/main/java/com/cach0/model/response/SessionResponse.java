package com.cach0.model.response;

public class SessionResponse extends BaseCacheResponse {

	private String bt;
	private String scope;
	private Boolean valid;
	
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public Boolean getValid() {
		return valid;
	}
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	@Override
	public String toString() {
		return "SessionResponse [bt=" + bt + ", scope=" + scope + ", valid=" + valid + "]";
	}
	
	
}
