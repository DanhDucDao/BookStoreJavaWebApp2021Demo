package dao.book;

import java.util.List;

import model.book.Author;

public interface AuthorDAO {
	
	Author createAuthor(Author author);
	
	Author editAuthor(Author author);
	
	List<Author> getAllAuthor();
	
	Author findById(int id);
	
	void endSession();
}
