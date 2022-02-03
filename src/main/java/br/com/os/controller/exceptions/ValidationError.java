package br.com.os.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timeStamp, Integer status, String error) {
		super(timeStamp, status, error);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

}
