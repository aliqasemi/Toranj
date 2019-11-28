package Database.Tables;

import com.sun.istack.internal.NotNull;

public class SupplierPhoneNumber extends Tables {

    private String phone_number;
    private int supplier_id;

    public SupplierPhoneNumber(@NotNull String phone_number, int supplier_id) {
        super("supplier_phone_number", 2);
        setPhoneNumber(phone_number);
        setSupplierID(supplier_id);
    }

    private void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    private void setSupplierID(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    @Override
    public String getFields() {
        String str = "phone_number, supplier_supplier_id";
        return str;
    }

    @Override
    public String getFieldsValues() {
        String str;
        str = "\"" + phone_number + "\"" + ", " + supplier_id;
        return str;
    }
}
