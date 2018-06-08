package com.mqloadcontrol.api.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestControllerBase {
	
	public ResponseEntity<Object> getResposeSuccess(final Object obj) {
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	
	public ResponseEntity<Object> getResposeError(final Object obj) {
		return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
	}

}
