package model.customer;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import model.customer.account.AccountStatus;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Account")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 4464355070310632519L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Status")
	private AccountStatus status;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "account_role", 
				joinColumns = @JoinColumn(name = "AccountID"), 
				inverseJoinColumns = @JoinColumn(name = "RoleID"))
	private List<Role> roles;

	@OneToOne(mappedBy = "account")
	private Customer customer;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public boolean hasRoles(String roleName) {
		for(Role r : this.roles) {
			if(r.getName().equalsIgnoreCase(roleName))
				return true;
		}
		return false;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", status=" + status
				+ ", roles=" + roles + "]";
	}
}
