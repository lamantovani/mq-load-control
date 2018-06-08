package com.mqloadcontrol.api.model;

public class ResponseSuccess {

	private String message;
	private Integer code;
	
	public ResponseSuccess(String message, Integer code) {
		super();
		this.message = message;
		this.code = code;
	}
	
	public ResponseSuccess() {
		super();
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ResponseSuccess [message=" + message + ", code=" + code + "]";
	}

	
	
	
}
