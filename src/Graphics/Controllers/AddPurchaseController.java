package Graphics.Controllers;

import Data.Customers;
import Data.Productes;
import Data.Purchase;
import Data.Suppliers;
import Graphics.Other.Start;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPurchaseController implements Initializable {

    @FXML
    private MenuButton UserPanelMenu;

    @FXML
    private Label UserLabel;

    @FXML
    private Label VersionLabel;

    @FXML
    private TextField OrderDateField;

    @FXML
    private TextField ProductPriceField;

    @FXML
    private TextField DeliveryDateField;

    @FXML
    private TextField CommentField;

    @FXML
    private TextField ProductCountField;

    @FXML
    private TextField DepositField;

    @FXML
    private ComboBox<Suppliers> SuppliersComboBox;

    @FXML
    private ComboBox<Productes> ProductsComboBox;

    @FXML
    private MenuItem PersonnelItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserLabel.setText(LoginController.Username);
        VersionLabel.setText("1.0.3");
        if (LoginController.user.getLevel() == 1) {
            PersonnelItem.setVisible(false);
        }
        SuppliersComboBox.setItems(LoginController.user.getSuppliers(Start.database));
        ProductsComboBox.setItems(LoginController.user.getProducts(Start.database));
        SuppliersComboBox.setConverter(new StringConverter<Suppliers>() {
            @Override
            public String toString(Suppliers supplier) {
                return supplier.getName() + " " + supplier.getLastName();
            }

            @Override
            public Suppliers fromString(String string) {
                return null;
            }
        });
        ProductsComboBox.setConverter(new StringConverter<Productes>() {
            @Override
            public String toString(Productes product) {
                return product.getName();
            }

            @Override
            public Productes fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    void ChangePasswordItemAction() throws IOException {
        GoToPage("ChangePassword", 600, 382);
    }

    @FXML
    void ConfirmButtonAction() {
        try {
            Purchase newPurchase = new Purchase(SuppliersComboBox.getSelectionModel().getSelectedItem().getName(), SuppliersComboBox.getSelectionModel().getSelectedItem().getLastName(), ProductsComboBox.getSelectionModel().getSelectedItem().getName(), OrderDateField.getText(), DeliveryDateField.getText(), Integer.parseInt(ProductCountField.getText()), Double.parseDouble(ProductPriceField.getText()), Double.parseDouble(DepositField.getText()), CommentField.getText());
            LoginController.user.add_Purchase(newPurchase, Start.database);
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
