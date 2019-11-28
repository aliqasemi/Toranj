package Graphics.Controllers;

import Data.Productes;
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
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    @FXML
    private MenuButton UserPanelMenu;

    @FXML
    private Label UserLabel;

    @FXML
    private Label VersionLabel;

    @FXML
    private TextField ProductNameField;

    @FXML
    private TextField ProductCountField;

    @FXML
    private TextField EndPriceField;

    @FXML
    private TextField DiscountPercentField;

    @FXML
    private TextField CommentField;

    @FXML
    private MenuItem PersonnelItem;

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
        GoToPage("ChangePassword", 600, 400);
    }

    @FXML
    void ConfirmButtonAction() {
        try {
            Productes newProduct = new Productes(ProductNameField.getText(), Double.parseDouble(EndPriceField.getText()), Integer.parseInt(DiscountPercentField.getText()), Integer.parseInt(ProductCountField.getText()), CommentField.getText());
            LoginController.user.addProduct(newProduct, Start.database);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("تایید");
            alert.setHeaderText(null);
            alert.setContentText("اطلاعات با موفقیت ثبت شد!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("خطا");
            alert.setHeaderText(null);
            alert.setContentText("خطا در ورود اطلاعات!");
            alert.showAndWait();
        }
    }

    @FXML
    void CreateProductItemAction() throws IOException {
        GoToPage("ChangePassword", 600, 382);
    }

    @FXML
    void CreatePurchaseItemAction() throws IOException {
        GoToPage("AddProduct", 600, 366);
    }

    @FXML
    void CreateSaleItemAction() throws IOException {
        GoToPage("AddPurchase", 600, 447);
    }

    @FXML
    void CustomersItemAction() throws IOException {
        GoToPage("AddSale", 600, 409);
    }

    @FXML
    void PersonnelItemAction() throws IOException {
        GoToPage("CustomersTable", 600, 434);
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
