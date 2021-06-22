package com.SpringAssignment.ClientApp.Exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;


public class ErrorResponseBody {
	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private String path;

	public ErrorResponseBody(Date timestamp, String message, HttpStatus status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status.value();
	}

	public ErrorResponseBody(Date timestamp, String message, HttpStatus status, String error) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status.value();
		this.error = error;
	}

	public ErrorResponseBody(Date timestamp, String message, HttpStatus status, String error, String pathURL) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status.value();
		this.error = error;
		this.path= pathURL;
		
		
		
	}
}
