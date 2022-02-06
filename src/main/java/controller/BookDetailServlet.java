package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.book.BookDAO;
import dao.book.BookService;
import model.book.Book;

@WebServlet("/bookDetail")
public class BookDetailServlet extends HttpServlet{
	private static final long serialVersionUID = 2129918645134481326L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		BookDAO bookDAO = new BookService();
		try {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = bookDAO.findById(bookId);
			
			request.setAttribute("book", book);
			getServletContext().getRequestDispatcher("/customer/book-detail.jsp").forward(request, response);
		} catch (NumberFormatException ex) {
			request.setAttribute("error", ex.getStackTrace());
			sendErrorPage(request, response);
		} catch (IllegalArgumentException ex) {
			request.setAttribute("error", ex.getMessage());
			sendErrorPage(request, response);
		}
		finally {
			bookDAO.endSession();
		}
	}
	
	private void sendErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
	}
	
}
