package com.epaam.rest.service;

import org.springframework.stereotype.Repository;

import com.epaam.rest.model.Order;

@Repository
public class NoOfferService extends OrderService {

	@Override
	public void applyOffer(Order order) {
		// do nothing
	}/*
		 * 
		 * @Override protected void totalItemPrice(Order order) {
		 * order.getItems().stream().forEach(it -> { it.setTotalPrice(it.getQuantity() *
		 * it.getPrice()); }); }
		 */
}
