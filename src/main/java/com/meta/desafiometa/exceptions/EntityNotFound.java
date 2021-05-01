package com.meta.desafiometa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jessé Sampaio
 *
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class EntityNotFound extends ServiceException {
	private static final long serialVersionUID = 5412611649024100600L;

	public EntityNotFound(final String error) {
		super(error);
	}

	public static EntityNotFound of(final String error) {
		return new EntityNotFound(error);
	}

	public static EntityNotFound of(final String error, final Object... params) {
		return new EntityNotFound(getErrorFormated(error, params));
	}

}
