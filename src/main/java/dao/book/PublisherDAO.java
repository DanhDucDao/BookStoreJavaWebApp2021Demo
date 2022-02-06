package dao.book;

import java.util.List;

import model.book.Publisher;

public interface PublisherDAO {
	/**
	 * @param publisher instance of the publisher you want to save to database, must not have id
	 * @return publisher saved in the database
	 */
	Publisher addPublisher(Publisher publisher);
	
	/**
	 * @param publisher instance of the publisher you want to edit to database, must have a correct id
	 * @return publisher edited in the database
	 */
	Publisher editPublisher(Publisher publisher);
	
	/**
	 * @return all publishers in database
	 */
	List<Publisher> getAllPublisher();
	
	/**
	 * 
	 * @param id ID of an Publisher
	 * @return Publisher instance
	 * @throws IllegalArgumentException if publisher doesn't exist
	 */
	Publisher findById(int id);
	
	void endSession();
	
	
}
