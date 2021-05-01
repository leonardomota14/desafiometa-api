package com.meta.desafiometa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UpdateError extends ServiceException {
	private static final long serialVersionUID = 8480449997741224698L;

	public UpdateError(final String error) {
		super(error);
	}

	public static UpdateError of(final String error) {
		return new UpdateError(error);
	}

	public static UpdateError of(final String error, final Object... params) {
		return new UpdateError(getErrorFormated(error, params));
	}

}
