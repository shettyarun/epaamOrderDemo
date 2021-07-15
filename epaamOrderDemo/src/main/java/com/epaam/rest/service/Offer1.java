package com.epaam.rest.service;

import org.springframework.stereotype.Repository;

import com.epaam.rest.annotation.Offer;
import com.epaam.rest.model.Order;

@Offer(name = "1")
@Repository
public class Offer1 extends OrderService {

	@Override
	public void applyOffer(Order order) {
		order.getItems().stream().filter(i -> "apple".equals(i.getName())).findFirst().ifPresent(it -> {
			double v = it.getPrice() * it.getQuantity();
			it.setTotalPrice(v / 2);
		});

	}

	

}
