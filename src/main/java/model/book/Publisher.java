package model.book;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Publisher")
@NamedQueries(@NamedQuery(name = "dao.selectAllPublishers", query = "SELECT e FROM Publisher e"))
public class Publisher implements Serializable{

	private static final long serialVersionUID = -8566032108207236005L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Address", nullable = true)
	private String address;
	
	@Column(name = "PhoneNumber", nullable = true)
	private String phoneNumber;

	public Publisher() {
	}
	
	public Publisher(String name, String description, String email, String address, String phoneNumber) {
		super();
		this.name = name;
		this.description = description;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", name=" + name + ", description=" + description + ", email=" + email
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
