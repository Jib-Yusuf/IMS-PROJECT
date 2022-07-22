package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private CustomerDAO customerDAO;

	public OrderDAO() {

	}

	public OrderDAO(CustomerDAO customerDao, ItemDAO itemDao) {
		super();
		this.itemDAO = itemDao;
		this.customerDAO = customerDao;
		

	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Order> allCreatedOrders = new ArrayList<>();
			while (resultSet.next()) {
				allCreatedOrders.add(modelFromResultSet(resultSet));
			}
			return allCreatedOrders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;

	}

	@Override
	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} 
	
	catch(Exception e)
	{
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	} 
	
	return null;

}

@Override
public Order create(Order order) {
	try (Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO orders(customer_id) VALUES (?, 0.0)");) {
		statement.setLong(1, order.getCustomerId().getId());
		statement.executeUpdate();
		return readLatest();
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return null;
		
	
}

@Override
public Order update(Order order) {
	return null; 
}

@Override
public int delete(long id) {
	try(Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement();) {
		return statement.executeUpdate("DELETE FROM orders_items WHERE fk_orders_id =" + id);
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return 0;
}

public double calculateTotalOrderCost(Long orderID) {
	try (Connection connection = DBUtils.getInstance().getConnection();
	PreparedStatement statement = connection
			.prepareStatement("SELECT * FROM orders_items WHERE fk_orders_id = ?");) {
		statement.setLong(1, orderID);
		ResultSet resultSet = statement.executeQuery();
		double totalOrderCost = 0;
		while (resultSet.next()) {
			totalOrderCost += itemDAO.read(resultSet.getLong("fk_items_id")).getPrice();
		}
		return totalOrderCost;
			
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return 0.0;
}

public List<Item> getItemInOrder(Long orderID) {
	List<Item> ListofItems = new ArrayList<>();
	try (Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM order_items WHERE fk_order_id = ?");) {
		statement.setLong(1, orderID);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			ListofItems.add(itemDAO.read(resultSet.getLong("fk_items_id")));
		}
		return ListofItems;
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return ListofItems;		
	
}

@Override
public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
	Long id = resultSet.getLong("id");
	Long customer_id = resultSet.getLong("customer_id");
	List<Item> itemList = getItemInOrder(id);
	double TotalPrice = calculateTotalOrderCost(id);
	
	Customer customer = customerDAO.read(customer_id);
	return new Order(id, customer, TotalPrice, customer_id, itemList);
}

public Order addToOrder_NewUpdate(Long orderID, Long itemID) {
	try (Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO orders_items(fk_orders_id, fk_items_id) VALUES (?, ?)");) {
		statement.setLong(1, orderID);
		statement.setLong(2, itemID);
		statement.executeUpdate();
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return read(orderID);
}

public Order removeFromOrder_NewUpdate(Long orderID, Long itemID) {
	try (Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement();) {
		statement.executeUpdate("DELETE FROM orders_items WHERE (fk_orders_id = ? AND fk_items_id = ?)");

	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return read(orderID);
}




public Object deleteOrdersItems(long l) {
	try (Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement();) {
		String orderId = null;
		return statement.executeUpdate("DELETE FROM order_items WHERE order_id = " + orderId);
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return 0;

}

}