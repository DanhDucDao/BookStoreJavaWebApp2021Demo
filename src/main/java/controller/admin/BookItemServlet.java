package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.book.BookDAO;
import dao.book.BookItemDAO;
import dao.book.BookItemService;
import dao.book.BookService;
import model.book.Book;
import model.book.BookItem;

@WebServlet("/admin/bookItem")
public class BookItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO bookDAO = new BookService();
		BookItemDAO bookItemDAO = new BookItemService();
		try {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			
			Book book = bookDAO.findById(bookId);
			request.setAttribute("book", book);
			
			List<BookItem> bookItems = bookItemDAO.getAllAvailableBookItem(book.getId());
			request.setAttribute("bookItems", bookItems);
			System.out.println(bookItems);
			getServletContext().getRequestDispatcher("/admin/book/book-item.jsp")
				.forward(request, response);
			
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Book ID must be a String");
			sendErrorPage(request, response);
		} catch (IllegalArgumentException e) {
			request.setAttribute("error",e.getMessage());
			sendErrorPage(request, response);
		} finally {
			bookDAO.endSession();
			bookItemDAO.endSession();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookItemDAO bookItemDAO = new BookItemService();
		try {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			String barCode = request.getParameter("barCode");
			float price = Float.parseFloat(request.getParameter("importPrice"));
			
			Book book = new Book();
			book.setId(bookId);
			BookItem bookItem = new BookItem();
			bookItem.setAssocciationBook(book);
			bookItem.setBarCode(barCode);
			bookItem.setImportPrice(price);
			bookItemDAO.addBookItem(bookItem);
			response.sendRedirect(getServletContext().getContextPath()+"/admin/bookItem?bookId=" + bookId);
			
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Book ID must be a String");
			sendErrorPage(request, response);
		} catch (IllegalArgumentException e) {
			
		}
		finally {
			if(bookItemDAO != null)
				bookItemDAO.endSession();
		}
	}
	
	private void sendErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
	}

}
