package br.com.os.controller.exceptions;

public class DataViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataViolationException(String message) {
		super(message);
	}

	public DataViolationException(String message, Throwable tr) {
		super(message, tr);
	}

}
