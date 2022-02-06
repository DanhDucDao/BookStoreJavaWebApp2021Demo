package dao.book;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dao.DBUtil;
import model.book.BookItem;
import model.book.Book;

public class BookItemService implements BookItemDAO{
	EntityManager en;
	
	public BookItemService() {
		en = DBUtil.getEntityManager();
	}
	
	@Override
	public BookItem addBookItem(BookItem bookItem) {
		try {
			en.getTransaction().begin();
			bookItem.setImportDate(new Date());
			en.persist(bookItem);
			en.getTransaction().commit();
			en.detach(bookItem);
			
			BookItem result = en.find(BookItem.class, bookItem.getId());
			return result;
		} finally {
			en.clear();
		}
		
	}

	@Override
	public List<BookItem> getAllAvailableBookItem(int bookId){
		try {
			
			Book book = en.find(Book.class, bookId);
			if(book == null)
				throw new IllegalArgumentException("None book with id : " + bookId);
			TypedQuery<BookItem> query = en.createQuery("SELECT e FROM BookItem e "
					+ " WHERE e.book = :book AND e.orderItem IS EMPTY", BookItem.class);
			query.setParameter("book", book);
			return query.getResultList();
		} finally {
			en.clear();
		}
	}
	
	@Override
	public void endSession() {
		if(en != null && en.isOpen() == true)
			en.close();
	}

}
