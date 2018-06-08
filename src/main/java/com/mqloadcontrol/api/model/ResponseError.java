package com.mqloadcontrol.api.model;

public class ResponseError {
	
	private String error;
	private Integer code;
	
	public ResponseError(String error, Integer code) {
		super();
		this.error = error;
		this.code = code;
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ResponseError [error=" + error + ", code=" + code + "]";
	}
	
	
	

}
