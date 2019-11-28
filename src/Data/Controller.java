package Data;

import Database.Database;
import Database.Exceptions.DatabaseSyntaxError;
import Database.Exceptions.DriverClassNotFoundException;

import java.sql.SQLException;


public class Controller {

    private static String path_login = "jdbc:mysql://localhost:3306/login?useSSL=false&autoReconnect=true";
    private static String db_password = "qwerty";
    private static String db_username = "root";

    private Database database;
    private Users user;


    public Controller() {
        createDatabase();


    }

    private void createDatabase() {
        try {
            database = new Database(path_login, db_username, db_password);

        } catch (DriverClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean createUser(String username, String password) {
        try {
            String[] info = database.isValidUsername(username, password);

            if (info.length == 0) {
                //User no exist!
                return false;
            }

            this.user = new Users(username, password, Integer.parseInt(info[0]), Integer.parseInt(info[1]), info[2]);

            return true;
        } catch (DatabaseSyntaxError e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}


