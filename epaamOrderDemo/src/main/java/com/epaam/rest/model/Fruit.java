package com.epaam.rest.model;

import java.math.BigDecimal;

public class Fruit {

	private String fruitName;
	private int fruitCount;
	private BigDecimal totalPrice;

	public String getFruitName() {
		return fruitName;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public int getFruitCount() {
		return fruitCount;
	}

	public void setFruitCount(int fruitCount) {
		this.fruitCount = fruitCount;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
