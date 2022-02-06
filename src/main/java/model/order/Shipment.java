package model.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.customer.Address;
import model.order.status.ShipmentStatus;

@Entity
public class Shipment implements Serializable{
	private static final long serialVersionUID = -8545019121013994200L;
	
	public static final String NORMAL = "NORMAL";
	public static final String FAST = "FAST";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String shipType;
	
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date encapsulateDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date arriveDate;
	
	private ShipmentStatus status;
	
	private float price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AddressID")
	private Address address;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrderID")
	private Order order;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEncapsulateDate() {
		return encapsulateDate;
	}

	public void setEncapsulateDate(Date encapsulateDate) {
		this.encapsulateDate = encapsulateDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	public ShipmentStatus getStatus() {
		return status;
	}

	public void setStatus(ShipmentStatus status) {
		this.status = status;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	/**
	 * this method only use for mapping while persisting object in JPA
	 * @param order The order (role: Target in JPA) instance already in the active persistence context
	 */
	public void setAssociationTargetOrder(Order order) {
		this.order = order;
	}
}
