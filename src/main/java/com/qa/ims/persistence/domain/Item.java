package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {
	
	private Long id;
	private String itemName;
	private String itemCategory;
	private Float price;
	
	
	public Item(String itemName, String itemCategory, Float price) {
		super();
			this.itemName = itemCategory;
			this.itemCategory = itemCategory;
			this.price = price;
	
	}
	
	public Item(long id,String itemName, String itemCategory,Float price) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.price = price;
		
			
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

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", itemCategory=" + itemCategory + ", price=" + price
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itemCategory, itemName, price);
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
		return Objects.equals(id, other.id) && Objects.equals(itemCategory, other.itemCategory)
				&& Objects.equals(itemName, other.itemName) && Objects.equals(price, other.price);
	}
	

}
