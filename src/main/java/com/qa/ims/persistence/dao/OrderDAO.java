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
			PreparedStatement statement = connection.prepareStatement("INSERT INTO orders(fk_customers_id, total_price, date_placed) VALUES (?, ?, ?)");)  {
		statement.setLong(1, order.getFKCustomerId().getId());
		statement.setDouble(2, order.getTotalPrice());
		statement.setString(3, order.getDatePlace());
		statement.execute();
		
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
	Long fk_customers_id = resultSet.getLong("fk_customers_id");
//	Double totalPrice = resultSet.getDouble("total_price");
	String datePlaced = resultSet.getString("date_placed");
	List<Item> itemList = getItemInOrder(id);
	double TotalPrice = calculateTotalOrderCost(id);
	Customer customer = customerDAO.read(fk_customers_id);
	return new Order(id, customer, TotalPrice, datePlaced, itemList);
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

}