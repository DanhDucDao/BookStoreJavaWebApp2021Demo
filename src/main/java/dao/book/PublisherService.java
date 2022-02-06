package dao.book;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import dao.DBUtil;
import model.book.Publisher;

public class PublisherService implements PublisherDAO{
	private EntityManager en;
	
	public PublisherService() {
		en = DBUtil.getEntityManager();
	}
	
	@Override
	public Publisher addPublisher(Publisher publisher) {
		en.getTransaction().begin();
		en.persist(publisher);
		en.getTransaction().commit();
		en.clear();
		return publisher;
	}

	@Override
	public Publisher editPublisher(Publisher publisher) {
		en.getTransaction().begin();
		
		Publisher toEdit = en.find(Publisher.class, publisher.getId());
		toEdit.setName(publisher.getName());
		toEdit.setPhoneNumber(publisher.getPhoneNumber());
		toEdit.setEmail(publisher.getEmail());
		toEdit.setAddress(publisher.getAddress());
		toEdit.setDescription(publisher.getDescription());
		en.getTransaction().commit();
		en.clear();
		return toEdit;
	}

	@Override
	public List<Publisher> getAllPublisher() {
		TypedQuery<Publisher> query = en.createNamedQuery("dao.selectAllPublishers", Publisher.class);
		en.clear();
		return query.getResultList();
	}

	@Override
	public Publisher findById(int id) {
		try {
			Publisher publisher = en.find(Publisher.class, id);
			
			if(publisher == null) 
				throw new IllegalArgumentException("None Publisher with id : " + id + " exists");
			
			return publisher;
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
