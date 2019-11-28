package Data;

public class Personels {

    //Attributes
    private String name;//
    private String lastName;//
    private String mobilePhoneNumber;//
    private String sex;//
    private String eMail;//
    private String birthDay;//
    private String nationalId;
    private String address;//
    private String startCooperationDate;//
    private double salary;//
    private String description;//
    private String position;//

    //Constructor
    public Personels(String name, String lastName, String mobilePhoneNumber, String sex, String eMail, String birthDay, String nationalId, String address, String startCooperationDate, double salary, String description, String position) {
        this.name = name;
        this.lastName = lastName;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.sex = sex;
        this.eMail = eMail;
        this.birthDay = birthDay;
        this.nationalId = nationalId;
        this.address = address;
        this.startCooperationDate = startCooperationDate;
        this.salary = salary;
        this.description = description;
        this.position = position;
    }

    //Default Constructor
    public Personels() {

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

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStartCooperationDate(String startCooperationDate) {
        this.startCooperationDate = startCooperationDate;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getBirthDay() {
        return birthDay;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getAddress() {
        return address;
    }

    public String getStartCooperationDate() {
        return startCooperationDate;
    }

    public double getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public String getPosition() {
        return position;
    }
}
