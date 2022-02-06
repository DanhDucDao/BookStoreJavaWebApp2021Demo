package model.customer.member;



import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MemberLevelConverter implements AttributeConverter<MemberLevel, String> {
 
    @Override
    public String convertToDatabaseColumn(MemberLevel level) {
        if (level == null) {
            return null;
        }
        return level.getValue();
    }

    @Override
    public MemberLevel convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        MemberLevel[] status = MemberLevel.values();
       for(int i = 0; i < status.length; i++) {
    	   if(value.equalsIgnoreCase(status[i].getValue()))
    		   return status[i];
       }
       return null;
    }
}