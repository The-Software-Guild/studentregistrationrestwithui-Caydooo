package com.softra.studentreg.exception;

import java.util.Date;
import java.util.List;

public class ExceptionResponse {
	private Date timestamp;
	private String message;
	private String details;
	private List<String> detailsList;
	
	public ExceptionResponse() {}

	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	public ExceptionResponse(Date timestamp, String message, List<String> detailsList) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.detailsList = detailsList;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<String> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<String> detailsList) {
		this.detailsList = detailsList;
	}
	
	
}
