package model.order.status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
 
    @Override
    public String convertToDatabaseColumn(OrderStatus status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        OrderStatus[] status = OrderStatus.values();
       for(int i = 0; i < status.length; i++) {
    	   if(value.equalsIgnoreCase(status[i].getValue()))
    		   return status[i];
       }
       return null;
    }
}
