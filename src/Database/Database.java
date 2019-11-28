package Database;


import Database.Exceptions.*;
import Database.Tables.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.sun.istack.internal.NotNull;

import java.sql.*;

public class Database {

    public final static String CUSTOMER = "customer";
    public final static String CUSTOMER_EMAIL_ADDRESS = "customer_email_address";
    public final static String CUSTOMER_PHONE_NUMBER = "customer_phone_number";
    public final static String ORDER_PRODUCT = "order_has_product";
    public final static String ORDERS = "orders";
    public final static String PERSONNEL = "personnel";
    public final static String PERSONNEL_EMAIL_ADDRESS = "personnel_email_address";
    public final static String PERSONNEL_PHONE_NUMBER = "personnel_phone_number";
    public final static String PRODUCT = "product";
    public final static String SUPPLIER = "supplier";
    public final static String SUPPLIER_EMAIL_ADDRESS = "supplier_email_address";
    public final static String SUPPLIER_FAX_NUMBER = "supplier_fax_number";
    public final static String SUPPLIER_PHONE_NUMBER = "supplier_phone_number";

    public final static String ASCENDING = "ASC";
    public final static String DESCENDING = "DESC";

    private String driver = "com.mysql.jdbc.Driver";
    private String server_path ; //jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false
    private String username;
    private String password;

    public Database(String server_path, String username, String password) throws DriverClassNotFoundException {
        setUsername(username);
        setPassword(password);
        registerDriver();
        setServerPath(server_path);

    }


    private void setServerPath(String server_path) {
        this.server_path = server_path;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void registerDriver() throws DriverClassNotFoundException {
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException ex) {
            throw new DriverClassNotFoundException("Class driver not found. i.e. \"com.mysql.jdbc.Driver\" not exist");
        }
    }

    public void add(Tables table_name) throws DatabaseConnectionException, DuplicatedEntryException, NotNullException {

        if (table_name == null) {
            throw new NotNullException("Table name can not be NULL");
        }
        try (Connection connection = DriverManager.getConnection(this.server_path, this.username, this.password)
             ;
             Statement statement = connection.createStatement()
        ) {


            String query = "INSERT INTO " + table_name.getValue() + " ( " + table_name.getFields() + " ) " +
                    "VALUES (" + table_name.getFieldsValues() + " )";

            statement.executeUpdate(query);
        } catch (SQLException ex) {
            if (ex.getMessage().contains("Duplicate")) {
                throw new DuplicatedEntryException(ex.getMessage());
            } else if (ex.getMessage().contains("Cannot be null")) {
                throw new NotNullException("This field can not be null.");
            } else if (ex.getMessage().contains("Cannot add or update a child row: a foreign key constraint")) {
                throw new DuplicatedEntryException(ex.getMessage());
            }
            String message = "Error in connecting to database: " + ex.getMessage();
            throw new DatabaseConnectionException(message);
        }
    }

    public String[][] retrieve(@NotNull String table_name, String condition, String order_by, String order_type) throws SQLException, DatabaseSyntaxError {

        String temp_condition = condition;
        String temp_order_by = order_by;
        String temp_order_type = order_type;

        if (temp_condition == null) {
            temp_condition = "";
        }
        if (temp_order_by == null) {
            temp_order_by = "";
        }
        if (temp_order_type == null) {
            temp_order_type = "";
        }


        int row_count;
        ResultSetMetaData resultSetMetaData;
        int column_count;
        ResultSet resultSet;
        String[][] result;
        try (Connection connection = DriverManager.getConnection(this.server_path, this.username, this.password)
             ;
             Statement statement = connection.createStatement()
        ) {

            String query;
            String ord;
            if (order_by == null || order_by.trim().equals("")) {
                ord = "";
            } else {
                ord = " ORDER BY ";
            }

            query = "SELECT * FROM " + table_name + " " + temp_condition + ord + temp_order_by + " " + temp_order_type;
            resultSet = statement.executeQuery(query);

            resultSet.last();
            row_count = resultSet.getRow();
            resultSet.first();
            resultSetMetaData = resultSet.getMetaData();
            column_count = resultSetMetaData.getColumnCount();
            result = new String[row_count + 1][column_count];

            for (int j = 0; j < column_count; j++) {
                result[0][j] = resultSetMetaData.getColumnLabel(j + 1);
            }

            for (int i = 1; i <= row_count; i++) {
                for (int j = 0; j < column_count; j++) {
                    result[i][j] = resultSet.getString(j + 1);
                }
                resultSet.next();
            }
        } catch (MySQLSyntaxErrorException ex) {
            throw new DatabaseSyntaxError(ex.getMessage());
        } catch (SQLException ex) {
            throw ex;
        }

        return result;
    }


