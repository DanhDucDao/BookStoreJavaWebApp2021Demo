package model.order;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("CASH")
@PrimaryKeyJoinColumn(name = "PaymentID")
public class Cash extends Payment implements Serializable{
	private static final long serialVersionUID = -5162007987217039722L;
	private float cashTendered;

	public float getCashTendered() {
		return cashTendered;
	}

	public void setCashTendered(float cashTendered) {
		this.cashTendered = cashTendered;
	}
	
	
}
