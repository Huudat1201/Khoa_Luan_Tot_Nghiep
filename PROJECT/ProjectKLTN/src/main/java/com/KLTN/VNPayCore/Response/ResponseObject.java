package com.KLTN.VNPayCore.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

public class ResponseObject<T> extends ResponseEntity<ResponseObject.Payload<T>> {
	public ResponseObject(HttpStatus code, String message, T data) {
		super(new Payload<>(code.value(), message, data), code);
	}

	@Builder
	public static class Payload<T> {
		public int code;
		public String message;
		@JsonInclude(JsonInclude.Include.NON_NULL)
		public T data;
	}
}
