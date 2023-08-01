package com.binance.api.client.exception;

import java.io.Serial;

public class UnsupportedEventException extends IllegalArgumentException {
	@Serial
	private static final long serialVersionUID = -1852755188564122928L;

	public UnsupportedEventException(String message) {
		super(message);
	}
}