package controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.*;
import model.exceptions.FormatException;
import model.exceptions.NameException;

import java.io.IOException;


public class view1Controller {

    @FXML private TextField nameField;
    @FXML private Label outputLabel;

    UniLink startup = new UniLink();


    public void loginButtonHandle(ActionEvent actionEvent) {
        String a= nameField.getText();
        try{
            if(startup.login(a)) {
//                outputLabel.setText("Welcome,"+ a);
                changeToMainWindow(actionEvent);
            }
        }catch(FormatException fe) {
               outputLabel.setText(fe.getReason());

            }catch (NameException name) {
            outputLabel.setText(name.getReason());
            } catch (Exception e) {
            outputLabel.setText(e.getMessage());
            e.printStackTrace();
            }


    }


    public void textFieldHandle(ActionEvent actionEvent) {

    }

    public void changeToMainWindow(ActionEvent event) throws IOException {
        Parent mainWindow= FXMLLoader.load(getClass().getResource("/view/MainWindow.fxml"));
        Scene scene= new Scene(mainWindow);

        //get the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
