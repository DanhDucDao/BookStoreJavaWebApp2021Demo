package model.order.status;

public enum ShipmentStatus {
	NONE("NONE"), START("START"), DELAY("DELAY"), CANCEL("CANCEL"), SHIPPED("SHIPPED");
	
	private String value;
	
	private ShipmentStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
