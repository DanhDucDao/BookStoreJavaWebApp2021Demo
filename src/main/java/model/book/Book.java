package model.book;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Book")
public class Book implements Serializable{

	private static final long serialVersionUID = -6583292297136670979L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "ISBN")
	private String isbn;
	
	@Column(name = "Title")
	private String title;
	
	@Column(name = "Summary")
	private String summary;
	
	@Column(name = "PublicationDate", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date publicationDate;
	
	@Column(name = "NumberOfPages")
	private int numberOfPages;
	
	@Column(name = "Language")
	private String language;
	
	@Column(name = "CoverImage", nullable = true)
	private String coverImage;
	
	@Column(name = "Price")
	private float price;
	
	@Column(name = "Status")
	private BookStatus status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@ManyToMany
	@JoinTable(name = "Book_Author", 
				joinColumns = @JoinColumn(name = "BookID"), 
				inverseJoinColumns = @JoinColumn(name = "AuthorID"))
	private List<Author> authors;
	
	@ManyToMany
	@JoinTable(name = "Book_Publisher", 
				joinColumns = @JoinColumn(name = "BookID"), 
				inverseJoinColumns = @JoinColumn(name = "PublisherID"))
	private List<Publisher> publishers;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public BookStatus getStatus() {
		return status;
	}
	public void setStatus(BookStatus status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public List<Publisher> getPublishers() {
		return publishers;
	}
	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", summary=" + summary + ", publicationDate="
				+ publicationDate + ", numberOfPages=" + numberOfPages + ", language=" + language + ", coverImage="
				+ coverImage + ", price=" + price + ", status=" + status + ", createDate=" + createDate + ", authors="
				+ authors + ", publishers=" + publishers + "]";
	}
	
}
