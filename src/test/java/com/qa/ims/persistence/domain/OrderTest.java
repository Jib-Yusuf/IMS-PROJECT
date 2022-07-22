package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class OrderTest {

	@Test
	public void firstConstructorTest() {
		Customer customer = new Customer("Ali", "Mo");
		Order order = new Order(customer);

		assertEquals(customer, order.getCustomerId());
	}

	@Test
	public void secondConstructorTest() {
		Customer customer = new Customer("Ali", "Mo");
		Order order = new Order(3L, customer);

		assertEquals(Long.valueOf(3), order.getId());
		assertEquals(customer, order.getCustomerId());
	}

	@Test
	public void thirdConstructorTest() {
		Customer customer = new Customer("Ali", "Mo");
		Order order = new Order(5L, customer);

		assertNotNull(order);
	}

	@Test
	public void fourthConstructorTest() {
		Item Bike = new Item("Bike",  15.99F);
		Item Ball = new Item(3L, "Ball", 12.50F);
		List<Item> listOfItems = new ArrayList<>();
		listOfItems.add(Bike);
		listOfItems.add(Ball);
		
		Order order = new Order(3L, listOfItems);

		assertEquals(Long.valueOf(3), order.getId());
		assertEquals(listOfItems, order.getOrdersItems());
	}

	@Test
	public void fifthConstructorTest() {
		Customer customer = new Customer("Joe", "Peter");
		Item Bed = new Item("Bed", 69.99F);
		Item Pillows = new Item(3L, "Bed", 69.99F);
		List<Item> listOfItems = new ArrayList<>();
		listOfItems.add(Bed);
		listOfItems.add(Pillows);
		double totalPrice = Bed.getPrice() + Pillows.getPrice();
		
		
		Order order = new Order(3L, customer, totalPrice, 2L, listOfItems);
	
		assertEquals(Long.valueOf(3), order.getId());
		assertEquals(totalPrice, order.getTotalPrice(), 0.01);
		assertEquals(listOfItems, order.getOrdersItems());
		assertEquals(customer, order.getCustomerId());
	}

// Below we are testing the toString Method in Order class.	
	@Test
	public void toStringTEST() {
		Customer customer = new Customer(1L, "Lee", "Jenkins");
		Item Bed = new Item("Bed", 70F);
		Item Pillows = new Item(3L, "Pillow", 65F);
		List<Item> ListOfItems = new ArrayList<>();
		ListOfItems.add(Bed);
		ListOfItems.add(Pillows);
		double totalPrice = Bed.getPrice() + Pillows.getPrice();
		
		Order order = new Order(3L, customer, totalPrice, customer.getId(), ListOfItems);
		
				
		assertEquals(
				"Order [id=3, customerId=id:1 first name:Lee surname:Jenkins, itemName=null, dateOrdered=null, ordersItems=[id:null item name:Bed price:70.0, id:3 item name:Pillow price:65.0]]",
				order.toString());
	}
}
