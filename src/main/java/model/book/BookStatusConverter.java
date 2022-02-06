package model.book;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus, String> {
 
    @Override
    public String convertToDatabaseColumn(BookStatus status) {
        if (status == null) {
            return null;
        }
        return status.getName();
    }

    @Override
    public BookStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

       BookStatus[] status = BookStatus.values();
       for(int i = 0; i < status.length; i++) {
    	   if(code.equalsIgnoreCase(status[i].getName()))
    		   return status[i];
       }
       return null;
    }

}