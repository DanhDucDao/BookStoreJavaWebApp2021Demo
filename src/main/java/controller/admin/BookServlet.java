package controller.admin;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.helper.FileNameUtil;
import controller.helper.FileUploader;
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

@WebServlet("/admin/book")
@MultipartConfig
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 5602152790715017744L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO service = new BookService();
		try {
			Book book = parserequestToBook(request);
			
			Part part = request.getPart("coverImage");
			String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			FileNameUtil fileNameU = new FileNameUtil(fileName);
			String coverImage = UUID.randomUUID().toString() + "." + fileNameU.getExtension();
			
			book.setCoverImage(coverImage);
			
			
			service.addBook(book);
			FileUploader fileUploader = new FileUploader(coverImage, part.getInputStream());
			fileUploader.writeToServer();
			response.sendRedirect(getServletContext().getContextPath()+"/admin/book");

		} catch (ParseException | PersistenceException e) {
			e.printStackTrace();
		} finally {
			service.endSession();
		}
	}
	
	private Book parserequestToBook(HttpServletRequest request) throws ParseException {
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
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setSummary(summary);
		book.setNumberOfPages(Integer.parseInt(numberOfPages));
		book.setLanguage(language);
		book.setPrice(Integer.parseInt(price));
		
		book.setAuthors(authors);
		book.setPublishers(publishers);
		
		book.setPublicationDate(publicationDate);
		
		book.setStatus(BookStatus.AVAILABLE);
		
		return book;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO service = new BookService();
		AuthorDAO authorDAO = new AuthorService();
		PublisherDAO pubDAO = new PublisherService();
		
		List<Book> books = service.getAllBooks();
		List<Publisher> publishers = pubDAO.getAllPublisher();
		List<Author> authors = authorDAO.getAllAuthor();
		
		request.setAttribute("books", books);
		request.setAttribute("authors", authors);
		request.setAttribute("publishers", publishers);
		
		getServletContext().getRequestDispatcher("/admin/book/book.jsp").forward(request, response);
	}
}
