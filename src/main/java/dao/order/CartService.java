package dao.order;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dao.DBUtil;
import model.book.Book;
import model.order.Cart;
import model.order.LineItem;

public class CartService implements CartDAO{
	private EntityManager en;

	public CartService() {
		en = DBUtil.getEntityManager();
	}
	
	@Override
	public Cart addToCart(LineItem lineItem, Cart cart) {
		try {
			LineItem existLineItem = getLineItemIfExist(lineItem, cart);
			if(existLineItem != null) 
				updateLineItem(existLineItem, cart, lineItem);
			else 
				createLineItem(lineItem, cart);
			Cart resultCart = en.find(Cart.class, cart.getId());
			return resultCart;
		} finally {
			en.clear();
		}
	}
	
	private void createLineItem(LineItem lineItem, Cart cart) {
		en.getTransaction().begin();
		Book book = en.find(Book.class, lineItem.getBook().getId());
		Cart owncart = en.find(Cart.class, cart.getId());
		lineItem.setBook(book);
		lineItem.setAssociationCart(owncart);
//		en.merge(lineItem);
		// prevent wrong price!
		lineItem.setPrice(book.getPrice());
		en.persist(lineItem);
		owncart.getLineitems().add(lineItem);
		en.getTransaction().commit();
	}
	
	private void updateLineItem(LineItem existLineItem, Cart cart, LineItem lineItem) {
		en.getTransaction().begin();
		existLineItem.setQuantity(existLineItem.getQuantity() + lineItem.getQuantity());
		en.getTransaction().commit();
	}

	private LineItem getLineItemIfExist(LineItem lineItem, Cart cart) {
		Cart owncart = en.find(Cart.class, cart.getId());
		int idBook = lineItem.getBook().getId();
		for(LineItem item : owncart.getLineitems()) {
			if(item.getBook().getId() == idBook)
				return item;
		} 
		return null;
			
	}

	
	public static void main(String[] args) {
		Cart cart = new Cart();
		cart.setId(1);
		CartService s = new CartService();
		System.out.println(s.countTotal(cart));
		
		
	}

	@Override
	public void endSession() {
		if(en != null && en.isOpen()) {
			en.close();
		}
		
	}

	@Override
	public Cart updateLineItem(Cart cart, LineItem lineItem) throws IllegalArgumentException{
		try {
			en.getTransaction().begin();
			Cart newCart = en.find(Cart.class, cart.getId());
			Book book = en.find(Book.class, lineItem.getBook().getId());
			
			TypedQuery<LineItem> query = en.createQuery("SELECT e FROM LineItem e "
					+ "WHERE e.cart = :cart AND e.book = :book", LineItem.class);
			query.setParameter("cart", newCart);
			query.setParameter("book", book);
			
			Optional<LineItem> lineItemOpt = query.getResultList().stream().findFirst();
			if(lineItemOpt.isEmpty()) {
				throw new IllegalArgumentException("Wrong ID");
			}
			
			LineItem oldLineItem = lineItemOpt.get();
			oldLineItem.setQuantity(lineItem.getQuantity());
			oldLineItem.setPrice(book.getPrice());
			
			en.getTransaction().commit();
			return cart;
		} finally {
			en.clear();
		}		
	}

	@Override
	public Cart deleteItem(Cart cart, LineItem lineItem) throws IllegalArgumentException {
		try {
			en.getTransaction().begin();
			Cart newCart = en.find(Cart.class, cart.getId());
			Book book = en.find(Book.class, lineItem.getBook().getId());
			
			TypedQuery<LineItem> query = en.createQuery("SELECT e FROM LineItem e "
					+ "WHERE e.cart = :cart AND e.book = :book", LineItem.class);
			query.setParameter("cart", newCart);
			query.setParameter("book", book);
			
			Optional<LineItem> lineItemOpt = query.getResultList().stream().findFirst();
			if(lineItemOpt.isEmpty()) {
				throw new IllegalArgumentException("Wrong ID");
			}
			
			LineItem newLineItem = lineItemOpt.get();
			newLineItem.setAssociationCart(null);
			newLineItem.setBook(null);
			//newCart.getLineitems().remove(newLineItem.getId());
			en.remove(newLineItem);
			en.getTransaction().commit();
			return newCart;
		} finally {
			en.clear();
		}
		
	}

	@Override
	public float countTotal(Cart cart) {
		try {
			TypedQuery<Double> query = en.createNamedQuery("dao.countTotal", Double.class);
			query.setParameter("cart", cart);
			if(query.getSingleResult() != null) {
				return query.getSingleResult().floatValue();
			}
			return 0;
			
		} finally {
			en.clear();
		}
	}

	@Override
	public float calSubTotal(Cart cart) {
		try {
			TypedQuery<Double> query = en.createNamedQuery("dao.countSubTotal", Double.class);
			query.setParameter("cart", cart);
			if(query.getSingleResult() != null) {
				return query.getSingleResult().floatValue();
			}
			return 0;
		} finally {
			en.clear();
		}
	}

	@Override
	public float calDiscount(Cart cart) {
		try {
			TypedQuery<Double> query = en.createNamedQuery("dao.countDiscount", Double.class);
			query.setParameter("cart", cart);
			if(query.getSingleResult() != null) {
				return query.getSingleResult().floatValue();
			}
			return 0;
		} finally {
			en.clear();
		}
	}
}
