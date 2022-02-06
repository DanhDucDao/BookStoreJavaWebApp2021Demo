package model.order.status;

public enum OrderStatus {
	WAIT_FOR_COMFIRM("WAIT_FOR_COFIRM"), PREPARE("PREPARE"), SHIPPING("SHIPPING"), SUCCESS("END"), CANCEL("CANCEL");
	
	private String value;
	
	private OrderStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
