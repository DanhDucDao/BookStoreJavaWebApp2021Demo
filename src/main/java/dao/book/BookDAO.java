package dao.book;

import java.util.List;

import javax.persistence.PersistenceException;

import model.book.Book;

public interface BookDAO {
	/**
	 * @deprecated
	 * @param book must have list of books or list of authors, each item in which must have an id
	 * @return book added
	 */
	Book addBookOld(Book book);
	
	/**
	 * @param book must have list of books or list of authors, each item in which must have an id
	 * @return book added
	 */
	Book addBook(Book book) throws PersistenceException;
	
	/**
	 * find book by it's id
	 * @param id id of the book to find
	 * @return an instance of book in database
	 * @throws IllegalArgumentException if id is not found in database
	 */
	Book findById(int id) throws IllegalArgumentException;
	
	/**
	 * edit a book in database
	 * @param book an instance of book you want to change status
	 * @return instance edited in database
	 * @throws IllegalArgumentException if the book with the correspond id not found
	 */
	Book editBook(Book book) throws IllegalArgumentException;
	
	/**
	 * find all book
	 * @return	all books in database
	 */
	List<Book> getAllBooks();
	
	/**
	 * 
	 * @param pageIndex index of the page
	 * @param pageSize size of the page
	 * @return list of book
	 */
	List<Book> getAvailableBooks(int pageIndex, int pageSize);
	
	int countAllBook();
	
	/**
	 * close resoure
	 */
	void endSession();
}
