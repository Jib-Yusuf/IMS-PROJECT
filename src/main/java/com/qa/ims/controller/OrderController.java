package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;


public class OrderController implements CrudController<Order> {
	
public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDAO orderDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

		@Override
		public List<Order> readAll() {
			List<Order> orders = orderDAO.readAll();
			for (Order order : orders) {
				LOGGER.info(order);
			}
			return orders;
		}
		
		@Override
		public Order create() {
			LOGGER.info("Please enter the ID of the customer placing the order.");
			Long fk_customers_id = utils.getLong();
			CustomerDAO customerDao = new CustomerDAO();
			Customer newCustomer = customerDao.read(fk_customers_id);
			Order order = orderDAO.create(new Order(newCustomer));
			LOGGER.info("Order created");
			return order;
		}
		
		@Override
		public Order update() {
			Order order = new Order();
			LOGGER.info("Please enter the id of the order you would like to update");
			Long order_id = utils.getLong();
			LOGGER.info("Would you like to ADD or REMOVE a item?");
			String choice = utils.getString();
			LOGGER.info("Please enter the ID of the item you would like to choose.");
			Long item_id = utils.getLong();
			if (choice.equals("ADD")) {
				order = orderDAO.addToOrder_NewUpdate(order_id, item_id);
				LOGGER.info("The item has been added to the order.");
			} else if (choice.equals("REMOVE")) {
				order = orderDAO.removeFromOrder_NewUpdate(order_id, item_id);
				LOGGER.info("The item has been removed from order.");
			}
			
			LOGGER.info("Order updated");
			return order;
		}
		
		@Override
		public int delete() {
			LOGGER.info("Please enter the id of the order you would like to delte");
			Long id = utils.getLong();
			return orderDAO.delete(id);
		}
	

}
