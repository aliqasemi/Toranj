package Database.Tables;

import com.sun.istack.internal.NotNull;

public class Personnel extends Tables {

    private int personnel_id;
    private String national_id;
    private String first_name;
    private String last_name;
    private String sex;
    private String address;
    private String date_of_birth;
    private double salary;
    private String start_date;
    private String position;
    private String description;

    public Personnel(int personnel_id, @NotNull String national_id, String first_name, String last_name, String sex,
                     String address, String date_of_birth, double salary, String start_date, String position, String description) {
        super("personnel", 10);

        setPersonnelID(personnel_id);
        setNationalID(national_id);
        setFirstName(first_name);
        setLastName(last_name);
        setSex(sex);
        setAddress(address);
        setDateOfBirth(date_of_birth);
        setSalary(salary);
        setStartDate(start_date);
        setPosition(position);
        setDescription(description);

    }

    private void setPersonnelID(int personnel_id) {
        this.personnel_id = personnel_id;
    }

    private void setNationalID(String national_id) {
        this.national_id = national_id;
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

    private void setAddress(String address) {
        this.address = address;
    }

    private void setDateOfBirth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    private void setSalary(double salary) {
        this.salary = salary;
    }

    private void setStartDate(String start_date) {
        this.start_date = start_date;
    }

    private void setPosition(String position) {
        this.position = position;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getFields() {
        String str = "personnel_id, national_id, first_name, last_name, sex, address, date_of_birth, salary, start_date, position, description";
        return str;
    }

    @Override
    public String getFieldsValues() {
        String str;
        str = personnel_id + ", " + "\"" + national_id + "\"" + ", " + "\"" + first_name + "\"" + ", " + "\"" +
                last_name + "\"" + ", \"" + this.sex + "\", " + "\"" + address + "\"" + ", " + "\"" + date_of_birth + "\" ,"
                + this.salary + ", \"" + start_date + "\", \"" + position + "\", \"" + description + "\"";

        str = str.replaceAll("\"null\"", "NULL");

        return str;
    }
}
