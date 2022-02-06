package model.order.status;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {
 
    @Override
    public String convertToDatabaseColumn(PaymentStatus status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        PaymentStatus[] status = PaymentStatus.values();
       for(int i = 0; i < status.length; i++) {
    	   if(value.equalsIgnoreCase(status[i].getValue()))
    		   return status[i];
       }
       return null;
    }
}
