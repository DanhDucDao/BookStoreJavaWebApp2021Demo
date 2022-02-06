package model.customer.gender;

public enum Gender {
	MALE("MALE"), FEMALE("FEMALE"), GAY("GAY"), LESBIAN("LES");

	private String type;
	private Gender(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
}
