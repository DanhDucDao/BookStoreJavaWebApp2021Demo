package dao.book;

import java.util.List;

import model.book.BookItem;

public interface BookItemDAO {
	
	/**
	 * @param bookItem an instance must have a association book with specific id for JPA mapping
	 * @return book item added to database
	 */
	BookItem addBookItem(BookItem bookItem);
	
	/**
	 * find all availabel book items that are not in any order of a book
	 * @param bookId id of the book
	 * @return all available book it
	 */
	List<BookItem> getAllAvailableBookItem(int bookId);
	
	void endSession();
}
