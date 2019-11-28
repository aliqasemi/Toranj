package Data;

import Database.Database;
import Database.Exceptions.*;
import Database.Tables.*;
import Exeptions.InvalidInputException;
import Exeptions.InvalidInputUsernamePasswordExeption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatException;

public class Users {
    private String username;
    private String password;
    private int level;
    private int personnel_id;
    private String note;

    public Users(String username, String password, int level, int personnel_id, String note) {
        this.username = username;
        this.password = password;
        this.level = level;
        this.personnel_id = personnel_id;
        this.note = note;
    }

    public String getUsername() {
        return this.username;
    }

    public String getNote() {
        return note;
    }

    public int getLevel() {
        return this.level;
    }

    public String getPassword() {
        return password;
    }

    public int getPersonnel_id() {
        return personnel_id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean changePassword(String username, String new_password, Database database) {
        if (this.username.equals(username) || this.level == 0) {
            try {
                database.updateUser(username, new_password);
                return true;
            } catch (DatabaseSyntaxError | SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    private String make_counter(int counter, String story) {
        String st;
        if (counter < 10) {
            st = story + "00" + counter;
        } else if (counter < 100 && counter > 9) {
            st = story + "0" + counter;
        } else if (counter > 99) {
            st = story + counter;
        } else {
            st = null;
        }
        return st;
    }

    private int make_id_customer(Database database) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String a = "1" + String.format(dateFormat.format(date).substring(1, 4)) + String.format(dateFormat.format(date).substring(5, 7));
        String x[][] = database.retrieve(Database.CUSTOMER, "", "", "");
        int counter = Integer.parseInt(x[x.length - 1][0].substring(6)) + 1;
        return Integer.parseInt(make_counter(counter, a));
    }

    private boolean isValid(char c) {
        int code = c;
        int a[] = {('A'), ('Z'), ('a'), ('z'), ('آ'), ('ی'), (' ')};
        if ((code >= a[0] && code <= a[1]) || (code >= a[2] && code <= a[3]) || (code >= a[4] && code <= a[5]) || code == a[6]) {
            return true;
        } else
            return false;
    }

    private boolean isValidInputName(String inputName) {
        boolean b = true;
        int count = 0;
        while (b && (count != inputName.length())) {
            b = isValid(inputName.charAt(count));
            count++;
        }
        return b;
    }

    //costomer id combined by '1' + yearNumber + monthNumber + dateNumber + clockTime
    public boolean addCustomer(Customers customer, Database database) throws InvalidInputException, Exception {
        // int customer_id = make_id_customer();
        int customer_id = make_id_customer(database);
        String first_name;
        String last_name;
        String sex;
        String email_adress;
        String phoneNumber;
        String description;
        int count_of_order = 1;
        if (isValidInputName(customer.getName())) {
            first_name = customer.getName();
        } else {
            throw new InvalidInputException("first_name");
        }
        if (isValidInputName(customer.getLastName())) {
            last_name = customer.getLastName();
        } else {
            throw new InvalidInputException("last_name");
        }
        if (customer.getSex().equals("male") || customer.getSex().equals("female")) {
            sex = customer.getSex();
        } else {
            throw new InvalidInputException("sex");
        }
        try {
            description = customer.getDescription();
        } catch (Exception ex) {
            throw new InvalidInputException("description");
        }
        if ((customer.getMobilePhoneNumber().length() == 11 && customer.getMobilePhoneNumber().startsWith("0"))) {
            phoneNumber = customer.getMobilePhoneNumber();
        }
        else
            throw new InvalidInputException("phoneNumber");
        if ((customer.geteMail().endsWith(".com") && customer.geteMail().contains("@")) || customer.geteMail().equals("")) {
            if (customer.geteMail().equals("")){
                email_adress = customer_id + "no have Email";
            }
            else
            email_adress = customer.geteMail();
        }
        else
            throw new InvalidInputException("Email");
        try {
            Customer cust = new Customer(customer_id, first_name, last_name, sex, count_of_order, description);
            CustomerPhoneNumber customerPhoneNumber = new CustomerPhoneNumber(phoneNumber, customer_id);
            CustomerEmailAddress customerEmailAddress = new CustomerEmailAddress(email_adress, customer_id);
            database.add(cust);
            database.add(customerEmailAddress);
            database.add(customerPhoneNumber);
            return true;
        } catch (DatabaseConnectionException | NotNullException | DuplicatedEntryException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int make_id_personel(Database database) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String a = "2" + String.format(dateFormat.format(date).substring(1, 4)) + String.format(dateFormat.format(date).substring(5, 7));
        String x[][] = database.retrieve(Database.PERSONNEL, "", "", "");
        int counter = Integer.parseInt(x[x.length - 1][0].substring(6)) + 1;
        return Integer.parseInt(make_counter(counter, a));
    }

    private boolean isValidDate(String date) throws ArrayIndexOutOfBoundsException {

        if (date.matches("[0-9][0-9][0-9][0-9]/[0-9][0-9]/[0-9][0-9]")) {
            return true;
        }

        return false;
    }

    public boolean addPersonel(Personels personel, Database database) throws InvalidInputException, Exception {
        // int customer_id = make_id_customer();
        int personel_id = make_id_personel(database);
        String first_name;
        String last_name;
        String sex;
        String email_adress;
        String phoneNumber;
        String description;
        String birthDay = "";
        String startCooperationDate = "";
        String address = "";
        double salary = 0;
        String position = "";
        String nationalId = "";
        int count_of_order = 1;
        if (isValidInputName(personel.getName())) {
            first_name = personel.getName();
        } else {
            throw new InvalidInputException("first_name");
        }
        if (personel.getNationalId().length() == 10) {
            nationalId = personel.getNationalId();
        } else {
            throw new InvalidInputException("nationalId");
        }
        if (isValidInputName(personel.getLastName())) {
            last_name = personel.getLastName();
        } else {
            throw new InvalidInputException("last_name");
        }
        if (personel.getSex().equals("male") || personel.getSex().equals("female")) {
            sex = personel.getSex();
        } else {
            throw new InvalidInputException("sex");
        }
        try {
            description = personel.getDescription();
        } catch (Exception ex) {
            throw new InvalidInputException("description");
        }
        if ((personel.getMobilePhoneNumber().length() == 11 && personel.getMobilePhoneNumber().startsWith("0")))
            phoneNumber = personel.getMobilePhoneNumber();
        else
            throw new InvalidInputException("phoneNumber");
        if ((personel.geteMail().endsWith(".com") && personel.geteMail().contains("@")) || personel.geteMail().equals("")) {
            if (personel.geteMail().equals("")){
                email_adress = personel_id + "no have Eamil";
            }
            else
            email_adress = personel.geteMail();
        }
        else
            throw new InvalidInputException("Email");
        try {
            if (isValidDate(personel.getBirthDay())) {
                birthDay = personel.getBirthDay();
            } else {
                throw new InvalidInputException("date");
            }
        } catch (Exception ex) {
            ex.printStackTrace();///////////////////////////////////////////////////////
        }
        try {
            if (isValidDate(personel.getStartCooperationDate())) {
                startCooperationDate = personel.getStartCooperationDate();
            } else {
                throw new InvalidInputException("coparationdate");
            }
        } catch (Exception ex) {
            ex.printStackTrace();///////////////////////////////////////////////////////
        }
        try {
            address = personel.getAddress();
        } catch (Exception ex) {
            ex.printStackTrace();///////////////////////////////////////////////////////
        }
        try {
            salary = personel.getSalary();
        } catch (Exception ex) {
            ex.printStackTrace();//////////////////////////////////////////////////////
        }
        try {
            position = personel.getPosition();
        } catch (Exception ex) {
            ex.printStackTrace();//////////////////////////////////////////////////////
        }
        try {
            Personnel pers = new Personnel(personel_id, nationalId, first_name, last_name, sex, address, birthDay, salary, startCooperationDate, position, description);
            PersonnelEmailAddress personnelEmailAddress = new PersonnelEmailAddress(email_adress, personel_id, nationalId);
            PersonnelPhoneNumber personnelPhoneNumber = new PersonnelPhoneNumber(phoneNumber, personel_id, nationalId);
            database.add(pers);
            database.add(personnelEmailAddress);
            database.add(personnelPhoneNumber);
            return true;
        } catch (DatabaseConnectionException | NotNullException | DuplicatedEntryException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int make_id_supplier(Database database) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String a = "3" + String.format(dateFormat.format(date).substring(1, 4)) + String.format(dateFormat.format(date).substring(5, 7));
        String x[][] = database.retrieve(Database.SUPPLIER, "", "", "");
        int counter = Integer.parseInt(x[x.length - 1][0].substring(6)) + 1;
        return Integer.parseInt(make_counter(counter, a));
    }

    public boolean addSupplier(Suppliers supplier, Database database) throws InvalidInputException, Exception {
        int supplier_id = make_id_supplier(database);
        String first_name;
        String last_name;
        String phoneNumber;
        String sex;
        String email_adress;
        String fax = "";
        String address = "";
        String nationalId;
        String description;
        if (isValidInputName(supplier.getName())) {
            first_name = supplier.getName();
        } else {
            throw new InvalidInputException("first_name");
        }
        if (isValidInputName(supplier.getLastName())) {
            last_name = supplier.getLastName();
        } else {
            throw new InvalidInputException("last_name");
        }
        if (supplier.getSex().equals("male") || supplier.getSex().equals("female")) {
            sex = supplier.getSex();
        } else {
            throw new InvalidInputException("sex");
        }
        try {
            description = supplier.getDescription();
        } catch (Exception ex) {
            throw new InvalidInputException("description");
        }
        if (supplier.getMobilePhoneNumber().length() == 11 && supplier.getMobilePhoneNumber().startsWith("0"))
            phoneNumber = supplier.getMobilePhoneNumber();
        else
            throw new InvalidInputException("phoneNumber");
        if (supplier.geteMail().endsWith(".com") && supplier.geteMail().contains("@") || supplier.geteMail().equals("")) {
            if (supplier.geteMail().equals("")) {
                email_adress = supplier_id + "no have Email";
            }
            else
                email_adress = supplier.geteMail();
        }
        else
            throw new InvalidInputException("Email");
        try {
            address = supplier.getAddress();
        } catch (Exception ex) {
            ex.printStackTrace();///////////////////////////////////////////////////////
        }
        try {
            if (supplier.getNationalId().length() == 10) {
                nationalId = supplier.getNationalId();
            } else {
                throw new InvalidInputException("nationalId");
            }
        } catch (Exception ex) {
            ex.printStackTrace();////////////////////////////////////////////////////
        }
        try {
            if (supplier.getFax().equals(""))
                fax = supplier_id + "no have fax";
            fax = supplier.getFax();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Supplier supl = new Supplier(supplier_id, first_name, last_name, sex, "2018_08_13", "2018_08_14", address, description);
            SupplierEmailAddress supplierEmailAddresse = new SupplierEmailAddress(email_adress, supplier_id);
            SupplierPhoneNumber supplierPhoneNumber = new SupplierPhoneNumber(phoneNumber, supplier_id);
            SupplierFaxNumber supplierFaxNumber = new SupplierFaxNumber(fax, supplier_id);
            database.add(supl);
            database.add(supplierEmailAddresse);
            database.add(supplierFaxNumber);
            database.add(supplierPhoneNumber);
            return true;
        } catch (DatabaseConnectionException | NotNullException | DuplicatedEntryException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int make_id_product(Database database) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String a = "4" + String.format(dateFormat.format(date).substring(1, 4)) + String.format(dateFormat.format(date).substring(5, 7));
        String x[][] = database.retrieve(Database.PRODUCT, "", "", "");
        int counter = Integer.parseInt(x[x.length - 1][0].substring(6)) + 1;
        return Integer.parseInt(make_counter(counter, a));
    }

    public boolean addProduct(Productes productes, Database database) throws InvalidInputException, Exception {
        int productId = make_id_product(database);
        ;
        String name;
        double price = 0;
        int discountPercent = 0;
        int count = 0;
        String description;
        if (isValidInputName(productes.getName())) {
            String condition = "Where name = '" + productes.getName() + "'";
            String[][] temp = database.retrieve(Database.PRODUCT, condition, null, null);
            if (temp.length != 1) {
                return false;
            }
            name = productes.getName();
        } else {
            throw new InvalidInputException("first_name");
        }
        try {
            description = productes.getDescription();
        } catch (Exception ex) {
            throw new InvalidInputException("description");
        }
        try {
            price = productes.getPrice();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (productes.getDiscountPercent() < 100 && productes.getDiscountPercent() > 0) {
                discountPercent = productes.getDiscountPercent();
            } else {
                throw new InvalidInputException("price");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            count = productes.getCount();
        } catch (Exception ex) {
            ex.printStackTrace();
            ;
        }

        try {
            Product prod = new Product(productId, name, price, discountPercent, count, description);
            database.add(prod);
            return true;
        } catch (DatabaseConnectionException | NotNullException | DuplicatedEntryException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int make_id_order_sales(Database database) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String a = "5" + String.format(dateFormat.format(date).substring(1, 4)) + String.format(dateFormat.format(date).substring(5, 7));
        String x[][] = database.retrieve(Database.ORDERS, "Where order_id < 601801000", "", "");
        int counter = Integer.parseInt(x[x.length - 1][0].substring(6)) + 1;
        return Integer.parseInt(make_counter(counter, a));
    }

    private int find_customer_id(Database database, String name, String lastname) {
        String condition = "where first_name ='" + name + "' and last_name ='" + lastname + "'";
        try {
            String[][] customer = database.retrieve(Database.CUSTOMER, condition, null, null);
            return Integer.parseInt(customer[1][0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public boolean add_sales(Sales sales, Database database) throws Exception {
        int sales_id = make_id_order_sales(database);
        String customerName;
        String customerLastname;
        String productName;
        int customer_id = 0;
        String orderDate;
        String deliveryDate;
        int count;
        double totalAmonnt;
        double prepaidFund = 0;
        String description;
        customerName = sales.getCustomerName();
        customerLastname = sales.getCustomerLastname();
        customer_id = find_customer_id(database, customerName, customerLastname);
        productName = sales.getProductName();
        String condition = "Where name = '" + sales.getProductName() + "'";
        String[][] product = database.retrieve(Database.PRODUCT, condition, "", "");
        Productes productes = new Productes(product[1][1], Integer.parseInt(product[1][2]), Integer.parseInt(product[1][3]), Integer.parseInt(product[1][4]), product[1][5]);
        if (sales.getCount() <= productes.getCount()) {
            int newContOfProduct = productes.getCount() - sales.getCount() ;
            count = sales.getCount();
            totalAmonnt = count * productes.getTotalAmont();
            sales.setTotalAmount(totalAmonnt);
            String set = "count_of_product = " + newContOfProduct ;
            String con = "WHERE product_id =" + product[1][0] ;
            database.update(Database.PRODUCT , set ,con);
        } else
            throw new InvalidInputException("out of product count");
        try {
            prepaidFund = sales.getPrepaidFund();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isValidDate(sales.getOrderDate())) {
            orderDate = sales.getOrderDate();
        } else {
            throw new InvalidInputException("date-Order-date");
        }
        if (isValidDate(sales.getDeliveryDate())) {
            deliveryDate = sales.getDeliveryDate();
        } else {
            throw new InvalidInputException("date-delivery-date");
        }
        description = sales.getDescription();
        try {
            Order sale1 = new Order(sales_id, prepaidFund, totalAmonnt, orderDate, deliveryDate, count, customer_id, this.personnel_id, 301801000, description);
            OrderHasProduct sale2 = new OrderHasProduct(sales_id, Integer.parseInt(product[1][0]));
           database.add(sale1);
            database.add(sale2);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    private int make_id_order_puchase(Database database) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String a = "6" + String.format(dateFormat.format(date).substring(1, 4)) + String.format(dateFormat.format(date).substring(5, 7));
        String x[][] = database.retrieve(Database.ORDERS, "", "", "");
        int counter = Integer.parseInt(x[x.length - 1][0].substring(6)) + 1;
        return Integer.parseInt(make_counter(counter, a));
    }

    private int find_supplier_id(Database database, String name, String lastname) {
        String condition = "where first_name ='" + name + "' and last_name ='" + lastname + "'";
        try {
            String[][] customer = database.retrieve(Database.SUPPLIER, condition, null, null);
            return Integer.parseInt(customer[1][0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean add_Purchase(Purchase purchase, Database database) throws Exception {
        int puchase_id = make_id_order_puchase(database);
        String supplierName;
        String supplierLastname;
        String productName;
        int supplier_id = 0;
        String orderDate;
        String deliveryDate;
        int count;
        double totalAmonnt;
        double prepaidFund = 0;
        String description;
        supplierName = purchase.getSupplierName();
        supplierLastname = purchase.getSupplieLastName();
        supplier_id = find_supplier_id(database, supplierName, supplierLastname);
        productName = purchase.getProductName();
        String condition = "Where name = '" + purchase.getProductName() + "'";
        String[][] product = database.retrieve(Database.PRODUCT, condition, "", "");
        Productes productes = new Productes(product[1][1], Integer.parseInt(product[1][2]), Integer.parseInt(product[1][3]), Integer.parseInt(product[1][4]), product[1][5]);
        int newContOfProduct = productes.getCount() + purchase.getCount();
        count = purchase.getCount();
        totalAmonnt = count * productes.getTotalAmont();
        purchase.setTotalAmount(totalAmonnt);
        String set = "count_of_product = " + newContOfProduct;
        String con = "WHERE product_id =" + product[1][0];
        database.update(Database.PRODUCT, set, con);
        try {
            prepaidFund = purchase.getPrepaidFund();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isValidDate(purchase.getOrderDate())) {
            orderDate = purchase.getOrderDate();
        } else {
            throw new InvalidInputException("date-Order-date");
        }
        if (isValidDate(purchase.getDeliveryDate())) {
            deliveryDate = purchase.getDeliveryDate();
        } else {
            throw new InvalidInputException("date-delivery-date");
        }
        description = purchase.getDescription();
        try {
            Order sale1 = new Order(puchase_id, prepaidFund, totalAmonnt, orderDate, deliveryDate, count, 101801000, this.personnel_id, supplier_id, description);
            OrderHasProduct sale2 = new OrderHasProduct(puchase_id, Integer.parseInt(product[1][0]));
            database.add(sale1);
            database.add(sale2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCustomer(Customers customers, String lastLastName, Database database) {
        String condition = "WHERE last_name = '" + lastLastName + "'";
        try {
            int customerId = Integer.parseInt(database.retrieve(Database.CUSTOMER, condition, "", "")[1][0]);
            String condition1 = "WHERE customer_customer_id = '" + customerId + "'";
            condition = "WHERE customer_id = '" + customerId + "'";

            if (!customers.getName().equals(database.retrieve(Database.CUSTOMER, condition, "", "")[1][1])) {
                if (isValidInputName(customers.getName())) {
                    String set = "first_name = '" + customers.getName() + "'";
                    database.update(Database.CUSTOMER, set, condition);
                    return true;
                } else {
                    return false;
                }
            }
            if (!customers.getLastName().equals(database.retrieve(Database.CUSTOMER, condition, "", "")[1][2])) {
                if (isValidInputName(customers.getLastName())) {
                    String set = "last_name = '" + customers.getLastName() + "'";
                    database.update(Database.CUSTOMER, set, condition);
                    return true;
                } else {
                    return false;
                }
            }
            if (!customers.getSex().equals(database.retrieve(Database.CUSTOMER, condition, "", "")[1][3])) {
                if (customers.getSex().equals("male") || customers.getSex().equals("female")) {
                    String set = "sex = '" + customers.getSex() + "'";
                    database.update(Database.CUSTOMER, set, condition);
                    return true;
                } else {
                    return false;
                }
            }
            if (!customers.getDescription().equals(database.retrieve(Database.CUSTOMER, condition, "", "")[1][5])) {
                String set = "description = '" + customers.getDescription() + "'";
                database.update(Database.CUSTOMER, set, condition);
                return true;
            }
            if (!customers.geteMail().equals(database.retrieve(Database.CUSTOMER_EMAIL_ADDRESS, condition1, "", "")[1][0])) {
                if (customers.geteMail().endsWith(".com") && customers.geteMail().contains("@")) {
                    String set = "email_address = '" + customers.geteMail() + "'";
                    database.update(Database.CUSTOMER_EMAIL_ADDRESS, set, condition1);
                    return true;
                } else {
                    return false;
                }
            }
            if (!customers.getMobilePhoneNumber().equals(database.retrieve(Database.CUSTOMER_PHONE_NUMBER, condition1, "", "")[1][0])) {
                if (customers.getMobilePhoneNumber().length() == 11 && customers.getMobilePhoneNumber().startsWith("09")) {
                    String set = "phone_number = '" + customers.getMobilePhoneNumber() + "'";
                    database.update(Database.CUSTOMER_PHONE_NUMBER, set, condition1);
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException | DatabaseSyntaxError ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updatePersonel(Personels personels, String lastLastName, Database database) {
        String condition = "WHERE last_name = '" + lastLastName + "'";
        try {
            int personelId = Integer.parseInt(database.retrieve(Database.PERSONNEL, condition, "", "")[1][0]);
            condition = "WHERE personnel_id = '" + personelId + "'";
            String condition1 = "WHERE personnel_personnel_id = '" + personelId + "'";

            if (!personels.getNationalId().equals(database.retrieve(Database.PERSONNEL, condition, "", "")[1][1])) {
                if (isValidInputName(personels.getName())) {
                    String set = "first_name = '" + personels.getName() + "'";
                    database.update(Database.PERSONNEL, set, condition);
                } else {
                    return false;
                }
            }
            if (!personels.getName().equals(database.retrieve(Database.PERSONNEL, condition, "", "")[1][2])) {
                if (isValidInputName(personels.getName())) {
                    String set = "first_name = '" + personels.getName() + "'";
                    database.update(Database.PERSONNEL, set, condition);
                } else {
                    return false;
                }
            }
            if (!personels.getLastName().equals(database.retrieve(Database.PERSONNEL, condition, "", "")[1][3])) {
                if (isValidInputName(personels.getName())) {
                    String set = "last_name = '" + personels.getLastName() + "'";
                    database.update(Database.PERSONNEL, set, condition);
                } else {
                    return false;
                }
            }
            if (!personels.getSex().equals(database.retrieve(Database.PERSONNEL, condition, "", "")[1][4])) {
                if (personels.getSex().equals("male") || personels.getSex().equals("female")) {
                    String set = "sex = '" + personels.getSex() + "'";
                    database.update(Database.PERSONNEL, set, condition);
                } else {
                    return false;
                }
            }
            if (!personels.getAddress().equals(database.retrieve(Database.PERSONNEL, condition, "", "")[1][5])) {
                String set = "address = '" + personels.getAddress() + "'";
                database.update(Database.PERSONNEL, set, condition);
            }
            if (!personels.getBirthDay().equals(database.retrieve(Database.PERSONNEL, condition, "", "")[1][6])) {
                if (isValidDate(personels.getBirthDay())) {
                    String set = "date_of_birth = '" + personels.getBirthDay() + "'";
                    database.update(Database.PERSONNEL, set, condition);
                } else {
                    return false;
                }
            }
            if (personels.getSalary() != (Double.parseDouble(database.retrieve(Database.PERSONNEL, condition, "", "")[1][7]))) {
                try {
                    String set = "salary = '" + personels.getSalary() + "'";
                    database.update(Database.PERSONNEL, set, condition);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
            if (!personels.getStartCooperationDate().equals(database.retrieve(Database.PERSONNEL, condition, "", "")[1][8])) {
                if (isValidDate(personels.getStartCooperationDate())) {
                    String set = "start_date = '" + personels.getStartCooperationDate() + "'";
                    database.update(Database.PERSONNEL, set, condition);
                } else {
                    return false;
                }
            }
            if (!personels.getPosition().equals(database.retrieve(Database.PERSONNEL, condition, "", "")[1][9])) {
                String set = "position = '" + personels.getPosition() + "'";
                database.update(Database.PERSONNEL, set, condition);
            }
            if (!personels.getDescription().equals(database.retrieve(Database.PERSONNEL, condition, "", "")[1][10])) {
                String set = "description = '" + personels.getDescription() + "'";
                database.update(Database.PERSONNEL, set, condition);
            }
            if (!personels.geteMail().equals(database.retrieve(Database.PERSONNEL_EMAIL_ADDRESS, condition1, "", "")[1][0])) {
                if (personels.geteMail().endsWith(".com") && personels.geteMail().contains("@")) {
                    String set = "email_address = '" + personels.geteMail() + "'";
                    database.update(Database.PERSONNEL_EMAIL_ADDRESS, set, condition1);
                } else {
                    return false;
                }
            }
            if (!personels.getMobilePhoneNumber().equals(database.retrieve(Database.PERSONNEL_PHONE_NUMBER, condition1, "", "")[1][0])) {
                if (personels.getMobilePhoneNumber().length() == 11 && personels.getMobilePhoneNumber().startsWith("09")) {
                    String set = "phone_number = '" + personels.getMobilePhoneNumber() + "'";
                    database.update(Database.PERSONNEL_PHONE_NUMBER, set, condition1);
                } else {
                    return false;
                }
            }
            return true;

        } catch (SQLException | DatabaseSyntaxError ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateSupplliers(Suppliers suppliers, String lastLastName, Database database) {
        String condition = "WHERE last_name = '" + lastLastName + "'";
        try {
            int suppliersId = Integer.parseInt(database.retrieve(Database.SUPPLIER, condition, "", "")[1][0]);
            condition = "WHERE supplier_id = '" + suppliersId + "'";
            String condition1 = "WHERE supplier_supplier_id = '" + suppliersId + "'";
            if (!suppliers.getNationalId().equals(database.retrieve(Database.SUPPLIER, condition, "", "")[1][1])) {
                if (isValidInputName(suppliers.getName())) {
                    String set = "first_name = '" + suppliers.getName() + "'";
                    database.update(Database.SUPPLIER, set, condition);
                } else {
                    return false;
                }
            }
            if (!suppliers.getLastName().equals(database.retrieve(Database.SUPPLIER, condition, "", "")[1][2])) {
                if (isValidInputName(suppliers.getName())) {
                    String set = "last_name = '" + suppliers.getLastName() + "'";
                    database.update(Database.SUPPLIER, set, condition);
                } else {
                    return false;
                }
            }
            if (!suppliers.getSex().equals(database.retrieve(Database.SUPPLIER, condition, "", "")[1][3])) {
                if (suppliers.getSex().equals("male") || suppliers.getSex().equals("female")) {
                    String set = "sex = '" + suppliers.getSex() + "'";
                    database.update(Database.SUPPLIER, set, condition);
                } else {
                    return false;
                }
            }
            if (!suppliers.getAddress().equals(database.retrieve(Database.SUPPLIER, condition, "", "")[1][6])) {
                String set = "address = '" + suppliers.getAddress() + "'";
                database.update(Database.SUPPLIER, set, condition);
            }

            if (!suppliers.getDescription().equals(database.retrieve(Database.SUPPLIER, condition, "", "")[1][7])) {
                String set = "description = '" + suppliers.getDescription() + "'";
                database.update(Database.SUPPLIER, set, condition);
            }
            if (!suppliers.geteMail().equals(database.retrieve(Database.SUPPLIER_EMAIL_ADDRESS, condition1, "", "")[1][0])) {
                if (suppliers.geteMail().endsWith(".com") && suppliers.geteMail().contains("@")) {
                    String set = "email_address = '" + suppliers.geteMail() + "'";
                    database.update(Database.SUPPLIER_EMAIL_ADDRESS, set, condition1);
                } else {
                    return false;
                }
            }
            if (!suppliers.getMobilePhoneNumber().equals(database.retrieve(Database.SUPPLIER_PHONE_NUMBER, condition1, "", "")[1][0])) {
                if (suppliers.getMobilePhoneNumber().length() == 11 && suppliers.getMobilePhoneNumber().startsWith("09")) {
                    String set = "phone_number = '" + suppliers.getMobilePhoneNumber() + "'";
                    database.update(Database.SUPPLIER_PHONE_NUMBER, set, condition1);
                } else {
                    return false;
                }
            }
            if (!suppliers.getFax().equals(database.retrieve(Database.SUPPLIER_FAX_NUMBER, condition1, "", "")[1][0])) {

                String set = "fax_number = '" + suppliers.getFax() + "'";
                database.update(Database.SUPPLIER_FAX_NUMBER, set, condition1);
            }
            return true;
        } catch (SQLException | DatabaseSyntaxError ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateProduct(Productes product, String name, Database database) {
        String condition = "WHERE name = '" + name + "'";
        try {
            int productId = Integer.parseInt(database.retrieve(Database.PRODUCT, condition, "", "")[1][0]);
            condition = "WHERE product_id = '" + productId + "'";
            if (!product.getName().equals(database.retrieve(Database.PRODUCT, condition, "", "")[1][1])) {
                String set = "name = '" + product.getName() + "'";
                database.update(Database.PRODUCT, set, condition);
            }
            if (product.getPrice() != (Double.parseDouble(database.retrieve(Database.PRODUCT, condition, "", "")[1][2]))) {
                String set = "price = '" + product.getPrice() + "'";
                database.update(Database.PRODUCT, set, condition);
            }
            if (product.getDiscountPercent() != (Double.parseDouble(database.retrieve(Database.PRODUCT, condition, "", "")[1][3]))) {
                String set = "discount_percent = '" + product.getDiscountPercent() + "'";
                database.update(Database.PRODUCT, set, condition);
            }

            if (product.getDiscountPercent() != (Double.parseDouble(database.retrieve(Database.PRODUCT, condition, "", "")[1][4]))) {
                String set = "count_of_product = '" + product.getCount() + "'";
                database.update(Database.PRODUCT, set, condition);
            }
            if (!product.getDescription().equals(database.retrieve(Database.PRODUCT, condition, "", "")[1][5])) {
                String set = "description = '" + product.getDescription() + "'";
                database.update(Database.PRODUCT, set, condition);
            }
            return true;
        } catch (SQLException | DatabaseSyntaxError ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateSales(Sales sale, String customerLastname, Database database) {
        try {
            String condition = "WHERE last_name = '" + customerLastname + "'";
            int customerId = Integer.parseInt(database.retrieve(Database.CUSTOMER, condition, "", "")[1][0]);
            condition = "WHERE customer_customer_id = '" + customerId + "'";
            int orderId = Integer.parseInt(database.retrieve(Database.ORDERS, condition, "", "")[1][0]);
            condition = "WHERE order_id = '" + orderId + "'";
            if (sale.getPrepaidFund() != (Double.parseDouble(database.retrieve(Database.ORDERS, condition, "", "")[1][1]))) {
                String set = "prepaid_fund = '" + sale.getPrepaidFund() + "'";
                database.update(Database.ORDERS, set, condition);
            }
            if (sale.getTotalAmount() != (Integer.parseInt(database.retrieve(Database.ORDERS, condition, "", "")[1][2]))) {
                String set = "total_amount = '" + sale.getTotalAmount() + "'";
                database.update(Database.ORDERS, set, condition);
            }
            if (!sale.getOrderDate().equals(database.retrieve(Database.ORDERS, condition, "", "")[1][3])) {
                String set = "order_date = '" + sale.getOrderDate() + "'";
                database.update(Database.ORDERS, set, condition);
            }
            if (!sale.getDeliveryDate().equals(database.retrieve(Database.ORDERS, condition, "", "")[1][4])) {
                String set = "delivery_date = '" + sale.getDeliveryDate() + "'";
                database.update(Database.ORDERS, set, condition);
            }
            if (sale.getCount() != (Integer.parseInt(database.retrieve(Database.ORDERS, condition, "", "")[1][5]))) {
                String set = "count_of_product = '" + sale.getCount() + "'";
                database.update(Database.ORDERS, set, condition);
            }
            if (!sale.getDescription().equals(database.retrieve(Database.ORDERS, condition, "", "")[1][9])) {
                String set = "description = '" + sale.getDescription() + "'";
                database.update(Database.ORDERS, set, condition);
            }
            return true;
        } catch (SQLException | DatabaseSyntaxError ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updatePurchase(Purchase Purchase, String supplierLastname, Database database) {
        try {
            String condition = "WHERE last_name = '" + supplierLastname + "'";
            int supplierId = Integer.parseInt(database.retrieve(Database.SUPPLIER, condition, "", "")[1][0]);
            condition = "WHERE supplier_supplier_id = '" + supplierId + "'";
            int orderId = Integer.parseInt(database.retrieve(Database.ORDERS, condition, "", "")[1][0]);
            condition = "WHERE order_id = '" + orderId + "'";
            if (Purchase.getPrepaidFund() != (Double.parseDouble(database.retrieve(Database.ORDERS, condition, "", "")[1][1]))) {
                String set = "prepaid_fund = '" + Purchase.getPrepaidFund() + "'";
                database.update(Database.ORDERS, set, condition);
            }
            if (Purchase.getTotalAmount() != (Integer.parseInt(database.retrieve(Database.ORDERS, condition, "", "")[1][2]))) {
                String set = "total_amount = '" + Purchase.getTotalAmount() + "'";
                database.update(Database.ORDERS, set, condition);
            }
            if (!Purchase.getOrderDate().equals(database.retrieve(Database.ORDERS, condition, "", "")[1][3])) {
                String set = "order_date = '" + Purchase.getOrderDate() + "'";
                database.update(Database.ORDERS, set, condition);
            }
            if (!Purchase.getDeliveryDate().equals(database.retrieve(Database.ORDERS, condition, "", "")[1][4])) {
                String set = "delivery_date = '" + Purchase.getDeliveryDate() + "'";
                database.update(Database.ORDERS, set, condition);
            }
            if (Purchase.getCount() != (Integer.parseInt(database.retrieve(Database.ORDERS, condition, "", "")[1][5]))) {
                String set = "count_of_product = '" + Purchase.getCount() + "'";
                database.update(Database.ORDERS, set, condition);
            }
            if (!Purchase.getDescription().equals(database.retrieve(Database.ORDERS, condition, "", "")[1][9])) {
                String set = "description = '" + Purchase.getDescription() + "'";
                database.update(Database.ORDERS, set, condition);
            }
            return true;
        } catch (SQLException | DatabaseSyntaxError ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteCustomer(String customerFirstname, String customerLastname, Database database) {
        try {
            String condition = "WHERE last_name = '" + customerLastname + "' and first_name = '" + customerFirstname + "'";
            int customerId = Integer.parseInt(database.retrieve(Database.CUSTOMER, condition, "", "")[1][0]);
            database.deleteCustomer(customerId);
            return true;
        } catch (SQLException | DatabaseSyntaxError ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deletePersonel(String personelFirstname, String personelsLastname, Database database) {
        try {
            String condition = "WHERE last_name = '" + personelsLastname + "' and first_name = '" + personelFirstname + "'";
            int personelId = Integer.parseInt(database.retrieve(Database.PERSONNEL, condition, "", "")[1][0]);
            database.deletePersonnel(personelId);
            return true;
        } catch (SQLException | DatabaseSyntaxError ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteSupplier(String suppliersFirstname, String suppliersLastname, Database database) {
        try {
            String condition = "WHERE last_name = '" + suppliersLastname + "' and first_name = '" + suppliersFirstname + "'";
            int supplierId = Integer.parseInt(database.retrieve(Database.SUPPLIER, condition, "", "")[1][0]);
            database.deleteSupplier(supplierId);
            return true;
        } catch (SQLException | DatabaseSyntaxError ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(String productname, Database database) {
        try {
            String condition = "WHERE name = '" + productname + "'";
            int productId = Integer.parseInt(database.retrieve(Database.PRODUCT, condition, "", "")[1][0]);
            database.deleteProduct(productId);
            return true;
        } catch (SQLException | DatabaseSyntaxError ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteSales(String customerFirstname, String customerLastname, Database database) {
        try {
            String condition = "WHERE last_name = '" + customerLastname + "' and first_name = '" + customerFirstname + "'";
            int customerId = Integer.parseInt(database.retrieve(Database.CUSTOMER, condition, "", "")[1][0]);
            condition = "WHERE customer_customer_id = " + customerId + "";
            int orderId = Integer.parseInt(database.retrieve(Database.ORDERS, condition, "", "")[1][0]);
            database.deleteOrder(orderId);
            return true;
        } catch (SQLException | DatabaseSyntaxError ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deletePurchase(String supplierFirstname, String supplierLastname, Database database) {
        try {
            String condition = "WHERE last_name = '" + supplierLastname + "' and first_name = '" + supplierFirstname + "'";
            int supplier_id = Integer.parseInt(database.retrieve(Database.SUPPLIER, condition, "", "")[1][0]);
            condition = "WHERE supplier_supplier_id = " + supplier_id + "";
            int orderId = Integer.parseInt(database.retrieve(Database.ORDERS, condition, "", "")[1][0]);
            database.deleteOrder(orderId);
            return true;
        } catch (SQLException | DatabaseSyntaxError ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ObservableList<Customers> getCustomers(Database database) {
        ObservableList<Customers> customersList = FXCollections.observableArrayList();
        try {
            String[][] databaseInformation = database.retrieve(Database.CUSTOMER, "", "", "");
            String[][] databaseMobileNumber = database.retrieve(Database.CUSTOMER_PHONE_NUMBER, "", "", "");
            String[][] databaseEmailAddress = database.retrieve(Database.CUSTOMER_EMAIL_ADDRESS, "", "", "");
            for (int i = 2; i < databaseInformation.length; i++) {
                customersList.add(new Customers(databaseInformation[i][1], databaseInformation[i][2], databaseMobileNumber[i][0], databaseInformation[i][3], databaseEmailAddress[i][0], databaseInformation[i][5]));
            }
        } catch (SQLException | DatabaseSyntaxError ex) {

        }
        return customersList;
    }

    public ObservableList<Personels> getPersonnels(Database database) {
        ObservableList<Personels> personnelsList = FXCollections.observableArrayList();
        try {
            String[][] databaseInformation = database.retrieve(Database.PERSONNEL, "", "", "");
            String[][] databaseMobileNumber = database.retrieve(Database.PERSONNEL_PHONE_NUMBER, "", "", "");
            String[][] databaseEmailAddress = database.retrieve(Database.PERSONNEL_EMAIL_ADDRESS, "", "", "");
            for (int i = 2; i < databaseInformation.length; i++) {
                personnelsList.add(new Personels(databaseInformation[i][2], databaseInformation[i][3], databaseMobileNumber[i][0], databaseInformation[i][4], databaseEmailAddress[i][0], databaseInformation[i][6], databaseInformation[i][1], databaseInformation[i][5], databaseInformation[i][8], Double.parseDouble(databaseInformation[i][7]), databaseInformation[i][10], databaseInformation[i][9]));
            }
        } catch (SQLException | DatabaseSyntaxError ex) {

        }
        return personnelsList;
    }

    public ObservableList<Suppliers> getSuppliers(Database database) {
        ObservableList<Suppliers> suppliersList = FXCollections.observableArrayList();
        try {
            String[][] databaseInformation = database.retrieve(Database.SUPPLIER, "", "", "");
            String[][] databaseMobileNumber = database.retrieve(Database.SUPPLIER_PHONE_NUMBER, "", "", "");
            String[][] databaseEmailAddress = database.retrieve(Database.SUPPLIER_EMAIL_ADDRESS, "", "", "");
            String[][] databaseFaxNumber = database.retrieve(Database.SUPPLIER_FAX_NUMBER, "", "", "");
            for (int i = 2; i < databaseInformation.length; i++) {
                suppliersList.add(new Suppliers(databaseInformation[i][1], databaseInformation[i][2], databaseMobileNumber[i][0], databaseInformation[i][3], databaseEmailAddress[i][0], databaseFaxNumber[i][0], databaseInformation[i][6], "0", databaseInformation[i][7]));
            }
        } catch (SQLException | DatabaseSyntaxError ex) {

        }
        return suppliersList;
    }

    public ObservableList<Productes> getProducts(Database database) {
        ObservableList<Productes> productsList = FXCollections.observableArrayList();
        try {
            String[][] databaseInformation = database.retrieve(Database.PRODUCT, "", "", "");
            for (int i = 2; i < databaseInformation.length; i++) {
                productsList.add(new Productes(databaseInformation[i][1], Double.parseDouble(databaseInformation[i][2]), Integer.parseInt(databaseInformation[i][3]), Integer.parseInt(databaseInformation[i][4]), databaseInformation[i][5]));
            }
        } catch (SQLException | DatabaseSyntaxError ex) {

        }
        return productsList;

    }

    public ObservableList<Sales> getSales(Database database) {
        ObservableList<Sales> salesList = FXCollections.observableArrayList();
        try {
            String[][] databaseInformation = database.retrieve(Database.ORDERS, "", "", "");
            for (int i = 2; i < databaseInformation.length; i++) {
                String condition = "WHERE customer_id = '" + databaseInformation[i][6] + "'";
                String customerName = database.retrieve(Database.CUSTOMER, condition, "", "")[1][1];
                String customerLastName = database.retrieve(Database.CUSTOMER, condition, "", "")[1][1];
                condition = "WHERE order_order_id = '" + databaseInformation[i][0] + "'";
                String product_id = database.retrieve(Database.ORDER_PRODUCT, condition, "", "")[1][1];
                condition = "WHERE product_id = '" + product_id + "'";
                String productName = database.retrieve(Database.PRODUCT, condition, "", "")[1][1];
                Sales newSale = new Sales(customerName, customerLastName, productName, databaseInformation[i][3], databaseInformation[i][4], Integer.parseInt(databaseInformation[i][5]), Double.parseDouble(databaseInformation[i][1]), databaseInformation[i][9]);
                condition = "WHERE personnel_id = '" + databaseInformation[i][7] + "'";
                String personelName = database.retrieve(Database.PERSONNEL, condition, "", "")[1][2];
                newSale.setPersonnelName(personelName);
                salesList.add(newSale);
            }
        } catch (SQLException | DatabaseSyntaxError ex) {

        }
        return salesList;
    }

    public ObservableList<Purchase> getPurchase(Database database) {
        ObservableList<Purchase> purchasesList = FXCollections.observableArrayList();
        try {
            String[][] databaseInformation = database.retrieve(Database.ORDERS, "", "", "");
            for (int i = 2; i < databaseInformation.length; i++) {
                String condition = "WHERE supplier_id = '" + databaseInformation[i][8] + "'";
                String supplierName = database.retrieve(Database.SUPPLIER, condition, "", "")[1][1];
                String supplierLastName = database.retrieve(Database.SUPPLIER, condition, "", "")[1][2];
                condition = "WHERE order_order_id = '" + databaseInformation[i][0] + "'";
                String product_id = database.retrieve(Database.ORDER_PRODUCT, condition, "", "")[1][1];
                condition = "WHERE product_id = '" + product_id + "'";
                String productName = database.retrieve(Database.PRODUCT, condition, "", "")[1][1];
                Purchase newPurchase = new Purchase(supplierName, supplierLastName, productName, databaseInformation[i][3], databaseInformation[i][4], Integer.parseInt(databaseInformation[i][5]), Double.parseDouble(databaseInformation[i][2]), Double.parseDouble(databaseInformation[i][1]), databaseInformation[i][9]);
                condition = "WHERE personnel_id = '" + databaseInformation[i][7] + "'";
                String personelName = database.retrieve(Database.PERSONNEL, condition, "", "")[1][2];
                newPurchase.setPersonnelName(personelName);
                purchasesList.add(newPurchase);
            }
        } catch (SQLException | DatabaseSyntaxError ex) {

        }
        return purchasesList;
    }

}
