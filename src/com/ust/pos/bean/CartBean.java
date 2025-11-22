package com.ust.pos.bean;

import java.sql.Date;

public class CartBean {
	private int cartId;
	private String type;
	private String foodId;
	private String userId;
	private double cost;
	private int quantity;
	private Date orderDate;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFoodId() {
		return foodId;
	}
	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(java.sql.Date orderDate2) {
		this.orderDate = orderDate2;
	}
	
}
