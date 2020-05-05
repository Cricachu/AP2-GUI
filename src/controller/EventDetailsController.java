package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Post;

import java.net.URL;
import java.util.ResourceBundle;

public class EventDetailsController implements Initializable {


    @FXML private Label eventDetailsLabel;
    Post event;

    public void initData(Post post){
        event=post;
    eventDetailsLabel.setText(event.getPostDetails());

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
