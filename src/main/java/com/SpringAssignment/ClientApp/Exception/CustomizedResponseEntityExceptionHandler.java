package com.SpringAssignment.ClientApp.Exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ErrorResponseBody> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		ErrorResponseBody errorDetails = new ErrorResponseBody(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST,
				"Bad Request", ((ServletWebRequest) request).getRequest().getRequestURI());

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IdIsNotSouthAfricanIdException.class)
	public final ResponseEntity<ErrorResponseBody> handleSAIdNumberIsNotValid(IdIsNotSouthAfricanIdException ex, WebRequest request) {
		ErrorResponseBody errorDetails = new ErrorResponseBody(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST,
				"Invalid South African ID", ((ServletWebRequest) request).getRequest().getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ClientNotfoundException.class)
	public final ResponseEntity<ErrorResponseBody> handleClientNotFound(ClientNotfoundException ex, WebRequest request) {
		ErrorResponseBody errorDetails = new ErrorResponseBody(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND,
				"Client does not exsist", ((ServletWebRequest) request).getRequest().getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MobileNumberNotValidException.class)
	public final ResponseEntity<ErrorResponseBody> handleMobileNumberalreadyExist(MobileNumberNotValidException ex, WebRequest request) {
		ErrorResponseBody errorDetails = new ErrorResponseBody(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST,
				"Mobile Number Not Valid", ((ServletWebRequest) request).getRequest().getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseBody> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorResponseBody errorDetails = new ErrorResponseBody(new Date(), "Something went wrong",
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(),
				((ServletWebRequest) request).getRequest().getRequestURI().toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

	

}
