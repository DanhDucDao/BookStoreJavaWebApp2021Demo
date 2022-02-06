package model.customer;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Fresher")
@PrimaryKeyJoinColumn(name = "CustomerID")
@DiscriminatorValue("FRESHER")
@Access(AccessType.FIELD)
public class Fresher extends Customer{
	private static final long serialVersionUID = -3655268793556767028L;
	
	private int point;
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return super.toString() + " is a Fresher [point=" + point + "]";
	}
	

}
