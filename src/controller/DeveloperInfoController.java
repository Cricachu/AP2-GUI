package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class DeveloperInfoController {
    public void closeButtonHandle(ActionEvent event) {
        //close this window
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }
}
