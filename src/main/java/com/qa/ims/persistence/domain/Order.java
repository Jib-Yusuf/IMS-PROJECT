package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
	private Long id;
	private Customer fkCustomerid;
	private Double totalPrice;
	private String datePlaced;
	private List<Item> ordersItems = new ArrayList<>();
	
	public Order() {
		
		
	}


	
	public Order(Customer fkCustomerId, Double totalPrice, String datePlaced, List<Item> orderItems) {
		super();
		this.fkCustomerid = fkCustomerId;
		this.totalPrice = totalPrice;
		this.ordersItems = orderItems;
	
	}
	
	public Order(Long id, Customer fkCustomerId, List<Item> orderItems, Double totalPrice, String datePlaced) {
		super();
		this.id = id;
		this.fkCustomerid = fkCustomerId;
		this.totalPrice = totalPrice;
		this.ordersItems = orderItems;
	}
	
	public Order(Long id, Customer fkCustomerId, Double totalPrice, String datePlaced, List<Item> orderItems) {
		super();
		this.id = id;
		this.fkCustomerid = fkCustomerId;
		this.totalPrice = totalPrice;
		this.ordersItems = orderItems;
	}
	public Order(Customer fkCustomerId) {
		this.setFkCustomerID(fkCustomerId);
	}
	
	public Order(Long id, Customer fkCustomerid) {
		this.setId(id);
		this.setFkCustomerID(fkCustomerid);
		
			
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Customer getFKCustomerId() {
		return this.fkCustomerid;
	}
	
	public void setFkCustomerID(Customer fkCustomerId) {
		this.fkCustomerid = fkCustomerId;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getDatePlace() {
		return datePlaced;
	}
	
	public void setDatePlaced(String date) {
		this.datePlaced = date;
	}
	
	public List<Item> getOrdersItems() {
		return ordersItems;
	}
	
	public void setOrdersItem(List<Item> orderItems) {
		this.ordersItems = orderItems;
	}



	@Override
	public String toString() {
		return "Order [id=" + id + ", fkCustomerid=" + fkCustomerid + ", totalPrice=" + totalPrice + ", datePlaced="
				+ datePlaced + ", ordersItems=" + ordersItems + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(datePlaced, fkCustomerid, id, ordersItems, totalPrice);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(datePlaced, other.datePlaced) && Objects.equals(fkCustomerid, other.fkCustomerid)
				&& Objects.equals(id, other.id) && Objects.equals(ordersItems, other.ordersItems)
				&& Objects.equals(totalPrice, other.totalPrice);
	}

}
