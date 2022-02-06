package dao.customer.exception;

public class UserNameExist extends Exception{
	private static final long serialVersionUID = -6197347869045992873L;
	private String message;
	
	public UserNameExist(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
