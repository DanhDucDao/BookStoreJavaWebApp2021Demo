package controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.customer.CustomerDAO;
import dao.customer.CustomerService;
import dao.customer.exception.InvalidAccount;
import model.customer.Customer;
import model.customer.account.AccountStatus;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = -4187085825782402500L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		CustomerDAO customerDAO = new CustomerService();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean check = checkInput(username, password);
		if(check == false ) {
			request.setAttribute("error", "User name or password is empty");
			sendErrorPage(request, response);
			return;
		}
		
		try {
			Customer customer = customerDAO.checkLogin(username, password);
			String returnUrl = request.getParameter("returnUrl");
			if(returnUrl == null) {
				returnUrl = getServletContext().getContextPath();
			}
			
			if(customer.getAccount().getStatus() == AccountStatus.BAND) {
				request.setAttribute("error", "You are band");
				sendErrorPage(request, response);
				return;
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("user", customer);
			
			response.sendRedirect(returnUrl);
		} catch (InvalidAccount e) {
			request.setAttribute("error", e.getMessage());
			sendErrorPage(request, response);
			return;
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			customerDAO.endSession();
		}
		
	}
	
	private boolean checkInput(String username, String password) {
		if(username == null || password == null || username.isEmpty() || password.isEmpty()) {
			return false;
		}
		return true;
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	private void sendErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
	}
	
	
}
