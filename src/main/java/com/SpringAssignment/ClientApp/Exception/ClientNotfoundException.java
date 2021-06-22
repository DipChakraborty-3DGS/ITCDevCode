package com.SpringAssignment.ClientApp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotfoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public  ClientNotfoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
