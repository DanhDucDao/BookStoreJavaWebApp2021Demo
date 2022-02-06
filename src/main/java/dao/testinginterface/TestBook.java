package dao.testinginterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.book.BookDAO;
import dao.book.BookItemDAO;
import dao.book.BookItemService;
import dao.book.BookService;
import model.book.Author;
import model.book.Book;
import model.book.BookItem;
import model.book.BookStatus;
import model.book.Publisher;

public class TestBook {
	public static void main(String[] args) {
		addItem();
	}
	
	private static void addItem() {
		BookItem item = new BookItem();
		item.setBarCode("d1b0b7fe-5998-11ec-bf63-0242ac130002");
		item.setImportDate(new Date());
		item.setImportPrice(100000);
		Book b = new Book();
		b.setId(1);
		item.setAssocciationBook(b);
		BookItemDAO dao = new BookItemService();
		item = dao.addBookItem(item);
		System.out.println(item);
		
	}
	
	private static void add() throws Exception {
		//Publisher p1 = new Publisher();p1.setId(1);
		Author a1 = new Author();a1.setId(1);
		Author a2 = new Author();a2.setId(2);
		
		List<Publisher> ps = new ArrayList<Publisher>();
		//ps.add(p1);
		List<Author> as = new ArrayList<Author>();
		as.add(a1);
		as.add(a2);
		
		Book book = new Book();
		book.setIsbn("9988889999");
		book.setTitle("Tam Quoc");
		book.setLanguage("Vietnamese");
		book.setCreateDate(new Date());
		book.setPublicationDate(new Date());
		book.setCoverImage("Doremon.jpg");
		book.setPrice(100);
		book.setNumberOfPages(12);
		book.setSummary("Story about Doremon and his friends!");
		book.setStatus(BookStatus.AVAILABLE);
		book.setAuthors(as);
		//book.setPublishers(ps);
		
		BookDAO dao = new BookService();
		Book newb = dao.addBook(book);
		System.out.println(newb);
		
	}
	
	private static void getbyId(int i) {
		BookDAO dao = new BookService();
		System.out.println(dao.findById(i));
	}
}
