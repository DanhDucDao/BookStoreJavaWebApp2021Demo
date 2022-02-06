package model.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;

@Entity
@PrimaryKeyJoinColumn(name = "PaymentID")
@DiscriminatorValue("CREDIT")
public class Credit extends Payment implements Serializable{
	private static final long serialVersionUID = -9181556115945224080L;

	private String number;
	
	private String type;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expDate;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Override
	public String toString() {
		return "Credit [number=" + number + ", type=" + type + ", expDate=" + expDate + "]";
	}

	
}
