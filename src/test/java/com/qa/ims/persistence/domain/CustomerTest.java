package com.qa.ims.persistence.domain;


import static org.junit.Assert.assertEquals;


import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}


	public void toStringTest() {
		Customer customer = new Customer(1L, "James", "Bay");
		String expected = "id:1 first name:James surname:Bay";
		assertEquals(expected, customer.toString());
	}

	@Test
	public void firstConstructorTest() {
		Customer customer = new Customer("Rick", "Mason");
		assertEquals("Rick", customer.getFirstName());
		assertEquals("Mason", customer.getSurname());

	}

	@Test
	public void secondConstructorTest() {
		Customer customer = new Customer(1L, "James", "Bay");
		assertEquals(Long.valueOf("1"), customer.getId());
		assertEquals("James", customer.getFirstName());
		assertEquals("Bay", customer.getSurname());
	}


}
