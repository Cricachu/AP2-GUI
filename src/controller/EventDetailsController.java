package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Event;
import model.Post;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventDetailsController implements Initializable {


    @FXML private ImageView photo;
    @FXML private Label eventDetailsLabel;
    @FXML private Button editDetails;
    @FXML private Button backToMainWindow;
    Post event;

    public void initData(Post post){
        event=post;
        //set post details
         eventDetailsLabel.setText(event.getPostDetails());
        //cannot edit details if there's reply already
        if(event.getArrayReply().size()>0) {
            editDetails.setDisable(true);
        }

        //set photo
        photo.setImage(event.getPhoto());

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void clickBackToMainWindow(ActionEvent actionEvent) {
        try {
            view1Controller.changeToMainWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
