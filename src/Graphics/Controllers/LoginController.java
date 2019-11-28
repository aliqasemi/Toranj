package Graphics.Controllers;

import Data.Users;
import Database.Database;
import Database.Exceptions.DatabaseSyntaxError;
import Database.Exceptions.DriverClassNotFoundException;
import Graphics.Other.Start;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public static Users user;

    @FXML
    private TextField UsernameField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button EnterButton;

    @FXML
    private Label VersionLabel;

    public static String Username;

    private static String path_login = "jdbc:mysql://localhost:3306/login?useSSL=false&autoReconnect=true";

    public static Database database;

    static {
        try {
            database = new Database(path_login, "root", "qwerty");
        } catch (DriverClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VersionLabel.setText("1.0.3");
    }

    private boolean InitializeUser() throws DatabaseSyntaxError, SQLException {
        String[] loginData = database.isValidUsername(UsernameField.getText(), PasswordField.getText());
        if (loginData.length == 1) {
            return false;
        }
        user = new Users(UsernameField.getText(), PasswordField.getText(), Integer.parseInt(loginData[0]), Integer.parseInt(loginData[1]), loginData[2]);
        return true;
    }

    @FXML
    void EnterButtonAction() throws IOException, DatabaseSyntaxError, SQLException {
        if (InitializeUser()) {
            if (UsernameField.getText().equals("مدیر")) {
                Username = "مدیر";
            } else if (UsernameField.getText().equals("کاربر1")) {
                Username = "علی قاسمی";
            } else if (UsernameField.getText().equals("کاربر2")) {
                Username = "نگار اقبال";
            }
            Parent root = FXMLLoader.load(getClass().getResource("/Graphics/FXMLS/MainPage.fxml"));
            Stage MainPage = new Stage();
            MainPage.setResizable(false);
            MainPage.setScene(new Scene(root, 600, 400));
            Stage LoginPage = (Stage) EnterButton.getScene().getWindow();
            MainPage.show();
            LoginPage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("خطا");
            alert.setHeaderText(null);
            alert.setContentText("نام کاربری یا رمز عبور اشتباه است!");
            alert.showAndWait();
        }
    }

}
