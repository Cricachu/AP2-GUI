package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.exceptions.FormatException;

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
        String userId=view1Controller.userId;
        String title=titleField.getText();
        String desc=descriptionField.getText();
        String venue=venueField.getText();
        String date=dateField.getText();
        String cap=capacityField.getText();

        try{
            view1Controller.uni.createNewEvent(userId,title,desc,venue,date,cap);
            messageLabel.setText("Succesfully create new event!");
            view1Controller.changeToMainWindow(actionEvent);
        }catch(FormatException e) {
            messageLabel.setText(e.getReason());
        }catch(Exception e) {
            messageLabel.setText(e.getMessage());
        }
    }


    public void cancelButtonHandle(ActionEvent actionEvent) {
    }
}
