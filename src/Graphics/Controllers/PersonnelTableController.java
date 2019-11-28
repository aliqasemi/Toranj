package Graphics.Controllers;

import Data.Personels;
import Database.Tables.Personnel;
import Graphics.Other.Start;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonnelTableController implements Initializable {

    @FXML
    private MenuButton UserPanelMenu;

    @FXML
    private Label UserLabel;

    @FXML
    private Label VersionLabel;

    @FXML
    private TableView<Personels> PersonnelTableView;

    @FXML
    private TableColumn<Personels, Number> TierTableColumn;

    @FXML
    private TableColumn<Personels, String> FirstNameTableColumn;

    @FXML
    private TableColumn<Personels, String> LastNameTableColumn;

    @FXML
    private TableColumn<Personnel, String> TelephoneTableColumn;

    public static Personels selectionPersonnel;

    @FXML
    private MenuItem PersonnelItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserLabel.setText(LoginController.Username);
        VersionLabel.setText("1.0.3");
        if (LoginController.user.getLevel() == 1) {
            PersonnelItem.setVisible(false);
        }
        TierTableColumn.setSortable(false);
        TierTableColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(PersonnelTableView.getItems().indexOf(column.getValue()) + 1));
        FirstNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        LastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TelephoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("mobilePhoneNumber"));
        PersonnelTableView.setItems(LoginController.user.getPersonnels(Start.database));
        PersonnelTableView.setRowFactory(tv -> {
            TableRow<Personels> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        selectionPersonnel = row.getItem();
                        GoToPage("PersonnelInformationDetails", 600, 600);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    @FXML
    void ChangePasswordItemAction() throws IOException {
        GoToPage("ChangePassword", 600, 382);
    }

    @FXML
    void CreateNewPersonnelButtonAction() throws IOException {
        GoToPage("AddPersonnel", 600, 610);
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
