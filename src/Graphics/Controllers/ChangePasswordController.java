package Graphics.Controllers;

import Database.Exceptions.DatabaseSyntaxError;
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

public class ChangePasswordController implements Initializable {

    @FXML
    private MenuButton UserPanelMenu;

    @FXML
    private Label UserLabel;

    @FXML
    private Label VersionLabel;

    @FXML
    private MenuItem PersonnelItem;

    @FXML
    private PasswordField LastPasswordField;

    @FXML
    private PasswordField NewPasswordField;

    @FXML
    private PasswordField RepeatNewPasswordField;

    @FXML
    private TextField usernameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserLabel.setText(LoginController.Username);
        VersionLabel.setText("1.0.3");
        if (LoginController.user.getLevel() == 1) {
            PersonnelItem.setVisible(false);
        }
    }

    @FXML
    void ChangePasswordItemAction() throws IOException {
        GoToPage("ChangePassword", 600, 382);
    }

    @FXML
    void ConfirmButtonAction() throws DatabaseSyntaxError, SQLException {
        if (LoginController.user.getLevel() == 0) {
            if (usernameField.getText().equals("مدیر")) {
                if (LastPasswordField.getText().equals(LoginController.user.getPassword()) && NewPasswordField.getText().equals(RepeatNewPasswordField.getText())) {
                    LoginController.database.updateUser("مدیر", NewPasswordField.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("تایید");
                    alert.setHeaderText(null);
                    alert.setContentText("رمز عبور تغییر یافت!");
                    alert.showAndWait();
                }
            } else if (usernameField.getText().equals("کاربر1") && NewPasswordField.getText().equals(RepeatNewPasswordField.getText())) {
                LoginController.database.updateUser("کاربر1", NewPasswordField.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("تایید");
                alert.setHeaderText(null);
                alert.setContentText("رمز عبور تغییر یافت!");
                alert.showAndWait();
            } else if (usernameField.getText().equals("کاربر2") && NewPasswordField.getText().equals(RepeatNewPasswordField.getText())) {
                LoginController.database.updateUser("کاربر2", NewPasswordField.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("تایید");
                alert.setHeaderText(null);
                alert.setContentText("رمز عبور تغییر یافت!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("خطا");
                alert.setHeaderText(null);
                alert.setContentText("خطا در ورود اطلاعات!");
                alert.showAndWait();
            }
            if (LoginController.user.getLevel() == 1) {
                if (usernameField.getText().equals("کاربر1") && LastPasswordField.getText().equals(LoginController.user.getPassword()) && NewPasswordField.getText().equals(RepeatNewPasswordField.getText())) {
                    LoginController.database.updateUser("کاربر1", NewPasswordField.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("تایید");
                    alert.setHeaderText(null);
                    alert.setContentText("رمز عبور تغییر یافت!");
                    alert.showAndWait();
                } else if (usernameField.getText().equals("کاربر2") && LastPasswordField.getText().equals(LoginController.user.getPassword()) && NewPasswordField.getText().equals(RepeatNewPasswordField.getText())) {
                    LoginController.database.updateUser("کاربر2", NewPasswordField.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("تایید");
                    alert.setHeaderText(null);
                    alert.setContentText("رمز عبور تغییر یافت!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("خطا");
                    alert.setHeaderText(null);
                    alert.setContentText("خطا در ورود اطلاعات!");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    void CreateProductItemAction() throws IOException {
        GoToPage("AddProduct", 600, 366);
    }

    @FXML
    void CreatePurchaseItemAction() throws IOException {
        GoToPage("AddPurchase", 600, 447);
    }

    @FXML
    void CreateSaleItemAction() throws IOException {
        GoToPage("AddSale", 600, 409);
    }

    @FXML
    void CustomersItemAction() throws IOException {
        GoToPage("CustomersTable", 600, 434);
    }

    @FXML
    void PersonnelItemAction() throws IOException {
        GoToPage("PersonnelTable", 600, 434);
    }

    @FXML
    void ProductsTableItemAction() throws IOException {
        GoToPage("ProductsTable", 600, 434);
    }

    @FXML
    void PurchasesTableItemAction() throws IOException {
        GoToPage("PurchasesTable", 600, 434);
    }

    @FXML
    void SaleTableItemAction() throws IOException {
        GoToPage("SaleTable", 600, 434);
    }

    @FXML
    void SuppliersItemAction() throws IOException {
        GoToPage("SuppliersTable", 600, 434);
    }

    @FXML
    void homeLinkAction() throws IOException {
        GoToPage("MainPage", 600, 400);
    }

    private void GoToPage(String page, int width, int height) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Graphics/FXMLS/" + page + ".fxml"));
        Stage NextPage = new Stage();
        NextPage.setResizable(false);
        NextPage.setScene(new Scene(root, width, height));
        Stage ThisPage = (Stage) UserPanelMenu.getScene().getWindow();
        NextPage.setResizable(false);
        NextPage.show();
        ThisPage.close();
    }

}
