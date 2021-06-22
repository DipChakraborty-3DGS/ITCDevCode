package com.SpringAssignment.ClientApp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class IDNumberNotValid extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IDNumberNotValid(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}

}
