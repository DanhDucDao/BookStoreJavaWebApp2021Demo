package controller.helper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LineItemJSON {
	@JsonProperty("lineItemId")
	public int lineItemId;
	
	@JsonProperty("bookId")
	public int bookId;
	
	@JsonProperty("title")
	public String title;
	
	@JsonProperty("quantity")
	public int quantity;
	
	@JsonProperty("price")
	public float price;
	
	@JsonProperty("discount")
	public float discount;
}
