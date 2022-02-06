package dao.testinginterface;

import java.util.List;

import dao.book.PublisherDAO;
import dao.book.PublisherService;
import model.book.Publisher;

public class TestPublisher {
	public static void main(String[] args) {
		addPub();
		listPub();
	}
	
	public static void addPub() {
		Publisher p = new Publisher("Shogakukan 3", "Den Tu Viet Name", "Shoga@vn.com", "Ha Noi", "0987656789");
		PublisherDAO pd = new PublisherService();
		Publisher newp =  pd.addPublisher(p);
		System.out.println(newp);
	}
	
	public static void editPub() {
		Publisher p = new Publisher("Kim Dong 2", "Den Tu Viet Name", "kimdong@vn.com", "Ha Noi", "0987656789");
		p.setId(1);
		PublisherDAO pd = new PublisherService();
		Publisher newp =  pd.editPublisher(p);
		System.out.println(newp);
	}
	
	public static void listPub() {
		List<Publisher> l = new PublisherService().getAllPublisher();
		for(Publisher p : l) {
			System.out.println(p);
		}
	}
	
}
