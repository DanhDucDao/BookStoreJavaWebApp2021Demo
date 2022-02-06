package dao.customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import dao.customer.exception.InvalidAccount;
import model.customer.Account;
import model.customer.Address;
import model.customer.Admin;
import model.customer.Customer;
import model.customer.FullName;
import model.customer.Role;
import model.customer.account.AccountStatus;
import model.customer.gender.Gender;
import model.order.Cart;

public class TestJPA {
	public static void main(String[] args) throws InvalidAccount, Exception {
		addAdmin();
	}
	
	public static void addAdmin() throws Exception{
		Admin admin = new Admin();
		
		Account account = new Account();
		account.setStatus(AccountStatus.OKE);
		account.setPassword("password");
		account.setUsername("admin");
		ArrayList<Role> roles = new ArrayList<>();
		Role role = new Role();
		Role role2= new Role();
		role.setId(1);
		role2.setId(2);
		roles.add(role2);
		roles.add(role);
		
		
		account.setRoles(roles);
		
		Address address = new Address();
		address.setStreet("Grove Street");
		address.setCity("Los Santos");
		address.setSubDistrict("Gaton");
		address.setDistrict("Long Beach");
		
		FullName fullname = new FullName();
		fullname.setFirstName("Carl");
		fullname.setLastName("Johnson");
		
		
		
		admin.setAccount(account);;
		admin.setFullname(fullname);
		admin.setAddress(address);
		Calendar c = new GregorianCalendar(2000, 7, 26);
		admin.setDateOfBirth(c.getTime());
		admin.setEmail("cj@grovestreet.com");
		admin.setGender(Gender.MALE);
		admin.setImageString("cj.jpg");
		admin.setPhoneNumber("0987654321");
		admin.setPosition("Admin");
		admin.setSecurityId("02584962554");
		
		CustomerDAO cus = new CustomerService();
		
		cus.createCustomer(admin);
		cus.endSession();
		
	}
}
