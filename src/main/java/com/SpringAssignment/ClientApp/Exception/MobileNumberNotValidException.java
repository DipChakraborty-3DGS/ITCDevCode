package com.SpringAssignment.ClientApp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MobileNumberNotValidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MobileNumberNotValidException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}

}
