package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

public class mainGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

@Override
    public void start(Stage stage) {
    try {
        Parent root= FXMLLoader.load(getClass().getResource("/view/view_1.fxml"));
        Scene scene= new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();

    } catch (IOException e) {
        System.out.println("Fail to load FXML file");

    }
}
}
