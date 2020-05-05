package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewEventFormController {
    @FXML private Label messageLabel;
    @FXML private Button cancelButton;
    @FXML private Button createButton;

    @FXML private TextField capacityField;
    @FXML private TextField dateField;
    @FXML private TextField venueField;
    @FXML private TextField descriptionField;
    @FXML private TextField titleField;

    public void createEventButtonHandle(ActionEvent actionEvent) {
    }


    public void cancelButtonHandle(ActionEvent actionEvent) {
    }
}
