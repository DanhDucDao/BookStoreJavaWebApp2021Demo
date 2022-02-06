package model.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.customer.Customer;
import model.order.status.OrderStatus;

@Entity
@Access(AccessType.FIELD)
@Table(name = "Order1")
public class Order implements Serializable{
	private static final long serialVersionUID = 2134080472543189997L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	private OrderStatus status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date acceptDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date cancelDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "CustomerID")
	private Customer customer;
	
	@OneToOne(mappedBy = "order", fetch = FetchType.EAGER)
	private OrderInfo orderInfo;
	
	@OneToOne(mappedBy = "order", fetch = FetchType.EAGER)
	private Payment payment;
	
	@OneToOne(mappedBy = "order", fetch = FetchType.EAGER)
	private Shipment shipment;
	
	@OneToOne(mappedBy = "order", fetch = FetchType.EAGER)
	private Cart cart;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	
}
