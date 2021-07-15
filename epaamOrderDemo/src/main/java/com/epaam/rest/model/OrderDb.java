package com.epaam.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.reflections.Reflections;
import org.springframework.stereotype.Repository;

import com.epaam.rest.annotation.Offer;
import com.epaam.rest.service.OrderService;

@Repository
public class OrderDb {
	private static final ConcurrentHashMap<Integer, Order> ordersMap = new ConcurrentHashMap<>();
	private static final Map<String, Map<String, String>> offerMap = new HashMap<>();
	private static final Map<String, OrderService> offerClassMap = new HashMap<>();

	private OrderDb() throws Exception {
		loadOffers();
		loadOfferClasses();
	}

	public static void insertIntoStorage(Order order) throws Exception {
		for (Order o : ordersMap.values()) {
			if (order.getOrderId() == o.getOrderId()) {
				throw new Exception("Order ID already present");
			}
		}
		ordersMap.put(ordersMap.size() + 1, order);

	}

	public static List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<>();
		for (Integer key : ordersMap.keySet()) {
			orders.add(ordersMap.get(key));
		}
		return orders;
	}

	public static Map<String, String> getOffersMap(String item) {
		return offerMap.get(item);
	}

	private void loadOffers() {
		Map<String, String> itemOfferMap = new HashMap();
		itemOfferMap.put("BuyOneGetOne", "1");
		offerMap.put("apple", itemOfferMap);
		itemOfferMap = new HashMap();
		itemOfferMap.put("3ForThePriceOf2", "2");
		offerMap.put("orange", itemOfferMap);
	}

	public static OrderService getOfferClass(String key) {
		return offerClassMap.get(key);
	}

	private void loadOfferClasses() throws Exception {
		try {
			Reflections refelections = new Reflections("com.epaam.rest.service");
			Iterator itr = refelections.getTypesAnnotatedWith(Offer.class).iterator();
			while (itr.hasNext()) {
				Class<?> clazz = (Class) itr.next();
				Offer service = clazz.getDeclaredAnnotation(Offer.class);
				offerClassMap.put(service.name(), (OrderService) clazz.newInstance());
			}
		} catch (Exception e) {
			throw new RuntimeException("Error while loading the application");
		}

	}
}
