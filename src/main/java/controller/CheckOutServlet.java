package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.customer.CustomerDAO;
import dao.customer.CustomerService;
import dao.order.CartDAO;
import dao.order.CartService;
import dao.order.OrderDAO;
import dao.order.OrderService;
import model.customer.Address;
import model.customer.Customer;
import model.order.Cart;
import model.order.Cash;
import model.order.Check;
import model.order.Credit;
import model.order.Order;
import model.order.OrderInfo;
import model.order.Payment;
import model.order.Shipment;

@WebServlet("/checkOut")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO cusDAO = new CustomerService();
		OrderDAO orderDAO = new OrderService();
		try {
			Order order = parseRequestToOrder(request);
			
			HttpSession session = request.getSession();
			Customer customer = (Customer) session.getAttribute("user");
			Cart cart = cusDAO.getCurrentCart(customer);
			
			order.setCustomer(customer);
			order.setCart(cart);
			
			orderDAO.addOrder(order);
			
			response.sendRedirect(getServletContext().getContextPath()+"/book");
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			cusDAO.endSession();
			orderDAO.endSession();
		}
		
		
	}
	
	private Order parseRequestToOrder(HttpServletRequest request) throws ParseException {
		OrderInfo orderInfo = parseRequestToOrderInfo(request);
		Payment payment = parseRequestToPayment(request);
		Shipment shipment = parseRequestToShipment(request);
		
		Order order = new Order();
		order.setPayment(payment);
		order.setShipment(shipment);
		order.setOrderInfo(orderInfo);
		return order;
	}

	

	private Shipment parseRequestToShipment(HttpServletRequest request) {
		Shipment shipment = new Shipment();
		Address address = new Address();
		address.setStreet(request.getParameter("street"));
		address.setSubDistrict(request.getParameter("subdistrict"));
		address.setDistrict(request.getParameter("district"));
		address.setCity(request.getParameter("city"));
		
		shipment.setShipType(request.getParameter("shipType"));
		
		shipment.setAddress(address);
		shipment.setPrice(30f);
		return shipment;
	}

	private OrderInfo parseRequestToOrderInfo(HttpServletRequest request) {
		
		OrderInfo info = new OrderInfo();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String note = request.getParameter("note");
		note = note == null? "" : note;
		
		info.setName(name);
		info.setNote(note);
		info.setPhone(phone);
		info.setEmail(email);
		
		return info;
	}
	
	private Payment parseRequestToPayment(HttpServletRequest request) throws ParseException {
		String s = request.getParameter("paymentType");
		if(s.equalsIgnoreCase("cash"))
			return parseCash(request);
		if(s.equalsIgnoreCase("check"))
			return parseCheck(request);
		else 
			return parseCredit(request);

	}

	private Credit parseCredit(HttpServletRequest request) throws ParseException {
		String number = request.getParameter("number");
		String creditType = request.getParameter("creditType");
		String dateStr = request.getParameter("expDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date expDate = sdf.parse(dateStr);
		
		Credit credit = new Credit();
		credit.setExpDate(expDate);
		credit.setNumber(number);
		credit.setType(creditType);
		return credit;
	}

	private Check parseCheck(HttpServletRequest request) {
		String bankName = request.getParameter("bankName");
		String bankID = request.getParameter("bankID");
		
		Check check = new Check();
		check.setName(bankName);
		check.setBankId(bankID);
		return check;
	}

	private Cash parseCash(HttpServletRequest request) {
		Cash cash = new Cash();
		cash.setCashTendered(0);
		return cash;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO cusDAO = new CustomerService();
		CartDAO cartDAO = new CartService();
		try {
			HttpSession session = request.getSession();
			Customer customer = (Customer) session.getAttribute("user");
			Cart cart = cusDAO.getCurrentCart(customer);
			
			float subTotal = cartDAO.calSubTotal(cart);
			float discount = cartDAO.calDiscount(cart);
			float total = cartDAO.countTotal(cart);
			
			request.setAttribute("cart", cart);
			request.setAttribute("subTotal", subTotal);
			request.setAttribute("discount", discount);
			request.setAttribute("total", total);
			getServletContext().getRequestDispatcher("/customer/check-out.jsp").forward(request, response);
		} finally {
			cusDAO.endSession();
		}
	}

}
