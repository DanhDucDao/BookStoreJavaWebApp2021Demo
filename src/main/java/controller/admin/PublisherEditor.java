package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.book.PublisherDAO;
import dao.book.PublisherService;
import model.book.Publisher;

@WebServlet("/admin/publisher/edit")
public class PublisherEditor extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublisherDAO service = new PublisherService();
		
		String idStr = request.getParameter("id");
		try {
			int id = Integer.parseInt(idStr);
			
			Publisher publisher = service.findById(id);
			request.setAttribute("publisher", publisher);
			getServletContext().getRequestDispatcher("/admin/book/publisher-edit.jsp").forward(request, response);
			
		} catch (NumberFormatException ex) {
			request.setAttribute("error", "id not a number");
			getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
			return;
		} catch (IllegalArgumentException ex) {
			request.setAttribute("error", ex.getMessage());
			getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id").trim());
			String name = request.getParameter("name").trim();
			String desc = request.getParameter("description").trim();
			String address = request.getParameter("address").trim();
			String phoneNumber = request.getParameter("phoneNumber").trim();
			String email = request.getParameter("email").trim();
			
			Publisher publisher = new Publisher();
			publisher.setId(id);
			publisher.setAddress(address);
			publisher.setName(name);
			publisher.setDescription(desc);
			publisher.setPhoneNumber(phoneNumber);
			publisher.setEmail(email);
			
			PublisherDAO service = new PublisherService();
			service.editPublisher(publisher);
			response.sendRedirect(getServletContext().getContextPath() + "/admin/publisher");
		} catch (NumberFormatException e) {
			request.setAttribute("error", "id not a number");
			getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
			return;
		}
	}

}
