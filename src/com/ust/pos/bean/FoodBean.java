package com.ust.pos.bean;

public class FoodBean {
	private String foodId;
	private String storeid;
	
	private String type;
	private String name;
	private String foodSize;
	private int quantity;
	private double price;
	public String getFoodId() {
		return foodId;
	}
	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}
	@Override
	public String toString() {
		return "FoodBean [foodId=" + foodId + ", storeid=" + storeid + ", type=" + type + ", name=" + name
				+ ", foodSize=" + foodSize + ", quantity=" + quantity + ", price=" + price + "]";
	}
	public String getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFoodSize() {
		return foodSize;
	}
	public void setFoodSize(String foodSize) {
		this.foodSize = foodSize;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}