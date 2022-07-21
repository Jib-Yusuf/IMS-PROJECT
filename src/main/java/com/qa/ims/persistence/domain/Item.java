package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {
	
	private Long id;
	private String itemName;
	private float price;
	
	
	public Item(String itemName, float price) {
		super();
			this.setItemName(itemName);
			this.setPrice(price);
	
	}
	
	public Item(long id,String itemName, Float price) {
		super();
		this.setId(id);
		this.setItemName(itemName);
		this.setPrice(price);
		
			
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", price=" + price
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itemName, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(id, other.id) && Objects.equals(itemName, other.itemName) 
				&& Objects.equals(price, other.price);
	}
	

}
