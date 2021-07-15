package com.epaam.rest.service;

import org.springframework.stereotype.Repository;

import com.epaam.rest.annotation.Offer;
import com.epaam.rest.model.Order;

@Offer(name = "2")
@Repository
public class Offer2 extends OrderService {

	@Override
	public void applyOffer(Order order) {
		order.getItems().stream().filter(i -> "orange".equals(i.getName())).findFirst().ifPresent(it -> {

			int value = it.getQuantity() % 2;
			if (0 == value) {
				it.setQuantity(it.getQuantity() - 2);
			} else {
				it.setQuantity(it.getQuantity() - 2 + 1);
			}
			it.setTotalPrice(it.getQuantity() * it.getPrice());
		});
	}
}
