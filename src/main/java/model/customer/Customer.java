package model.customer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.customer.gender.Gender;

@Entity
@Table(name = "customer")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "CustomerType")
@Access(AccessType.FIELD)
@NamedQueries(@NamedQuery(name = "dao.selectCustomerByAccount", query = "SELECT e FROM Customer e WHERE e.account = :account"))
public abstract class Customer implements Serializable{
	private static final long serialVersionUID = 2282957484998004460L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	private Gender gender;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	private String email;
	
	private String phoneNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastAccess;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AccountID")
	private Account account;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FullNameID")
	private FullName fullname;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AddressID")
	private Address address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public FullName getFullname() {
		return fullname;
	}

	public void setFullname(FullName fullname) {
		this.fullname = fullname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", createDate="
				+ createDate + ", email=" + email + ", phoneNumber=" + phoneNumber + ", lastAccess=" + lastAccess
				+ ", account=" + account.getUsername() + ", fullname=" + fullname + ", address=" + address + "]";
	}
	
	
}
