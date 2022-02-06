package model.customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.customer.account.AccountStatus;
import model.customer.gender.Gender;

public class Demo {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("book_store");
	
	public static void main(String[] args) {
		takeUser();
	}
	
	static void takeUser() {
		EntityManager en = emf.createEntityManager();
		
		
		Customer cus = en.find(Customer.class, 4);
		if(cus instanceof Admin) {
			Admin ad = (Admin) cus;
			System.out.println(ad.toString());
		}
		//System.out.println(cus.getFullname().getFirstName());
	}
	static void addUser() {
EntityManager en = emf.createEntityManager();
		
		en.getTransaction().begin();
		Address address = new Address();
		address.setStreet("137 Quang Trune");
		address.setSubDistrict("Hoang Dieu");
		address.setDistrict("Hai Ba Trung");
		address.setCity("Ha Noi");
		en.persist(address);
		
		FullName fullname = new FullName();
		fullname.setFirstName("Michael");
		fullname.setLastName("Kane");
		en.persist(fullname);
		
		Role role = en.find(Role.class, 1);
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		Account acc = new Account();
		acc.setRoles(roles);
		acc.setPassword("password");
		acc.setUsername("customer12");
		acc.setStatus(AccountStatus.OKE);
		en.persist(acc);

		Admin admin = new Admin();
		Calendar ca = new GregorianCalendar(2000, 07, 26);
		admin.setDateOfBirth(ca.getTime());
		admin.setGender(Gender.MALE);
		admin.setCreateDate(new Date());
		admin.setEmail("michaelkean2");
		admin.setPhoneNumber("0987654321");
		admin.setLastAccess(new Date());
		admin.setAccount(acc);
		admin.setAddress(address);
		admin.setFullname(fullname);
		
		admin.setSecurityId("034200000190");
		admin.setPosition("employee");
		admin.setImageString("Image");
		
				
		
		en.persist(admin);
		en.getTransaction().commit();
		
		
		en.close();
	}
}
