package com.KLTN.VNPayCore.Exception.Custom;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatusCode;
	private String errorCode;
	private String message;
	private Map<String, Object> details;
}
