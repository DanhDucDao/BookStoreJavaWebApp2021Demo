package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.book.AuthorDAO;
import dao.book.AuthorService;
import model.book.Author;


@WebServlet("/admin/author/edit")
public class AuthorEditor extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAuthor = request.getParameter("id").trim();
		if(idAuthor.isEmpty()) {
			request.setAttribute("error", "No ID");
			getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
			return;
		}
		AuthorService service = new AuthorService();
		Author author = service.findById(Integer.parseInt(idAuthor));
		request.setAttribute("author", author);
		getServletContext().getRequestDispatcher("/admin/book/author-edit.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAuthor = request.getParameter("id").trim();
		String name = request.getParameter("name").trim();
		String biography = request.getParameter("biography").trim();
		
		if(idAuthor.isEmpty() || name.isEmpty() || biography.isEmpty()) {
			request.setAttribute("error", "Empty field");
			getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
			return;
		}
		
		Author author = new Author();
		author.setId(Integer.parseInt(idAuthor));
		author.setName(name);
		author.setBiography(biography);
		AuthorDAO service = new AuthorService();
		service.editAuthor(author);
		response.sendRedirect(getServletContext().getContextPath() + "/admin/author");
	}

}
