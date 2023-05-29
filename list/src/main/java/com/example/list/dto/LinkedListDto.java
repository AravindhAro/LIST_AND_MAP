package com.example.list.dto;

import java.util.List;

public class LinkedListDto {
	
	private long id;
	private String name;
	private String value;
	private List<ArrayListDto> employee;
	
	public List<ArrayListDto> getEmployee() {
		return employee;
	}
	public void setEmployee(List<ArrayListDto> employee) {
		this.employee = employee;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
