package model.customer.gender;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {
 
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getType();
    }

    @Override
    public Gender convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }

        Gender[] status = Gender.values();
       for(int i = 0; i < status.length; i++) {
    	   if(type.equalsIgnoreCase(status[i].getType()))
    		   return status[i];
       }
       return null;
    }
}