package Data;

public class Suppliers {

    //Attributes
    private int supplierId;
    private String name;
    private String lastName;
    private String mobilePhoneNumber;
    private String sex;
    private String eMail;
    private String fax;
    private String address;
    private String nationalId;
    private String description;

    //Constructor
    public Suppliers(String name, String lastName, String mobilePhoneNumber, String sex, String eMail, String fax, String address, String nationalId, String description) {
        this.name = name;
        this.lastName = lastName;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.sex = sex;
        this.eMail = eMail;
        this.fax = fax;
        this.address = address;
        this.nationalId = nationalId;
        this.description = description;
    }

    //Default Constructor
    public Suppliers() {

    }

    //Methods

    //Setters

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

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Getters

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public String geteMail() {
        return eMail;
    }

    public String getFax() {
        return fax;
    }

    public String getAddress() {
        return address;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getDescription() {
        return description;
    }
}
