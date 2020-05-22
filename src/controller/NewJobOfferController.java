package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Job;

public class NewJobOfferController {

    @FXML private TextField OfferField;
    @FXML private Label messageLabel;
    private Job job;

    public void okButtonHandle(ActionEvent actionEvent) {
    }

    public void cancelButtonHandle(ActionEvent actionEvent) {
        closeThisWindow(actionEvent);
    }


    //close this window
    public void closeThisWindow(ActionEvent actionEvent) {
        //get the stage information
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST)); //setup action when closing window: update main post listview
        window.close();
    }

    //passing data from main screen to this window
    public void initData(Job post) {
        job=post;
    }

}
