package Database.Tables;

public final class Customer extends Tables {


    private int customer_id;
    private String first_name;
    private String last_name;
    private String sex;
    private Integer count_of_orders;
    private String notes;


    public Customer(int customer_id, String first_name, String last_name,
                    String sex, int count_of_orders, String notes) {
        super("customer", 6);
        setCustomerID(customer_id);
        setFirstName(first_name);
        setLastName(last_name);
        setSex(sex);
        setCountOfOrders(count_of_orders);
        setNotes(notes);
    }


    private void setCustomerID(int customer_id) {
        this.customer_id = customer_id;
    }

    private void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    private void setLastName(String last_name) {
        this.last_name = last_name;
    }

    private void setSex(String sex) {
        this.sex = sex;
    }

    private void setCountOfOrders(int count_of_orders) {
        this.count_of_orders = count_of_orders;
    }

    private void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFieldsValues() {
        String str = "" + customer_id + ", \"" + first_name + "\"" + ", \"" + last_name + "\"" + ", \"" + sex + "\" ," +
                count_of_orders.intValue() + ", \'" + this.notes + "\'";

        str = str.replaceAll("\"null\"", "NULL");
        return str;
    }

    public String getFields() {
        String str = "customer_id , first_name, last_name, sex, count_of_orders, description";
        return str;
    }

}
