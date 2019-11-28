package Graphics.Other;

import Database.Database;
import Database.Exceptions.DriverClassNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start extends Application {

    private static String path_toranj = "jdbc:mysql://localhost:3306/toranj?useSSL=false&autoReconnect=true";
    private static String path_login = "jdbc:mysql://localhost:3306/login?useSSL=false&autoReconnect=true";
    private static String username = "root";
    private static String password = "qwerty";
    public static Database database;

    static {
        try {
            database = new Database(path_toranj, username, password);
        } catch (DriverClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Start() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Graphics/FXMLS/Login.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Store");
        primaryStage.getIcons().add(new Image("file:Logo.png"));
        primaryStage.setScene(new Scene(root, 550, 350));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

