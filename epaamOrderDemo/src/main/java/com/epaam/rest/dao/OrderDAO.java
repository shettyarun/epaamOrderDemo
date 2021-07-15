package com.epaam.rest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epaam.rest.model.Order;
import com.epaam.rest.model.OrderDb;

@Repository
public class OrderDAO {

	public void insertIntoStorage(Order order) throws Exception {
		synchronized (this) {
			OrderDb.insertIntoStorage(order);
		}
	}

	public List<Order> getAllOrders() {
		return OrderDb.getAllOrders();
	}

}
