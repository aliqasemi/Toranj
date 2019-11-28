package Database.Tables;

import com.sun.istack.internal.NotNull;

public class SupplierEmailAddress extends Tables {

    private String email_address;
    private int supplier_id;

    public SupplierEmailAddress(@NotNull String email_address, int supplier_id) {
        super("supplier_email_address", 2);
        setEmailAddress(email_address);
        setSupplierID(supplier_id);

    }

    private void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }

    private void setSupplierID(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    @Override
    public String getFields() {
        String str = "email_address, supplier_supplier_id";
        return str;
    }

    @Override
    public String getFieldsValues() {
        String str;
        str = "\"" + email_address + "\"" + ", " + supplier_id;
        str = str.replaceAll("\"null\"", "NULL");
        return str;
    }
}
