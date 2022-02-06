package controller.helper;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CartJSON {
	@JsonProperty("createDate")
	public Date createDate;
	
	@JsonProperty("lineItems")
	public List<LineItemJSON> lineJsons;
	

	public static void main(String[] args) throws JsonProcessingException {
		CartJSON json = new CartJSON();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(json));
	}
}

