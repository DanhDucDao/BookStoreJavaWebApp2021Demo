package model.customer.member;

public enum MemberLevel {
	SILVER("SILVER"), GOLD("GOLD"), DIAMOND("DIAMOND");
	
	private String value;
	
	private MemberLevel(String value) {
		this.value =value;
	}
	
	public String getValue() {
		return this.value;
	}
}
