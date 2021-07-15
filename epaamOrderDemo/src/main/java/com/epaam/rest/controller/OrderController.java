package com.epaam.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epaam.rest.Util.MapperUtil;
import com.epaam.rest.model.Order;
import com.epaam.rest.service.CRUDOrderService;
import com.epaam.rest.service.NoOfferService;

@RestController
@RequestMapping(path = "/epaam")
public class OrderController {

	@Autowired
	private CRUDOrderService crudOrderService;

	@Autowired
	private NoOfferService orderService;

	@GetMapping(path = "/store", produces = "application/json")
	public String home() {
		return "Welcome to epaam store";
	}

	@GetMapping(path = "/store/allOrders", produces = "application/json")
	public String returnAllOrders() {
		return MapperUtil.convertToJson(crudOrderService.findAllOrders());

	}

	@GetMapping(path = "/store/getOrder", produces = "application/json")
	public String returnAllOrders(@RequestParam(value = "orderId") String orderId) throws Exception {
		try {
			return MapperUtil.convertToJson(crudOrderService.getOrder(Integer.parseInt(orderId)));
		} catch (Exception e) {
			return MapperUtil.convertToJson(e.getMessage());
		}

	}

	@PostMapping(path = "/store/receiveOrders", consumes = "application/json", produces = "application/json")
	public String addOrder(@RequestBody Order order,
			@RequestParam(required = false, value = "offerId") List<String> offerIds) {
		try {
			orderService.processOrder(order, offerIds);
			crudOrderService.inserOder(order);
			return MapperUtil.convertToJson(orderService.prepareResponse(order));
		} catch (Exception e) {
			e.printStackTrace();
			return MapperUtil.convertToJson(e.getMessage());
		}

	}
}
