package model.customer;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
@DiscriminatorValue("ADMIN")
@PrimaryKeyJoinColumn(name = "CustomerID")
@Access(AccessType.FIELD)
public class Admin extends Customer{

	private static final long serialVersionUID = -1828592832056729063L;
	
	private String securityId;
	
	private String position;

	private String imageString;

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getImageString() {
		return imageString;
	}

	public void setImageString(String imageString) {
		this.imageString = imageString;
	}

	@Override
	public String toString() {
		return super.toString() + " is : Admin [securityId=" + securityId + ", position=" + position + ", imageString="
				+ imageString + "]";
	}
	
	
}
