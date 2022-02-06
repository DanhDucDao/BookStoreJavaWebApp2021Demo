package model.book;

public enum BookStatus {
	AVAILABLE("AVAILABLE"), STOP_SALE("STOP"), CONTACT("CONTACT"), OUT_OF_STOCK("OUT_OF_STOCK");
	
	private String name;
	
	private BookStatus(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
