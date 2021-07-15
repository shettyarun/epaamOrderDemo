package com.epaam.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epaam.rest.dao.OrderDAO;
import com.epaam.rest.model.Order;

@Repository
public class CRUDOrderService {

	@Autowired
	private OrderDAO orderDao;
	
	public Order getOrder(int orderId) throws Exception {
		Optional<Order> order = orderDao.getAllOrders().stream().filter(o-> orderId == o.getOrderId()).findFirst();
		if(order.isPresent()) return order.get(); else throw new Exception("Order not present");
		
	}
	
	public List<Order> findAllOrders() {
		return orderDao.getAllOrders();
	}
	

	
	public void inserOder(Order order) throws Exception{
		orderDao.insertIntoStorage(order);
	}
}
