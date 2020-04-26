package controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import model.exceptions.FormatException;
import model.exceptions.NameException;

public class MainWindow implements Initializable {
    @FXML private ScrollPane scrollPaneView;
    @FXML private HBox titleCentre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load content to scroll Pane
        //Add vbox to store elements
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(50));

        //add data to the Vbox
        for (int i = 0; i <50 ; i++) {
            //wrap each data chunks in a hbox
            HBox postPanel= new HBox();
            postPanel.setPadding(new Insets(10,10,10,10));
            postPanel.setSpacing(10);
            Label label = new Label("Welcome!");
            TextField postDes= new TextField();
            postPanel.getChildren().addAll(label,postDes);
            postPanel.setAlignment(Pos.CENTER);
            //add the collection of hbox to vbox
            vbox.getChildren().add(postPanel);
        }
        scrollPaneView.setContent(vbox);

    }
}
