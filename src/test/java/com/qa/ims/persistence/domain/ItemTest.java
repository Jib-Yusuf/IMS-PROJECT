package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {
	@Test
    public void toStringTEST() {
        Item item = new Item(1L, "Samsung", 800F);
        String expected = "id:1 item name:Samsung price:800.0";
        assertEquals(expected, item.toString());
    }

    @Test
    public void firstConstructorTEST() {
        Item item = new Item("Samsung",  799.99F);
        assertEquals("Samsung", item.getItemName());
        assertEquals(799.99, item.getPrice(), 0.02);
    }

    @Test
    public void secondConstructorTEST() {
        Item item = new Item(1L, "Samsung", 799.99F);
        assertEquals(Long.valueOf("1"), item.getId());
        assertEquals("Samsung", item.getItemName());
        assertEquals(799.99, item.getPrice(), 0.02);

    }

   @Test
    public void equalsTEST() {
        EqualsVerifier.simple().forClass(Item.class).verify();
    }

    @Test
    public void setIdTEST() {
        Item item = new Item(1L, "Samsung", 799.99F);
        item.setId(2L);
        assertEquals(Long.valueOf("2"), item.getId());

    }

    @Test
    public void setItemNameTEST() {
        Item item = new Item(1L, "Samsung", 799.99F);
        item.setItemName("Samsung");
        assertEquals("Samsung", item.getItemName());
    }

  

    @Test
    public void setPriceTEST() {
        Item item = new Item(1L, "Samsung",  799.99F);
        item.setPrice(5000);
        assertEquals(5000, item.getPrice(), 0.02);

    }

}
