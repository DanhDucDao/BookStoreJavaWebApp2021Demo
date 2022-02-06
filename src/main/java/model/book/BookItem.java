package model.book;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.order.OrderItem;

@Entity
@Table(name = "BookItem")
@NamedQueries(@NamedQuery(name="dao.findBookItemsOfABook", query = "SELECT e FROM BookItem e WHERE e.book = :book"))
public class BookItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2832700559403616029L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "BarCode")
	private String barCode;
	
	@Column(name = "ImportPrice")
	private float importPrice;
	
	@Column(name = "ImportDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date importDate;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public float getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(float importPrice) {
		this.importPrice = importPrice;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}
	
	/**
	 * Use only for JPA mapping
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BookID")
	private Book book;
	/**
	 * only use with JPA
	 * @param book mapping to book
	 */
	public void setAssocciationBook(Book book) {
		this.book = book;
	}
	
	/**
	 * use for JPA Mapping
	 * @return book
	 */
	public Book getAsscociationBook() {
		return this.book;
	}
	
	/**
	 * JPA puspose
	 */
	@OneToOne(mappedBy = "bookItem")
	private OrderItem orderItem;


	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
	
}
