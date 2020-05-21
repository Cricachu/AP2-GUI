package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ReplyMessage {

    @FXML private Label messageLabel;

    public void okButtonPressed(ActionEvent actionEvent) {
        //get the stage information
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.close();

    }

    public void initData(String message) {
        messageLabel.setText(message);
    }
}
