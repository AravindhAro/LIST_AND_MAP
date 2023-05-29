package com.example.list.dto;

import java.util.List;

public class CommonDto<T> {

private String message;
	
	private List<?> responsedto;
		

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getResponsedto() {
		return responsedto;
	}

	public void setResponsedto(List<?> responsedto) {
		this.responsedto = responsedto;
	}
	
	
}
