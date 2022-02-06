package model.book;


import java.util.Date;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJPA {
	private static EntityManagerFactory em = Persistence.createEntityManagerFactory("book_store");
	public static EntityManager getEntityManager() {
		return em.createEntityManager();
	}
	
	public static void main(String[] args) {
		EntityManager em = getEntityManager();
		
		Book book = new Book();
		book.setIsbn("9999999999999999");
		book.setTitle("Doraemon vol. 1");
		book.setLanguage("Vietnamese");
		book.setCreateDate(new Date());
		book.setPublicationDate(new Date());
		book.setCoverImage("Doremon.jpg");
		book.setPrice(100);
		book.setNumberOfPages(12);
		book.setSummary("Story about Doremon and his friends!");
		book.setStatus(BookStatus.AVAILABLE);
//		
//		Author au = em.find(Author.class, 1);
//		List<Author> aus = new ArrayList(); 
//		aus.add(au);
//		
//		Publisher pub1 = em.find(Publisher.class, 1);
//		Publisher pub2 = em.find(Publisher.class, 2);
//		List<Publisher> pub = new ArrayList();
//		pub.add(pub1);
//		pub.add(pub2);
//		
//		book.setAuthors(aus);
//		book.setPublishers(pub);
//		em.persist(book);
		
		book = em.find(Book.class, 1);
		
		System.out.println(book);
		for(Author au : book.getAuthors()) {
			System.out.println(au);
		}
		
		for(Publisher p : book.getPublishers())
		{
			System.out.println(p);
		}
	}
			
}
