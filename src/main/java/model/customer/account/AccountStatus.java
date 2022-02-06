package model.customer.account;

public enum AccountStatus {
	OKE("OKE"), BAND("BAND"), RESTRICT("RESTRICT");
	
	private String value;
	private AccountStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
