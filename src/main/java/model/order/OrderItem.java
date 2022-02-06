package model.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.book.BookItem;

@Entity
@Access(AccessType.FIELD)
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 5918502886514438073L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date addTime;
	
	private String note;
	private float price;
	private float discount;
	
	@ManyToOne
	@JoinColumn(name = "LineItemID")
	private LineItem lineItem;
	
	@OneToOne
	@JoinColumn(name = "BookItemID")
	private BookItem bookItem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public BookItem getBookItem() {
		return bookItem;
	}

	public void setBookItem(BookItem bookItem) {
		this.bookItem = bookItem;
	}
	
	public LineItem getOwnLineItem() {
		return this.lineItem;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", addTime=" + addTime + ", note=" + note + ", price=" + price + ", discount="
				+ discount + "]";
	}
}
