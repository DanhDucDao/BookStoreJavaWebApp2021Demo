package dao.book;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dao.DBUtil;
import model.book.Author;

public class AuthorService implements AuthorDAO{
	private EntityManager en;
	
	public AuthorService() {
		en = DBUtil.getEntityManager();
	}
	
	@Override
	public Author createAuthor(Author author){
		en.getTransaction().begin();
		en.persist(author);
		en.getTransaction().commit();
		en.clear();
		return author;
	}

	@Override
	public Author editAuthor(Author author) {
		en.getTransaction().begin();
		Author toEditAuthor = en.find(Author.class, author.getId());
		toEditAuthor.setName(author.getName());
		toEditAuthor.setBiography(author.getBiography());
		en.getTransaction().commit();
		en.clear();
		return toEditAuthor;
	}

	@Override
	public List<Author> getAllAuthor() {
		TypedQuery<Author> query = en.createNamedQuery("dao.selectAllAuthors", Author.class);
		en.clear();
		return query.getResultList();
	}
	
	public void endSession() {
		if(en != null && en.isOpen() == true)
			en.close();
	}

	@Override
	public Author findById(int id) {
		Author author = en.find(Author.class, id);
		en.clear();
		return author;
		
	}

}
