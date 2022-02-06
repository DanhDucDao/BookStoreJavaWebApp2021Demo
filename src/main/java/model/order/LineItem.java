package model.order;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import model.book.Book;

@Entity
public class LineItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "CartID")
	private Cart cart;
	
	private int quantity;
	
	private float price;
	
	private float discount;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BookID")
	private Book book;
	
	@OneToMany(mappedBy = "lineItem", fetch = FetchType.LAZY)
	private List<OrderItem> orderItems;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Book getBook() {
		return this.book;
	}
	
	public Cart getOwnCart() {
		return this.cart;
	}
	
	/**
	 * Set cart (a target entity), only for JPA puspose
	 * @param cart a cart instance already persist in database
	 */
	public void setAssociationCart(Cart cart) {
		this.cart = cart;
	}
}
