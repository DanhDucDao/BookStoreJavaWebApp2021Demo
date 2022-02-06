package dao.customer.exception;

public class InvalidAccount extends Exception{
	private static final long serialVersionUID = -6197347869045992873L;
	private String message;
	
	public InvalidAccount(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
