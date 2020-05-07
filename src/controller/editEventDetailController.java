package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Event;
import model.Post;

public class editEventDetailController {
    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private TextField venueField;
    @FXML private TextField dateField;
    @FXML private TextField capacityField;

    Post event;
    public void initData(Post post) {
        this.event=post;
        titleField.setText(event.getTitle());
        descriptionField.setText(event.getDescription());
        venueField.setText( ((Event)post).getVenue());
        dateField.setText(((Event)post).getDate());
        capacityField.setText( Integer.toString(((Event)post).getCapacity()));
    }
    public void okButtonHandle(ActionEvent actionEvent) {
    }

    public void cancelButtonHandle(ActionEvent actionEvent) {
    }
}