    public void update(@NotNull String table_name, @NotNull String set, String condition) throws DatabaseSyntaxError, SQLException {

        String temp_condition = condition;
        if (temp_condition == null) {
            temp_condition = "";
        }


        try (Connection connection = DriverManager.getConnection(this.server_path, this.username, this.password)
             ;
             Statement statement = connection.createStatement()
        ) {

            String query;

            query = "UPDATE " + table_name + " SET " + set + " " + temp_condition;
            statement.executeUpdate(query);

        } catch (MySQLSyntaxErrorException ex) {
            throw new DatabaseSyntaxError(ex.getMessage());
        } catch (SQLException ex) {
            throw ex;
        }

    }

    public void delete(@NotNull String table_name, String condition) throws DatabaseSyntaxError, SQLException {

        String temp_condition = condition;
        if (temp_condition == null) {
            temp_condition = "";
        }
        try (Connection connection = DriverManager.getConnection(this.server_path, this.username, this.password)
             ;
             Statement statement = connection.createStatement()
        ) {

            String query;

            query = "DELETE FROM " + table_name + " " + temp_condition;
            statement.executeUpdate(query);

        } catch (MySQLSyntaxErrorException ex) {
            throw new DatabaseSyntaxError(ex.getMessage());
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public String[] isValidUsername(String username, String password) throws DatabaseSyntaxError, SQLException {

        ResultSet resultSet;
        String user, pass;
        int level;
        int personnel_id;
        String notes;

        try (Connection connection = DriverManager.getConnection(this.server_path, this.username, this.password)
             ;
             Statement statement = connection.createStatement()
        ) {

            String query;

            query = "SELECT * FROM login_info " + "WHERE username = '" + username + "'";
            resultSet = statement.executeQuery(query);

            resultSet.last();
            if (resultSet.getRow() == 0) {
                String[] temp = new String[1];
                temp[0] = "Username not found!";
                return temp;
            }
            resultSet.first();
            user = resultSet.getString("username");
            pass = resultSet.getString("password");
            level = resultSet.getInt("user_level");
            personnel_id = resultSet.getInt("personnel_id");
            notes = resultSet.getString("notes");
        } catch (MySQLSyntaxErrorException ex) {
            throw new DatabaseSyntaxError(ex.getMessage());
        } catch (SQLException ex) {
            throw ex;
        }


        if (password.equals(pass)) {
            String[] temp = new String[3];
            temp[0] = Integer.toString(level);
            temp[1] = Integer.toString(personnel_id);
            temp[2] = notes;
            return temp;
        }

        String[] temp = new String[1];
        temp[0] = "Password is incorrect!";
        return temp;
    }

    public int registerUser(String username, String password, int level, int personnel_id, String notes) throws
            DuplicatedEntryException, NotNullException, DatabaseConnectionException {

        int row_changed;
        try (Connection connection = DriverManager.getConnection(this.server_path, this.username, this.password)
             ;
             Statement statement = connection.createStatement()
        ) {


            String query = "INSERT INTO login_info (username, password, user_level, personnel_id, notes)" +
                    " VALUES ('" + username + "', '" + password + "', " + level + " ," + personnel_id + ", '" + notes + "' )";

            row_changed = statement.executeUpdate(query);
        } catch (SQLException ex) {
            if (ex.getMessage().contains("Duplicate")) {
                throw new DuplicatedEntryException(ex.getMessage());
            } else if (ex.getMessage().contains("Cannot be null")) {
                throw new NotNullException("This field can not be null.");
            } else if (ex.getMessage().contains("Cannot add or update a child row: a foreign key constraint")) {
                throw new DuplicatedEntryException(ex.getMessage());
            }
            String message = "Error in connecting to database: " + ex.getMessage();
            throw new DatabaseConnectionException(message);
        }
        return row_changed;
    }

    public int deleteUser(String username) throws DatabaseSyntaxError, SQLException {
        int row_changed;
        try (Connection connection = DriverManager.getConnection(this.server_path, this.username, this.password)
             ;
             Statement statement = connection.createStatement()
        ) {

            String query;

            query = "DELETE FROM login_info WHERE username = '" + username + "'";
            row_changed = statement.executeUpdate(query);

        } catch (MySQLSyntaxErrorException ex) {
            throw new DatabaseSyntaxError(ex.getMessage());
        } catch (SQLException ex) {
            throw ex;
        }
        return row_changed;
    }

    public int updateUser(String username, String new_password) throws DatabaseSyntaxError, SQLException {
        int row_changed;
        try (Connection connection = DriverManager.getConnection(this.server_path, this.username, this.password)
             ;
             Statement statement = connection.createStatement()
        ) {

            String query;

            query = "UPDATE login_info" + " SET password = '" + new_password + "' WHERE username = '" + username + "'";
            row_changed = statement.executeUpdate(query);

        } catch (MySQLSyntaxErrorException ex) {
            throw new DatabaseSyntaxError(ex.getMessage());
        } catch (SQLException ex) {
            throw ex;
        }
        return row_changed;
    }

    public int updateUser(String username, int new_level) throws DatabaseSyntaxError, SQLException {
        int row_changed;
        try (Connection connection = DriverManager.getConnection(this.server_path, this.username, this.password)
             ;
             Statement statement = connection.createStatement()
        ) {

            String query;

            query = "UPDATE login_info" + " SET user_level = " + new_level + " WHERE username = '" + username + "'";
            row_changed = statement.executeUpdate(query);

        } catch (MySQLSyntaxErrorException ex) {
            throw new DatabaseSyntaxError(ex.getMessage());
        } catch (SQLException ex) {
            throw ex;
        }
        return row_changed;
    }

    public int updateUser(String username, int new_personnel_id, String notes) throws DatabaseSyntaxError, SQLException {
        int row_changed;
        try (Connection connection = DriverManager.getConnection(this.server_path, this.username, this.password)
             ;
             Statement statement = connection.createStatement()
        ) {

            String query;

            query = "UPDATE login_info" + " SET personnel_id = " + new_personnel_id + ", notes = '" + notes + "'" +
                    " WHERE username = '" + username + "'";
            row_changed = statement.executeUpdate(query);

        } catch (MySQLSyntaxErrorException ex) {
            throw new DatabaseSyntaxError(ex.getMessage());
        } catch (SQLException ex) {
            throw ex;
        }
        return row_changed;
    }

    public int updateUser(String username, String new_password, int new_level) throws DatabaseSyntaxError, SQLException {
        int row_changed;

        updateUser(username, new_password);
        row_changed = updateUser(username, new_level);

        return row_changed;

    }

    //*************************************Add**************************************************************************

    public void addCustomer(int customer_id, String first_name, String last_name,
                            String sex, int count_of_orders, String notes, String[] customer_email_address,
                            String[] customer_phone_number) throws DuplicatedEntryException, NotNullException, DatabaseConnectionException {
        Customer customer = new Customer(customer_id, first_name, last_name, sex, count_of_orders, notes);
        CustomerEmailAddress customerEmailAddress;
        CustomerPhoneNumber customerPhoneNumber;
        add(customer);

        for (int i = 0; i < customer_email_address.length; i++) {
            customerEmailAddress = new CustomerEmailAddress(customer_email_address[i], customer_id);
            add(customerEmailAddress);
        }
        for (int i = 0; i < customer_phone_number.length; i++) {
            customerPhoneNumber = new CustomerPhoneNumber(customer_phone_number[i], customer_id);
        }
    }

    public void addPersonnel(int personnel_id, @NotNull String national_id, String first_name, String last_name, String sex,
                             String address, String date_of_birth, double salary, String start_date, String position, String description,
                             String[] personnel_email_address, String[] personnel_phone_number) throws DuplicatedEntryException, NotNullException, DatabaseConnectionException {
        Personnel personnel = new Personnel(personnel_id, national_id, first_name, last_name, sex, address, date_of_birth,
                salary, start_date, position, description);
        add(personnel);

        PersonnelEmailAddress personnelEmailAddress;
        for (int i = 0; i < personnel_email_address.length; i++) {
            personnelEmailAddress = new PersonnelEmailAddress(personnel_email_address[i], personnel_id, national_id);
        }
        PersonnelPhoneNumber personnelPhoneNumber;
        for (int i = 0; i < personnel_phone_number.length; i++) {
            personnelPhoneNumber = new PersonnelPhoneNumber(personnel_phone_number[i], personnel_id, national_id);
        }

    }

    public void addProduct(int product_id, String name, double price, int discount_percent, int count_of_product, String description)
            throws DuplicatedEntryException, NotNullException, DatabaseConnectionException {
        Product product = new Product(product_id, name, price, discount_percent, count_of_product, description);
        add(product);
    }

    public void addSupplier(int supplier_id, String first_name, String last_name, String sex, String start_date, String end_date
            , String business_address, String description, String[] email_address, String[] fax_number, String[] phone_number)
            throws DuplicatedEntryException, NotNullException, DatabaseConnectionException {
        Supplier supplier = new Supplier(supplier_id, first_name, last_name, sex, start_date, end_date, business_address,
                description);
        add(supplier);
        SupplierEmailAddress emailAddress;
        for (int i = 0; i < email_address.length; i++) {
            emailAddress = new SupplierEmailAddress(email_address[i], supplier_id);
        }
        SupplierFaxNumber faxNumber;
        for (int i = 0; i < fax_number.length; i++) {
            faxNumber = new SupplierFaxNumber(fax_number[i], supplier_id);
        }
        SupplierPhoneNumber phoneNumber;
        for (int i = 0; i < phone_number.length; i++) {
            phoneNumber = new SupplierPhoneNumber(phone_number[i], supplier_id);
        }

    }

    public void addOrder(int order_id, Double prepaid_fund, Double total_amount, String order_date, String delivery_date,
                         Integer count_of_product, int customer_id, int personnel_id, int supplier_id, String description)
            throws DuplicatedEntryException, NotNullException, DatabaseConnectionException {
        Order order = new Order(order_id, prepaid_fund, total_amount, order_date, delivery_date, count_of_product, customer_id,
                personnel_id, supplier_id, description);
        add(order);
    }

    //*********************************Delete***************************************************************************
    public void deleteOrder(int order_id) throws DatabaseSyntaxError, SQLException {
        String condition = "Where order_order_id = " + order_id;
        String con = "Where order_id = " + order_id;
        delete(Database.ORDER_PRODUCT, condition);
        delete(Database.ORDERS, con);
    }

    public void deleteCustomer(int customer_id) throws DatabaseSyntaxError, SQLException {
        String condition = "Where customer_customer_id = " + customer_id;
        String condition2 = "Where customer_id = " + customer_id;
        delete(Database.CUSTOMER_EMAIL_ADDRESS, condition);
        delete(Database.CUSTOMER_PHONE_NUMBER, condition);

        String[][] customer_orders = retrieve(Database.ORDERS, condition, null, null);
        if (customer_orders.length != 1) {
            for (int i = 1; i < customer_orders.length; i++) {
                String con = "Where order_order_id = " + customer_orders[i][0];
                delete(Database.ORDER_PRODUCT, con);
            }
        }
        delete(Database.ORDERS, condition);
        delete(Database.CUSTOMER, condition2);
    }

    public void deletePersonnel(int personnel_id) throws DatabaseSyntaxError, SQLException {
        String condition = "Where personnel_personnel_id = " + personnel_id;
        String con = "Where personnel_id = " + personnel_id;
        delete(Database.PERSONNEL_EMAIL_ADDRESS, condition);
        delete(Database.PERSONNEL_PHONE_NUMBER, condition);

        String[][] orders = retrieve(Database.ORDERS, condition, null, null);
        if (orders.length != 1) {
            for (int i = 1; i < orders.length; i++) {
                int order_id = Integer.parseInt(orders[i][0]);
                deleteOrder(order_id);
            }
        }
        delete(Database.PERSONNEL, con);
    }

    public void deleteSupplier(int supplier_id) throws DatabaseSyntaxError, SQLException {
        String condition = "Where supplier_supplier_id = " + supplier_id;
        delete(Database.SUPPLIER_EMAIL_ADDRESS, condition);
        delete(Database.SUPPLIER_FAX_NUMBER, condition);
        delete(Database.SUPPLIER_PHONE_NUMBER, condition);

        String[][] supplier_orders = retrieve(Database.ORDERS, condition, null, null);

        if (supplier_orders.length != 1) {
            for (int i = 1; i < supplier_orders.length; i++) {
                int order_id = Integer.parseInt(supplier_orders[i][0]);
                deleteOrder(order_id);
            }
        }
        String con = "Where supplier_id = " + supplier_id;
        delete(Database.SUPPLIER, con);
    }

    public void deleteProduct(int product_id) throws DatabaseSyntaxError, SQLException {
        String condition = "Where product_product_id = " + product_id;
        delete(Database.ORDER_PRODUCT, condition);
        String con = "Where product_id = " + product_id;
        delete(Database.PRODUCT, con);
    }
}
