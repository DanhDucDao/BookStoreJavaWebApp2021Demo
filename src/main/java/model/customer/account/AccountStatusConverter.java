package model.customer.account;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AccountStatusConverter implements AttributeConverter<AccountStatus, String> {
 
    @Override
    public String convertToDatabaseColumn(AccountStatus status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public AccountStatus convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        AccountStatus[] status = AccountStatus.values();
       for(int i = 0; i < status.length; i++) {
    	   if(value.equalsIgnoreCase(status[i].getValue()))
    		   return status[i];
       }
       return null;
    }
}