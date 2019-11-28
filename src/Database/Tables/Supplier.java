package Database.Tables;

public class Supplier extends Tables {


    private int supplier_id;
    private String first_name;
    private String last_name;
    private String sex;
    private String start_date;
    private String end_date;
    private String business_address;
    private String description;

    public Supplier(int supplier_id, String first_name, String last_name, String sex, String start_date, String end_date
            , String business_address, String description) {
        super("supplier", 7);

        setSupplierID(supplier_id);
        setFirstName(first_name);
        setLastName(last_name);
        setSex(sex);
        setStartDate(start_date);
        setEndDate(end_date);
        setBusinessAddress(business_address);
        setDescription(description);
    }

    private void setSupplierID(int supplier_id) {
        this.supplier_id = supplier_id;
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

    private void setStartDate(String start_date) {
        this.start_date = start_date;
    }

    private void setEndDate(String end_date) {
        this.end_date = end_date;
    }

    private void setBusinessAddress(String business_address) {
        this.business_address = business_address;
    }

    private void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String getFields() {
        String str = "supplier_id, first_name, last_name, sex, start_date, end_date, business_address, description";
        return str;
    }

    @Override
    public String getFieldsValues() {
        String str;
        str = supplier_id + ", " + "\"" + first_name + "\"" + ", " + "\"" + last_name + "\"" + ", \"" + this.sex + "\", " +
                "\"" + start_date + "\"" + ", " + "\"" + end_date + "\"" + ", " +
                "\"" + business_address + "\", " + "\"" + description + "\"";

        str = str.replaceAll("\"null\"", "NULL");
        return str;
    }
}
