package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SaleReplyMessageController {
    @FXML private Text textField;

    public void OkButtonHandle(ActionEvent actionEvent) {
        //get the stage information
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.close();
    }

    public void initData(String message) {
        textField.setText(message);
    }
}
