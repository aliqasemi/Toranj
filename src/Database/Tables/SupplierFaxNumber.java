package Database.Tables;

import com.sun.istack.internal.NotNull;

public class SupplierFaxNumber extends Tables {

    private String fax_number;
    private int supplier_id;

    public SupplierFaxNumber(@NotNull String fax_number, int supplier_id) {
        super("supplier_fax_number", 2);
        setFaxNumber(fax_number);
        setSupplierID(supplier_id);
    }

    private void setFaxNumber(String fax_number) {
        this.fax_number = fax_number;
    }

    private void setSupplierID(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    @Override
    public String getFields() {
        String str = "fax_number, supplier_supplier_id";
        return str;
    }

    @Override
    public String getFieldsValues() {
        String str;
        str = "\"" + fax_number + "\"" + ", " + supplier_id;
        return str;
    }
}
