package controller;

import java.io.IOException;

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
import model.book.Book;
import model.customer.Customer;
import model.order.Cart;
import model.order.LineItem;

@WebServlet("/updateCart")
public class UpdateCart extends HttpServlet{
	private static final long serialVersionUID = 4501684802064748299L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String op = request.getParameter("op");
		
		if(op.equalsIgnoreCase("update")) {
			updateCart(request);
		}
		if(op.equalsIgnoreCase("delete")) {
			deleteItem(request);
		}
		
		response.sendRedirect(getServletContext().getContextPath() + "/cart");
	}

	private void deleteItem(HttpServletRequest request) {
		CustomerDAO cusDAO = new CustomerService();
		CartDAO cartDAO = new CartService();
		try {
			int bookID = Integer.parseInt(request.getParameter("id"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			
			HttpSession session = request.getSession();
			Customer customer = (Customer) session.getAttribute("user");
			Cart cart = cusDAO.getCurrentCart(customer);
			
			
			Book book = new Book();
			book.setId(bookID);
			LineItem lineItem = new LineItem();
			lineItem.setBook(book);
			lineItem.setQuantity(quantity);
			cartDAO.deleteItem(cart, lineItem);
			
			
		} finally {
			cusDAO.endSession();
			cartDAO.endSession();
		}
	}

	private void updateCart(HttpServletRequest request) {
		CustomerDAO cusDAO = new CustomerService();
		CartDAO cartDAO = new CartService();
		try {
			int bookID = Integer.parseInt(request.getParameter("id"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			
			HttpSession session = request.getSession();
			Customer customer = (Customer) session.getAttribute("user");
			Cart cart = cusDAO.getCurrentCart(customer);
			
			
			Book book = new Book();
			book.setId(bookID);
			LineItem lineItem = new LineItem();
			lineItem.setBook(book);
			lineItem.setQuantity(quantity);
			Cart newCart = cartDAO.updateLineItem(cart, lineItem);
			request.setAttribute("cart", newCart);
			
		} finally {
			cusDAO.endSession();
			cartDAO.endSession();
		}
	}
}
