package com.example.list.dto;


public class AddressDto {
	
	private long id;
	private String address;
	private String state;
	private String city;
	private String district;
	private String pincode;
	
	private ArrayListDto array;
	
	

	public ArrayListDto getArray() {
		return array;
	}

	public void setArray(ArrayListDto array) {
		this.array = array;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	

}
