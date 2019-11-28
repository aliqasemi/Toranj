package Database.Tables;

import com.sun.istack.internal.NotNull;

public class CustomerPhoneNumber extends Tables {

    private String phone_number;
    private int customer_id;

    public CustomerPhoneNumber(@NotNull String phone_number, int customer_id) {
        super("customer_phone_number", 2);
        setPhoneNumber(phone_number);
        setCustomerID(customer_id);
    }

    private void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    private void setCustomerID(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFields() {
        String str = "phone_number, customer_customer_id";
        return str;
    }

    public String getFieldsValues() {
        String str = "\"" + phone_number + "\"" + ", " + customer_id;
        return str;
    }

}
