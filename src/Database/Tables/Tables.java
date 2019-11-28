package Database.Tables;

abstract public class Tables {

    /*customer("customer", 5), customer_email_address("customer_email_address", 2),
    customer_phone_number("customer_phone_number", 2), order("order", 10),
    order_has_product("order_has_product", 2), personnel("personnel", 7),
    personnel_email_address("personnel_email_address", 3), personnel_phone_number("personnel_phone_number", 3),
    product("product", 4), supplier("supplier", 7), supplier_email_address("supplier_email_address", 2),
    supplier_fax_number("supplier_fax_number", 2), supplier_phone_number("supplier_phone_number", 2);
*/

    private String value;
    private int field_count;

    public Tables(String value, int field_count) {
        setValue(value);
        setFieldCount(field_count);
    }

    private void setValue(String value) {
        this.value = value;
    }

    private void setFieldCount(int field_count) {
        this.field_count = field_count;
    }

    public String getValue() {
        return value;
    }

    public int getFieldCount() {
        return field_count;
    }


    abstract public String getFields();

    abstract public String getFieldsValues();
}
