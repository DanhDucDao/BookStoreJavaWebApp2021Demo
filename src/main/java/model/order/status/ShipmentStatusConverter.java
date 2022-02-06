package model.order.status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ShipmentStatusConverter implements AttributeConverter<ShipmentStatus, String> {
 
    @Override
    public String convertToDatabaseColumn(ShipmentStatus status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public ShipmentStatus convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
       ShipmentStatus[] status = ShipmentStatus.values();
       for(int i = 0; i < status.length; i++) {
    	   if(value.equalsIgnoreCase(status[i].getValue()))
    		   return status[i];
       }
       return null;
    }
}
