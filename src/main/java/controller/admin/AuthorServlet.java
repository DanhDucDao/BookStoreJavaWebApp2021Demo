package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.book.AuthorService;
import model.book.Author;

/**
 * Servlet implementation class author
 */
@WebServlet(urlPatterns = {"/admin/author"})
public class AuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthorService service = new AuthorService();
		List<Author> authors = service.getAllAuthor();
		request.setAttribute("authors", authors);
		getServletContext().getRequestDispatcher("/admin/book/author.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
