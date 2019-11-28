package Database.Tables;

public class CustomerEmailAddress extends Tables {

    private String email_address;
    private int customer_id;

    public CustomerEmailAddress(String email_address, int customer_id) {
        super("customer_email_address", 2);
        setEmailAddress(email_address);
        setCustomerID(customer_id);
    }

    private void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }

    private void setCustomerID(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFieldsValues() {
        String str = "\"" + email_address + "\"" + ", " + customer_id;
        return str;
    }

    public String getFields() {
        String str = "email_address, customer_customer_id";
        return str;
    }
}
