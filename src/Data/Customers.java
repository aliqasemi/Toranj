package Data;

public class Customers {
    //Attributes
    private String name;
    private String lastName;
    private String mobilePhoneNumber;
    private String sex;
    private String eMail;
    private String description;

    //Constructor
    public Customers(String name, String lastName, String mobilePhoneNumber, String sex, String eMail, String description) {
        this.name = name;
        this.lastName = lastName;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.sex = sex;
        this.eMail = eMail;
        this.description = description;
    }

    //Default constructor
    public Customers() {

    }

    //Methods

    //Setters


    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    //Getters


    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public String getDescription() {
        return description;
    }

}
