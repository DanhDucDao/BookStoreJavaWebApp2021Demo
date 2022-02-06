package model.customer;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import model.customer.member.MemberLevel;

import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

@Entity
@Table(name = "Member")
@PrimaryKeyJoinColumn(name = "CustomerID")
@DiscriminatorValue("MEMBER")
@Access(AccessType.FIELD)
public class Member extends Customer{
	private static final long serialVersionUID = 6212097009892420885L;

	@Column(name = "Level")
	private MemberLevel level;
	
	private String card;

	public MemberLevel getLevel() {
		return level;
	}

	public void setLevel(MemberLevel level) {
		this.level = level;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	@Override
	public String toString() {
		return super.toString() +  " is Member [level=" + level + ", card=" + card + "]";
	}
	
	
}
