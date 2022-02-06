package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.helper.CartJSON;
import controller.helper.LineItemJSON;
import dao.customer.CustomerDAO;
import dao.customer.CustomerService;
import dao.order.CartDAO;
import dao.order.CartService;
import model.book.Book;
import model.customer.Customer;
import model.order.Cart;
import model.order.LineItem;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		CartDAO cartDAO = new CartService();
		CustomerDAO customerDAO = new CustomerService();
		try {
			int bookId  = Integer.parseInt(request.getParameter("bookId"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			
			Book book = new Book();
			book.setId(bookId);
			
			LineItem lineItem = new LineItem();
			lineItem.setBook(book);
			lineItem.setQuantity(quantity);
			
			HttpSession session = request.getSession();
			Customer customer = (Customer) session.getAttribute("user");
			Cart cart = customerDAO.getCurrentCart(customer);
			
			cartDAO.addToCart(lineItem, cart);
			
			cart = customerDAO.getCurrentCart(customer);
			
			String json = parseJSON(cart);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			PrintWriter print = response.getWriter();
			
			try {
				print.write(json);
				print.flush();
			} finally {
				print.close();
			}			
		} catch (NumberFormatException e) {
			request.setAttribute("error", e.getMessage());
			sendErrorPage(request, response);
		} finally {
			cartDAO.endSession();
			customerDAO.endSession();
		}
		
	}
	
	private String parseJSON(Cart cart) throws JsonProcessingException {
		List<LineItemJSON> list = new ArrayList<LineItemJSON>();
		for(LineItem l : cart.getLineitems()) {
			LineItemJSON lij = new LineItemJSON();
			lij.lineItemId = l.getId();
			lij.bookId = l.getBook().getId();
			lij.price = l.getPrice();
			lij.discount = l.getDiscount();
			lij.quantity = l.getQuantity();
			lij.title = l.getBook().getTitle();
			list.add(lij);
		}
		
		CartJSON cartJSON = new CartJSON();
		cartJSON.createDate = cart.getCreateDate();
		cartJSON.lineJsons = list;
		return new ObjectMapper().writeValueAsString(cartJSON);
	}

	private void sendErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
	}
	

}
