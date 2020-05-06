package main;
import controller.EventDetailsController;
import controller.view1Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

public class mainGUI extends Application {
    UniLink uni=new UniLink();

    public static void main(String[] args) {

        //launch GUI
        launch(args);
    }

@Override
    public void start(Stage stage) {
    try {
//        Parent root= FXMLLoader.load(getClass().getResource("/view/view_1.fxml"));
//
//        Scene scene= new Scene(root,600,400);
//        stage.setScene(scene);
//        stage.show();

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/view_1.fxml"));
        Parent login= loader.load();

        Scene loginView= new Scene(login,600,400);

        //access controller and call init method
        view1Controller controller=loader.getController();
        controller.initData(uni);

        //set scene
        stage.setScene(loginView);
        stage.setTitle("Log In");
        stage.show();


    } catch (IOException e) {
        System.out.println("Fail to load FXML file");

    }
}
}
