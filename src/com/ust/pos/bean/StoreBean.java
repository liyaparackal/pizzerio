package com.ust.pos.bean;

public class StoreBean {
	private String storeId;
	private String name;
	private String street;
	private String city;
	private String pincode;
	private String mobileNo;
	private String state;
	public StoreBean() {
		this.storeId=storeId;
		this.name=name;
		this.street=street;
		this.city=city;
		this.pincode=pincode;
		this.mobileNo=mobileNo;
		this.state=state;
		//initialising constructor
		//or do source-generate cons using fields
		
		
		// TODO Auto-generated constructor stub
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "StoreBean [storeId=" + storeId + ", name=" + name + ", street=" + street + ", city=" + city
				+ ", pincode=" + pincode + ", mobileNo=" + mobileNo + ", state=" + state + "]";
	}
	
}

