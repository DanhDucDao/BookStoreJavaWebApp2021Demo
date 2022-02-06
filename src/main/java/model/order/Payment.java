package model.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.order.status.PaymentStatus;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Payment")
@Access(AccessType.FIELD)
@DiscriminatorColumn(name = "PaymentMethod")
public abstract class Payment implements Serializable{
	private static final long serialVersionUID = 8774695730786187666L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrderID")
	private Order order;
	
	private float amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date payDate;
	
	private PaymentStatus status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", payDate=" + payDate + ", status="
				+ status + "]";
	}
	
	/**
	 * this method only use for mapping while persisting object in JPA
	 * @param order The order (role: Target in JPA) instance already in the active persistence context
	 */
	public void setAssociationTargetOrder(Order order) {
		this.order = order;
	}
}
