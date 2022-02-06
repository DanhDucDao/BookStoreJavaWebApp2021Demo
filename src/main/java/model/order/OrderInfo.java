package model.order;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OrderInfo implements Serializable{
	private static final long serialVersionUID = -560596747287404967L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String email;
	private String phone;
	private String note;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrderID")
	private Order order;

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * this method only use for mapping while persisting object in JPA
	 * @param order The order (role: Target in JPA) instance already in the active persistence context
	 */
	public void setAssociationTargetOrder(Order order) {
		this.order = order;
	}
}
