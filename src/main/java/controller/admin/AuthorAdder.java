package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.book.AuthorService;
import model.book.Author;

@WebServlet("/admin/author/add")
public class AuthorAdder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("error", "Method Forbidden : GET");
		getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorService service = new AuthorService();
		try {
			String name = request.getParameter("name").trim();
			String bio = request.getParameter("bio").trim();
			System.out.println(name + bio);
			if(name.isEmpty() || bio.isEmpty()) {
				request.setAttribute("error", "Ten va Bio khong duoc bo trong");
				getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
				return;
			}
			
			Author author = new Author();
			author.setName(name);
			author.setBiography(bio);
			
			
			service.createAuthor(author);
			response.sendRedirect(getServletContext().getContextPath()+"/admin/author");
		} finally {
			service.endSession();
		}
	}

}
