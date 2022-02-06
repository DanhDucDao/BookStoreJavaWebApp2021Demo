package model.order.status;

public enum PaymentStatus {
	
	PAID("PAID"), WAIT("WAIT");
	
	private String value;
	
	private PaymentStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
