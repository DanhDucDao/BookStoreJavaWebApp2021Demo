package controller.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.book.AuthorDAO;
import dao.book.AuthorService;
import dao.book.BookDAO;
import dao.book.BookService;
import dao.book.PublisherDAO;
import dao.book.PublisherService;
import model.book.Author;
import model.book.Book;
import model.book.BookStatus;
import model.book.Publisher;

@WebServlet("/admin/book/edit")
@MultipartConfig
public class BookEditor extends HttpServlet{
	private static final long serialVersionUID = -1279832731258190075L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			int bookId = Integer.parseInt(request.getParameter("id"));
			
			BookDAO service = new BookService();
			Book book = service.findById(bookId);
			AuthorDAO authorDAO = new AuthorService();
			PublisherDAO pubDAO = new PublisherService();
			List<Publisher> publishers = pubDAO.getAllPublisher();
			List<Author> authors = authorDAO.getAllAuthor();
			
			request.setAttribute("book", book);
			request.setAttribute("authors", authors);
			request.setAttribute("publishers", publishers);
			
			getServletContext().getRequestDispatcher("/admin/book/book-edit.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Wrong Number");
			sendErrorPage(request, response);
		} catch (IllegalArgumentException e) {
			request.setAttribute("error", e.getMessage());
			sendErrorPage(request, response);
		}
		
	}
	
	private void sendErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/admin/error.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		BookDAO service = new BookService();
		try {
			Book book = parserequestToBook(request);
			service.editBook(book);
			response.sendRedirect(getServletContext().getContextPath()+ "/admin/book");
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Wrong Number");
			sendErrorPage(request, response);
		} catch (IllegalArgumentException e) {
			request.setAttribute("error", e.getMessage());
			sendErrorPage(request, response);
		} catch (ParseException e) {
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
		} finally {
			service.endSession();
		}
		
	}
	
	private Book parserequestToBook(HttpServletRequest request) throws ParseException, NumberFormatException {
		int id = Integer.parseInt(request.getParameter("id"));
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		String numberOfPages = request.getParameter("numberOfPages");
		String language = request.getParameter("language");
		String price = request.getParameter("price");
		
		String authorIDs[] = request.getParameterValues("authors");
		if(authorIDs == null)
			authorIDs = new String[0];
		List<Author> authors = new ArrayList<Author>();
		for(String authorID : authorIDs) {
			System.out.println(authorID);
			Author author = new Author();
			author.setId(Integer.parseInt(authorID));
			authors.add(author);
		}
		
		String publisherIDs[] = request.getParameterValues("publishers");
		if(publisherIDs == null)
			publisherIDs = new String[0];
		List<Publisher> publishers = new ArrayList<Publisher>();
		for(String publisherID : publisherIDs) {
			Publisher publisher = new Publisher();
			publisher.setId(Integer.parseInt(publisherID));
			publishers.add(publisher);
		}
		
		String publicationDateStr = request.getParameter("publicationDate");
		Date publicationDate = new SimpleDateFormat("yyyy-MM-dd").parse(publicationDateStr);
		
		Book book = new Book();
		book.setId(id);
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setSummary(summary);
		book.setNumberOfPages(Integer.parseInt(numberOfPages));
		book.setLanguage(language);
		book.setPrice(Float.parseFloat(price));
		
		book.setAuthors(authors);
		book.setPublishers(publishers);
		
		book.setPublicationDate(publicationDate);
		
		book.setStatus(BookStatus.AVAILABLE);
		
		return book;
	}

}
