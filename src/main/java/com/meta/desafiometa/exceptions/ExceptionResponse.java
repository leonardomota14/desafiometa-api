package com.meta.desafiometa.exceptions;

import lombok.Data;

@Data
public class ExceptionResponse {

	private final String message;

	public ExceptionResponse(final String message) {
		this.message = message;
	}

}