package br.com.os.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.os.exception.NegocioException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<StandartError> negocioException(NegocioException ne) {
		StandartError error = new StandartError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				ne.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}

}
