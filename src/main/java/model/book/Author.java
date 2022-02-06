package model.book;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( name = "Author" )
@NamedQueries(@NamedQuery(name = "dao.selectAllAuthors", query = "SELECT e FROM Author e"))
public class Author implements Serializable{
	private static final long serialVersionUID = -5260725505963802909L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Biography")
	private String biography;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", biography=" + biography + "]";
	}	
}
