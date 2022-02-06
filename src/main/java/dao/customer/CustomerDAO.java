package dao.customer;

import java.util.List;

import dao.customer.exception.UserNameExist;

import dao.customer.exception.InvalidAccount;
import model.customer.Customer;
import model.order.Cart;
import model.order.Order;
import model.order.status.OrderStatus;

public interface CustomerDAO{
	/**
	 * add a customer to database
	 * @param customer a customer instance registered to system, must include address, full name, account.
	 * @return customer saved in database
	 * @throws UserNameExist if username already exist
	 * @throws Exception if a JPA exception occurs
	 */
	Customer createCustomer(Customer customer) throws UserNameExist, Exception;
	
	/**
	 * check login and return correspond customer
	 * @param username 
	 * @param password
	 * @return customer that logins
	 * @throws InvalidAccount if username or password not correct
	 * @throws Exception if a JPA exception occurs
	 */
	Customer checkLogin(String username, String password) throws InvalidAccount;
	/**
	 * 
	 * @param customer a customer with information to edit, must have a ID
	 * @return customer is edited and saved to database
	 * @throws Exception if a JPA exception occurs, or a field is invalid
	 */
	Customer editCustome(Customer customer) throws Exception;
	
	/**
	 * make a current cart for a customer, customer use this cart to perform cart management,
	 * this cart is not in any order yet ( OrderID = null)
	 * @param customer a customer instance that possesses the cart
	 * @return current cart (created or current one)
	 */
	Cart getCurrentCart(Customer customer);
	
	/**
	 * return all the orders of a customer
	 * @param customer a {@link Customer} instance
	 * @return a {@link List} of {@link Order} instances that go with the customer
	 * @throws IllegalArgumentException if {@link Customer} not found
	 */
	List<Order> getAllOrders(Customer customer);
	
	
	/**
	 * return all orders of a customer that have specific status
	 * @param customer a {@link Customer} instance 
	 * @param status a {@link OrderStatus} instance
	 * @return a {@link List} of {@link Order} instances
	 * @throws IllegalArgumentException if {@link Customer} not found
	 */
	List<Order> getAllOrders(Customer customer, OrderStatus status);
	
	void endSession();
}
