package Database.Tables;

import Database.Exceptions.NotNullException;
import com.sun.istack.internal.NotNull;

public class PersonnelEmailAddress extends Tables {

    private String email_address;
    private int personnel_id;
    private String national_id;

    public PersonnelEmailAddress(@NotNull String email_address, int personnel_id, @NotNull String national_id) throws NotNullException {
        super("personnel_email_address", 3);
        setEmailAddress(email_address);
        setNationalID(national_id);
        setPersonnelID(personnel_id);
    }

    private void setEmailAddress(String email_address) throws NotNullException {
        if (email_address == null) {
            throw new NotNullException("This field can not be null.");
        }
        this.email_address = email_address;
    }

    private void setPersonnelID(int personnel_id) {
        this.personnel_id = personnel_id;
    }

    private void setNationalID(String national_id) {
        this.national_id = national_id;
    }

    @Override
    public String getFields() {
        String str = "email_address, personnel_personnel_id, personnel_national_id";
        return str;
    }

    @Override
    public String getFieldsValues() {
        String str;
        str = "\"" + email_address + "\"" + ", " + personnel_id + ", " + "\"" + national_id + "\"";

        return str;
    }
}
