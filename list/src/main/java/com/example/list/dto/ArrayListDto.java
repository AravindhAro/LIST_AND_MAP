package com.example.list.dto;


import java.util.List;

public class ArrayListDto {
	
	private long id;
	private String name;
	
	private List<LinkedListDto> request;
	
	private List<AddressDto>  addressdto;

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

	public List<LinkedListDto> getRequest() {
		return request;
	}

	public void setRequest(List<LinkedListDto> request) {
		this.request = request;
	}

	public List<AddressDto> getAddressdto() {
		return addressdto;
	}

	public void setAddressdto(List<AddressDto> addressdto) {
		this.addressdto = addressdto;
	}

	
	

}
