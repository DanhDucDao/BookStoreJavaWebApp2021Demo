package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.customer.Admin;
import model.customer.Customer;

@WebFilter("/admin/*")
public class AdminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			request.setAttribute("returnUrl", request.getRequestURI());
			request.getServletContext().getRequestDispatcher("/login").forward(request, response);
			return;
		}
		
		Customer customer = (Customer) session.getAttribute("user");
		if((customer instanceof Admin) && customer.getAccount().hasRoles("ADMIN") ) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("error", "Khong du tham quyen");
			request.getServletContext().getRequestDispatcher("/error").forward(request, response);
			return;
		}
	}

}
