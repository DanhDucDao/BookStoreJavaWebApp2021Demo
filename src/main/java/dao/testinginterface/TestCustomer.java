package dao.testinginterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import dao.customer.CustomerService;
import model.customer.Account;
import model.customer.Address;
import model.customer.Admin;
import model.customer.Fresher;
import model.customer.FullName;
import model.customer.Member;
import model.customer.Role;
import model.customer.account.AccountStatus;
import model.customer.gender.Gender;
import model.customer.member.MemberLevel;

public class TestCustomer {
	public static void main(String[] args) {
		Address address = new Address();
		address.setId(4);
		address.setStreet("137 Quang Trune");
		address.setSubDistrict("Hoang Dieu");
		address.setDistrict("Hai Ba Trung");
		address.setCity("Ha Noi");
		
		
		FullName fullname = new FullName();
		fullname.setId(4);
		fullname.setFirstName("Trung");
		fullname.setLastName("La Quan");
		
		
		List<Role> roles = new ArrayList<Role>();
		Role r1 = new Role(); r1.setId(1);
		Role r2 = new Role(); r2.setId(2);
		roles.add(r1);roles.add(r2);
		Account acc = new Account();
		acc.setId(4);
		acc.setRoles(roles);
		acc.setPassword("password");
		acc.setUsername("member");
		acc.setStatus(AccountStatus.OKE);
//		en.persist(acc);

		Member admin = new Member();
		admin.setId(4);
		Calendar ca = new GregorianCalendar(2000, 06, 25);
		Date date = ca.getTime();
		admin.setDateOfBirth(date);
		admin.setGender(Gender.MALE);
		admin.setCreateDate(new Date());
		admin.setEmail("michael123@gmail.com");
		admin.setPhoneNumber("09987654321");
		admin.setLastAccess(new Date());
		admin.setAccount(acc);
		admin.setAddress(address);
		admin.setFullname(fullname);
		admin.setCard("123456");
		admin.setLevel(MemberLevel.SILVER);
//		admin.setPoint(10);
//		admin.setSecurityId("034200000190");
//		admin.setPosition("employee");
//		admin.setImageString("Image.png");
		
		CustomerService c = new CustomerService();
		try {
			c.editCustome(admin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		en.persist(admin);
//		en.getTransaction().commit();
//		
//		
//		en.close();
	}
}
