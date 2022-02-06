package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.book.PublisherDAO;
import dao.book.PublisherService;
import model.book.Publisher;

@WebServlet("/admin/publisher")
public class PublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublisherDAO service = new PublisherService();
		List<Publisher> publishers = service.getAllPublisher();
		request.setAttribute("publishers", publishers);
		getServletContext().getRequestDispatcher("/admin/book/publisher.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublisherDAO service = new PublisherService();
		
		String name = request.getParameter("name").trim();
		String desc = request.getParameter("description").trim();
		String address = request.getParameter("address").trim();
		String phoneNumber = request.getParameter("phoneNumber").trim();
		String email = request.getParameter("email").trim();
		
		Publisher publisher = new Publisher();
		publisher.setAddress(address);
		publisher.setName(name);
		publisher.setDescription(desc);
		publisher.setPhoneNumber(phoneNumber);
		publisher.setEmail(email);
		
		if(name.isEmpty() || desc.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
			request.setAttribute("error", "Empty Field");
			getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
			return;
		}
		
		service.addPublisher(publisher);
		response.sendRedirect(getServletContext().getContextPath() + "/admin/publisher");
	}

}
