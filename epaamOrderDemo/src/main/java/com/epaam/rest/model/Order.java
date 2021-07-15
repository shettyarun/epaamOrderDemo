package com.epaam.rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orderId;
	private List<Item> items;
	private Double price;
	private String message;
	private Date orderedDate;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Double getPrice() {
		return price;
	}

	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", items=" + items + ", price=" + price + ", message=" + message
				+ ", orderedDate=" + orderedDate + "]";
	}

}
