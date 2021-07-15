package com.epaam.rest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epaam.rest.model.Item;
import com.epaam.rest.model.Order;
import com.epaam.rest.model.OrderDb;

public abstract class OrderService {

	public Order process(Order order) throws Exception {
		validateRequest(order);
		applyOffer(order);
		// totalItemPrice(order);
		return order;
	}

	public void processOrder(Order order, List<String> offers) throws Exception {

		validateRequest(order);

		List<Item> items = order.getItems();
		Map<String, Boolean> processedMap = new HashMap();
		for (Item item : items) {
			Map<String, String> offerMap = OrderDb.getOffersMap(item.getName());
			if (offerMap != null && offers != null) {
				for (String offer : offers) {
					if (offerMap.keySet().contains(offer)) {
						String offerClassName = offerMap.get(offer);
						if (offerClassName != null) {
							OrderDb.getOfferClass(offerClassName).applyOffer(order);
							processedMap.put(item.getName(), true);
						}
					} /*
						 * else if (processedMap.get(item.getName()) != null) {
						 * this.totalItemPrice(order, item.getName()); }
						 */
				}
			} else {
				this.totalItemPrice(order, null);
			}
		}
	}

	private void validateRequest(Order order) throws Exception {
		if (order.getItems() != null && order.getItems().isEmpty()) {
			throw new Exception("Invalid order. Items in the order cannot be empty");
		}
	}

	public Order prepareResponse(Order order) {

		finalOrderPrice(order);
		setMessage(order);
		return order;
	}

	protected abstract void applyOffer(Order order);

	protected void totalItemPrice(Order order, String name) {
		if (name != null) {
			order.getItems().stream().filter(i -> name.equals(i.getName())).forEach(it -> {
				it.setTotalPrice(it.getQuantity() * it.getPrice());
			});
		} else {
			order.getItems().stream().forEach(it -> {
				it.setTotalPrice(it.getQuantity() * it.getPrice());
			});
		}

	}

	protected void finalOrderPrice(Order order) {
		double tp = 0;
		for (Item i : order.getItems()) {
			if (i.getTotalPrice() != null) {

				tp += i.getTotalPrice();
			}
		}
		order.setPrice(tp);
	}

	private void setMessage(Order order) {

		order.setMessage("Order successful, please pay " + order.getPrice());
	}

}
