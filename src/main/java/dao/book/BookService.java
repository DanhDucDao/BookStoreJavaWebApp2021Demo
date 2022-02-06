package dao.book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;


import dao.DBUtil;
import model.book.Author;
import model.book.Book;
import model.book.Publisher;

public class BookService implements BookDAO{
	private EntityManager en;

	public BookService() {
		en = DBUtil.getEntityManager();
	}
	
	@Override
	@Deprecated
	public Book addBookOld(Book book) {
		en.getTransaction().begin();
		
		List<Author> authors = new ArrayList<>();
		List<Publisher> publishers = new ArrayList<>();
		if(book.getAuthors() != null || book.getAuthors().size() > 0) {
			for(Author a : book.getAuthors()) {
				Author author = en.find(Author.class, a.getId());
				authors.add(author);
			}
		}
		if(book.getPublishers() != null || book.getPublishers().size() > 0) {
			for(Publisher a : book.getPublishers()) {
				Publisher p = en.find(Publisher.class, a.getId());
				publishers.add(p);
			}
		}
		
		book.setAuthors(authors);
		book.setPublishers(publishers);
		en.persist(book);
		
		en.getTransaction().commit();
		en.clear();
		return book;
	}

	@Override
	public Book addBook(Book b) throws PersistenceException{
		Book result = null;
		en.getTransaction().begin();
		b.setCreateDate(new Date());
		en.persist(b);
		en.getTransaction().commit();
		en.clear();
		
		result = en.find(Book.class, b.getId());
		
		// trigger loading authors and books
		if(result.getAuthors()==null) {
			result.setAuthors(new ArrayList<Author>());
		}
		if(result.getPublishers() == null) {
			result.setPublishers(new ArrayList<Publisher>());;
		}
		List<Author> authors = result.getAuthors();
		for(int i = 0; i<authors.size(); i++) {
			// trigger loading !! trick !
			authors.get(i).getName();
		}
		if(result.getPublishers() != null) {
			List<Publisher> pubs = result.getPublishers();
			for(int i = 0; i<pubs.size();i++) {
				pubs.get(i).getName();
			}
		}
		en.clear();
		return result;
	}
	
	@Override
	public Book findById(int id) throws IllegalArgumentException{
		Book result = en.find(Book.class, id);
		if(result == null) 
			throw new IllegalArgumentException("Not found id : " + id);
		// trigger loading authors and books
		if(result.getAuthors()==null) {
			result.setAuthors(new ArrayList<Author>());
		}
		if(result.getPublishers() == null) {
			result.setPublishers(new ArrayList<Publisher>());;
		}
		List<Author> authors = result.getAuthors();
		for(int i = 0; i<authors.size(); i++) {
			// trigger loading !! trick !
			authors.get(i).getName();
		}
		List<Publisher> pubs = result.getPublishers();
		for(int i = 0; i<pubs.size();i++) {
			pubs.get(i).getName();
		}
		en.clear();
		return result;
	}
	@Override
	public List<Book> getAllBooks() {
		try {
			TypedQuery<Book> query = en.createQuery("SELECT e FROM Book e", Book.class);
			return query.getResultList();
		} finally {
			en.clear();
		}
		
	}

	@Override
	public Book editBook(Book book) throws IllegalArgumentException {
		try {
			en.getTransaction().begin();
			Book oldBook = en.find(Book.class, book.getId());
			if(oldBook == null) {
				throw new IllegalArgumentException("Not have an ID");
			}
			copyValue(book, oldBook);
			en.getTransaction().commit();
			return oldBook;
		} finally {
			en.clear();
		}
		
	}

	private void copyValue(Book book, Book oldBook) {
		if(book.getIsbn() != null) {
			oldBook.setIsbn(book.getIsbn());
		}
		if(book.getTitle() != null) {
			oldBook.setTitle(book.getTitle());
		}
		if(book.getSummary() != null) {
			oldBook.setSummary(book.getSummary());
		}
		if(book.getPublicationDate() != null)
			oldBook.setPublicationDate(book.getPublicationDate());
		oldBook.setNumberOfPages(book.getNumberOfPages());
		
		if(book.getLanguage() != null) {
			oldBook.setLanguage(book.getLanguage());
		}
		
		if(book.getCoverImage() != null) {
			oldBook.setCoverImage(book.getCoverImage());
		}
		
		oldBook.setPrice(book.getPrice());
		
		if(book.getStatus() != null) {
			oldBook.setStatus(book.getStatus());
		}
		
		if(book.getAuthors() != null) {
			List<Author> authors = new ArrayList<Author>();
			for(Author author : book.getAuthors()) {
				Author au = en.find(Author.class, author.getId());
				en.merge(au);
				authors.add(au);
				
			}
			System.out.println(authors);
			oldBook.setAuthors(authors);
		}
		
		if(book.getPublishers() != null)
			oldBook.setPublishers(book.getPublishers());
	}
	
	@Override
	public void endSession() {
		if(en != null && en.isOpen() == true)
			en.close();
	}

	@Override
	public List<Book> getAvailableBooks(int pageIndex, int pageSize) {
		try {
			TypedQuery<Book> query = en.createQuery("SELECT e FROM Book e ORDER BY e.createDate DESC", Book.class);
			return query.setFirstResult(pageSize * pageIndex)
					.setMaxResults(pageSize)
					.getResultList();
		} finally {
			en.clear();
		}
	}

	@Override
	public int countAllBook() {
		try {
			TypedQuery<Long> query = en.createQuery("SELECT COUNT(e) FROM Book e", Long.class);
			return query.getSingleResult().intValue();
		} finally {
			en.clear();
		}
	}
}
