package dao.customer;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import dao.DBUtil;
import dao.customer.exception.InvalidAccount;
import dao.customer.exception.UserNameExist;
import model.book.Book;
import model.customer.Account;
import model.customer.Customer;
import model.order.Cart;
import model.order.LineItem;
import model.order.Order;
import model.order.status.OrderStatus;

public class CustomerService implements CustomerDAO{
	private EntityManager en;
	
	
	public CustomerService() {
		this.en = DBUtil.getEntityManager();
	}


	@Override
	public Customer createCustomer(Customer customer) throws UserNameExist, Exception {
		try {
			
			checkUnique(customer.getAccount().getUsername());
			en.getTransaction().begin();
			customer.setCreateDate(new Date());
			en.persist(customer.getAccount());
			en.persist(customer.getAddress());
			en.persist(customer.getFullname());
			en.persist(customer);
			en.getTransaction().commit();
			return customer;
		} catch (UserNameExist e) {
			e.printStackTrace();
			throw new UserNameExist(e.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getCause().getMessage());
		} finally {
			en.clear();
		}
		
	}
	
	private void checkUnique(String username) throws UserNameExist{
		TypedQuery<Account> query = en.createQuery("SELECT e FROM Account e WHERE e.username = :username", Account.class);
		query.setParameter("username", username);
		Optional<Account> first = query.getResultList().stream().findFirst();
		if(first.isPresent()) 
			throw new UserNameExist("User " + username + " exist!");
	}


	@Override
	public Customer checkLogin(String username, String password) throws InvalidAccount{
		try {
			TypedQuery<Account> query = en.createQuery("SELECT e FROM Account e WHERE e.username = :username AND e.password = :password", Account.class);
			query.setParameter("username", username);
			query.setParameter("password", password);
			Optional<Account> first = query.getResultList().stream().findFirst();
			if(first.isPresent()) {
				TypedQuery<Customer> cusQuery = en.createNamedQuery("dao.selectCustomerByAccount", Customer.class);
				cusQuery.setParameter("account", first.get());
				return cusQuery.getSingleResult();
			}
			else
				throw new InvalidAccount("Username or password is incorrect");
		} finally {
			en.clear();
		}
		
	}


	@Override
	public Customer editCustome(Customer customer) throws Exception {
		try {
			en.getTransaction().begin();
			
			en.merge(customer);
			en.merge(customer.getAccount());
			en.merge(customer.getAddress());
			en.merge(customer.getFullname());
			
			en.getTransaction().commit();
			Customer addedCustomer = en.find(Customer.class, customer.getId());
			return addedCustomer;
		} finally {
			en.clear();
		}
		
	}


	@Override
	public Cart getCurrentCart(Customer customer) {
		Cart cart = getCurrentCartIfExist(customer);
		if(cart != null) {
			return cart;
		}
		cart = createCart(customer);
		return cart;
	}
	
	
	private Cart getCurrentCartIfExist(Customer customer) {
		try {
			TypedQuery<Cart> query = en.createQuery("SELECT e FROM Cart e WHERE e.customer = :customer AND e.order is NULL", Cart.class);
			query.setParameter("customer", customer);
			Optional<Cart> current = query.getResultList().stream().findFirst();
			if(current.isPresent()) {
				Cart cart = current.get();
				for(LineItem item : cart.getLineitems()) {
					Book book = en.find(Book.class, item.getBook().getId());
					item.setPrice(book.getPrice());
				}
				
				en.getTransaction().begin();
				en.getTransaction().commit();
				return current.get();
			}	
			return null;
		} finally {
			en.clear();
		}
	}
	
	private Cart createCart(Customer customer) {
		try {
			Cart cart = new Cart();
			cart.setCreateDate(new Date());
			Customer ownCustomer = en.find(Customer.class, customer.getId());
			cart.setCustomer(ownCustomer);
			
			en.getTransaction().begin();
			en.persist(cart);
			en.getTransaction().commit();
			return cart;
		} finally {
			en.clear();
		}
	}
	
	public void endSession() {
		en.close();
	}


	@Override
	public List<Order> getAllOrders(Customer customer) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("not implemeted yet");
	}


	@Override
	public List<Order> getAllOrders(Customer customer, OrderStatus status) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("not implemeted yet");
	}
	
	
}
