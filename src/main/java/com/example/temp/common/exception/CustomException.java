package com.example.temp.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

	private final HttpStatus httpStatus;
	private final String message;
	private final String detail;

	public CustomException(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
		this.detail = null;
	}

	public CustomException(HttpStatus httpStatus, String message, String detail) {
		this.httpStatus = httpStatus;
		this.message = message;
		this.detail = detail;
	}

}
