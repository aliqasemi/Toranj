package Database.Tables;

import Database.Exceptions.NotNullException;
import com.sun.istack.internal.NotNull;

public class PersonnelPhoneNumber extends Tables {

    private String phone_number;
    private int personnel_id;
    private String national_id;

    public PersonnelPhoneNumber(@NotNull String phone_number, int personnel_id, @NotNull String national_id) throws NotNullException {
        super("personnel_phone_number", 3);
        setPhoneNumber(phone_number);
        setPersonnelID(personnel_id);
        setNationalID(national_id);
    }

    private void setPhoneNumber(String phone_number) throws NotNullException {
        if (phone_number == null) {
            throw new NotNullException("This field can not be null.");
        }
        this.phone_number = phone_number;
    }

    private void setPersonnelID(int personnel_id) {
        this.personnel_id = personnel_id;
    }

    private void setNationalID(String national_id) {
        this.national_id = national_id;
    }

    @Override
    public String getFields() {
        String str = "phone_number, personnel_personnel_id, personnel_national_id";
        return str;
    }

    @Override
    public String getFieldsValues() {
        String str;
        str = "\"" + phone_number + "\"" + ", " + personnel_id + ", " + "\"" + national_id + "\"";
        return str;
    }
}
